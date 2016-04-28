package com.valentine.game.entity.vfx;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.valentine.game.core.Input;
import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.entity.base.Layer;
import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.MathExt;

public class Star extends Layer implements KeyListener
{
	public int level = 0;
	
	private StarSide left;
	private StarSide right;
	private StarSide bottom;
	
	private double sideLenght;
	
	private double xTop;
	private double yTop;
	private double xLeft;
	private double yLeft;
	private double xRight;
	private double yRight;
	
	private boolean LEVEL_UP = false;
	private boolean LEVEL_DOWN = false;
	
	public Star(Container _container, double _x, double _y, double _sideLenght)
	{
		super(_container, _x, _y);
		
		Input.addKeyListener(this);
		
		setFillColor(ColorExt.makeTransparent(Color.BLACK, 220));
		
		sideLenght =_sideLenght;
		
		xTop = sideLenght / 2.;
		yTop = sideLenght - Math.pow(3, 0.5) * sideLenght / 2;
		xLeft = 0;
		yLeft = sideLenght;
		xRight = sideLenght;
		yRight = sideLenght;
		
		bottom = new StarSide(this, 0, xLeft, yLeft, xRight, yRight);
		right = new StarSide(this, 0, xRight, yRight, xTop, yTop);
		left = new StarSide(this, 0, xTop, yTop, xLeft, yLeft);
	}
	
	public void paint()
	{
		Screen.setColor(Color.MAGENTA);
		super.paint();
		Screen.drawString("LEVEL: " + level, getX(), getY() + 30);
	}
	
	public void update()
	{
		super.update();
		
		if (LEVEL_DOWN || LEVEL_UP)
		{
			if (LEVEL_DOWN)
			{
				if (level > 0) level--;
				LEVEL_DOWN = false;
			}
			if (LEVEL_UP)
			{
				level++;
				LEVEL_UP = false;
			}
			
			bottom.update();
			right.update();
			left.update();
		}
	}

	public void keyPressed(KeyEvent _keyEvent)
	{
		switch(_keyEvent.getKeyChar())
		{
			case '=':
			case '+':
			{
				LEVEL_UP = true;
				break;
			}
			case '-':
			{
				LEVEL_DOWN = true;
				break;
			}
		}
	}
	
	public void keyTyped(KeyEvent _keyEvent) {}
	public void keyReleased(KeyEvent _keyEvent) {}
}

class StarSide extends Entity
{
	private StarSide uno;
	private StarSide duo;
	private StarSide tre;
	private StarSide qtr;
	
	private double xBeg;
	private double yBeg;
	private double x2;
	private double y2;
	private double x3;
	private double y3;
	private double x4;
	private double y4;
	private double xEnd;
	private double yEnd;
	private double normal;
	private double segmentLengh;
	private double triangleHeight;
	
	private int level = 0;
	
	public StarSide(Container _star, int _level, double _xBeg, double _yBeg, double _xEnd, double _yEnd)
	{
		super(_star);
		
		setDrawColor(ColorExt.randomColor(20, 255));
		setUpdatable(false);
		
		level = _level;
		
		xBeg = _xBeg;
		yBeg = _yBeg;
		xEnd = _xEnd;
		yEnd = _yEnd;
		
		x2 = xBeg + (xEnd - xBeg) / 3.;
		y2 = yBeg + (yEnd - yBeg) / 3.;
		normal = MathExt.rotationMake(xEnd - xBeg, yEnd - yBeg) + MathExt.PI_1_2;
		segmentLengh = MathExt.distanceMake(xEnd - xBeg, yEnd - yBeg) / 3.;
		triangleHeight = Math.pow(3, 0.5) * segmentLengh / 2.;
		x3 = (xEnd + xBeg) / 2. + MathExt.rotationMakeX(normal) * triangleHeight;
		y3 = (yEnd + yBeg) / 2. + MathExt.rotationMakeY(normal) * triangleHeight;
		x4 = xBeg + 2 * (xEnd - xBeg) / 3.;
		y4 = yBeg + 2 * (yEnd - yBeg) / 3.;
		
		update();
	}
	
	public void paint()
	{
		//Screen.setColor(getDrawColor());
		if (uno != null && duo != null && tre != null && qtr != null) return;
		
		Screen.drawLine(xBeg, yBeg, x2, y2);
		Screen.drawLine(x2, y2, x3, y3);
		Screen.drawLine(x3, y3, x4, y4);
		Screen.drawLine(x4, y4, xEnd, yEnd);
	}

	public void update()
	{
		if (level < ((Star)getContainer()).level)
		{
			if (uno == null) makeChildren();
			else
			{
				uno.update();
				duo.update();
				tre.update();
				qtr.update();
			}
		}
		else
		{
			massakarsh();
		}
		
	}
	
	public void makeChildren()
	{
		uno = new StarSide(getContainer(), level+1, xBeg, yBeg, x2, y2);
		duo = new StarSide(getContainer(), level+1, x2, y2, x3, y3);
		tre = new StarSide(getContainer(), level+1, x3, y3, x4, y4);
		qtr = new StarSide(getContainer(), level+1, x4, y4, xEnd, yEnd);
	}
	
	private void massakarsh()
	{
		if (uno != null)
		{
			uno.massakarsh();
			uno.kill(this);
			uno = null;
		}
		if (duo != null)
		{
			duo.massakarsh();
			duo.kill(this);
			duo = null;
		}
		if (tre != null)
		{
			tre.massakarsh();
			tre.kill(this);
			tre = null;
		}
		if (qtr != null)
		{
			qtr.massakarsh();
			qtr.kill(this);
			qtr = null;
		}
	}
}
