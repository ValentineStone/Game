package com.valentine.game.entity.vfx.line;

import java.awt.event.*;
import java.util.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class LineOld extends Entity implements MouseListener, MouseMotionListener, KeyListener
{
	public enum Style
	{
		STRAIGHT,
		LAGRANGE,
		BEZIER
	}
	
	private class Dot
	{
		public double x;
		public double y;
		public double t;
		public Dot(double _x, double _y)
		{
			x = _x;
			y = _y;
		}
	}
	
	Style style = Style.STRAIGHT;
	Style scheduledStyle  = Style.STRAIGHT;
	
	private double dotRadius;
	
	private List<Dot> dots;
	private List<Dot> dotsForDeletion;
	
	private Dot selected;
	
	public LineOld(Container _container, int _amountOfDots, double _dotRadius, Style _style)
	{
		super(_container);
		
		Input.addKeyListener(this);
		Input.addMouseListener(this);
		Input.addMouseMotionListener(this);
		
		setDotRadius(_dotRadius);
		
		setDrawColorRandom();
		
		setStyle(_style);
		
		dotsForDeletion = new ArrayList<Dot>();
		dots = new ArrayList<Dot>();
		
		for (int i = 0; i < _amountOfDots; i++)
		{
			addDot(MathExt.random(getContainer().getWidth()/_amountOfDots) + i * getContainer().getWidth()/_amountOfDots, MathExt.random(getContainer().getHeight()));
		}
	}
	
	public LineOld(Container _container, int _amountOfDots, double _dotRadius)
	{
		this(_container, _amountOfDots, _dotRadius, Style.STRAIGHT);
	}
	
	private void makeT()
	{
		switch (getStyle())
		{
			case LAGRANGE:
			{
				int n = dots.size();
				double dt = 1/(n-1.);
				for (int i = 0; i < n; i++)
				{
					getDot(i).t = dt * i;
				}
				break;
			}
			case BEZIER:
			{
				int n = dots.size();
				for (int i = 0; i < n; i++)
				{
					getDot(i).t = MathExt.factorial(n-1) / (double)(MathExt.factorial(i) * MathExt.factorial(n-1-i));
				}
				break;
			}
			case STRAIGHT: {}
		}
	}
	
	
	
	public Style getStyle()
	{
		return style;
	}


	public void setStyle(Style _style)
	{
		scheduleStyleChange(_style);
	}


	public double getDotRadius()
	{
		return dotRadius;
	}


	public void setDotRadius(double _dotRadius)
	{
		dotRadius = _dotRadius;
	}

	
	

	public void randomize()
	{
		setDrawColorRandom();
		
		for (Dot dot : dots) {
			dot.x = MathExt.random(getContainer().getWidth());
			dot.y = MathExt.random(getContainer().getHeight());
		}
	}
	
	public void setDrawColorRandom()
	{
		setDrawColor(ColorExt.randomColor(35, 220));
	}
	
	
	private int size()
	{
		return dots.size();
	}
	
	public void addDot(double _x, double _y)
	{
		dots.add(new Dot(_x, _y));
		makeT();
	}
	
	protected Dot getDot(int _i)
	{
		return dots.get(_i);
	}
	
	public Dot getDotNear(double _x, double _y)
	{
		for (Dot dot : dots)
		{
			if (MathExt.distanceMake(dot.x, dot.y, _x, _y) < getDotRadius())
			{
				return dot;
			}
		}
		return null;
	}
	
	public void removeDot(Dot _dot)
	{
		scheduleDotDeletion(_dot);
	}
	
	public boolean removeDotNear(double _x, double _y)
	{
		Dot dotForRemoval = getDotNear(_x, _y);
		if (dotForRemoval != null)
		{
			removeDot(dotForRemoval);
			return true;
		}
		return false;
	}
	
	
	
	
	private void scheduleDotDeletion(Dot _dot)
	{
		dotsForDeletion.add(_dot);
	}
	
	private void scheduleStyleChange(Style _style)
	{
		scheduledStyle = _style;
	}
	
	
	
	
	@Override
	public void update()
	{
		boolean needsMakeT = false;
		
		if (!dotsForDeletion.isEmpty())
		{
			for (Dot dot : dotsForDeletion)
			{
				dots.remove(dot);
			}
			needsMakeT = true;
		}
		
		if (!scheduledStyle.equals(getStyle()))
		{
			style = scheduledStyle;
			
			if (getStyle() != Style.STRAIGHT)
			{
				needsMakeT = true;
			}
		}
		
		if (needsMakeT)
		{
			makeT();
		}
	}
	
	
	

	@Override
	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());
		
		switch (getStyle())
		{
			case LAGRANGE:
			{
				paintLagrange(_screen);
				break;
			}
			case BEZIER:
			{
				paintBezier(_screen);
				break;
			}
			default:
			{
				paintStraight(_screen);
			}
		}
		paintDots(_screen);
	}
	
	private void paintStraight(Screen _screen)
	{
		for (int i = 1; i < size(); i++)
		{
			_screen.drawLine(getDot(i-1).x, getDot(i-1).y, getDot(i).x, getDot(i).y);
		}
		
		for (int i = 0; i < size(); i++)
		{
			_screen.drawOval((getDot(i).x - dotRadius), (getDot(i).y - dotRadius), (dotRadius + dotRadius), (dotRadius + dotRadius));
		}
	}
	
	private void paintLagrange(Screen _screen)
	{
		int n = dots.size();
	
		double x;
		double y;
		double omega;
		
		double x_old = getDot(0).x;
		double y_old = getDot(0).y;
		
		for (double t = 0, dt = 0.001; t <= 1+dt; t += dt) {
			
			x = 0;
			y = 0;
			
			for (int i = 0; i < n; i++) {
				omega = 1;
				
				for (int j = 0; j < i; j++) {
					omega *= ((t - getDot(j).t)/(getDot(i).t - getDot(j).t));
				}
				for (int j = i+1; j < n; j++) {
					omega *= ((t - getDot(j).t)/(getDot(i).t - getDot(j).t));
				}
				
				x += omega * getDot(i).x;
				y += omega * getDot(i).y;
				
			}
			
			_screen.drawLine(x_old, y_old, x, y);
			
			x_old = x;
			y_old = y;
		}
	}
	
	private void paintBezier(Screen _screen)
	{
		int n = dots.size();
		
		double x;
		double y;
		double coeficent;
		
		double x_old = getDot(0).x;
		double y_old = getDot(0).y;
		
		for (double t = 0, dt = 0.001; t <= 1+dt; t += dt)
		{
			
			x = 0;
			y = 0;
			
			for (int i = 0; i < n; i++)
			{
				coeficent = getDot(i).t * Math.pow(t, i) * Math.pow((1-t), (n-1)-i);
				x +=  coeficent * getDot(i).x;
				y +=  coeficent * getDot(i).y;
			}
			
			_screen.drawLine(x_old, y_old, x, y);
			
			x_old = x;
			y_old = y;
		}
	}
	
	private void paintDots(Screen _screen)
	{
		for (int i = 0; i < dots.size(); i++)
		{
			_screen.drawOval(getDot(i).x - getDotRadius(), getDot(i).y - getDotRadius(), 2 * getDotRadius(), 2 * getDotRadius());
			_screen.drawString(i + "", getDot(i).x + getDotRadius(), getDot(i).y - getDotRadius());
		}
	}
	
	
	
	
	
	
	
	@Override
	public boolean kill(Entity _killer)
	{
		Input.removeKeyListener(this);
		Input.removeMouseListener(this);
		Input.removeMouseMotionListener(this);
		return kill(_killer);
	}
	
	
	
	
	
	

	@Override
	public void keyPressed(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_R:
			{
				randomize();
				break;
			}
			case KeyEvent.VK_C:
			{
				setDrawColorRandom();
				break;
			}
			case KeyEvent.VK_S:
			{
				switch (getStyle())
				{
					case LAGRANGE:
					{
						setStyle(Style.BEZIER);
						break;
					}
					case BEZIER:
					{
						setStyle(Style.STRAIGHT);
						break;
					}
					default:
					{
						setStyle(Style.LAGRANGE);
					}
				}
				break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent _keyEvent) {}

	@Override
	public void keyTyped(KeyEvent _keyEvent) {}

	@Override
	public void mouseClicked(MouseEvent _mouseEvent) {}

	@Override
	public void mouseEntered(MouseEvent _mouseEvent) {}

	@Override
	public void mouseExited(MouseEvent _mouseEvent) {}

	@Override
	public void mousePressed(MouseEvent _mouseEvent)
	{
		if (_mouseEvent.getButton() == MouseEvent.BUTTON3)
		{
			if (!removeDotNear(_mouseEvent.getX(), _mouseEvent.getY()))
			{
				addDot(_mouseEvent.getX(), _mouseEvent.getY());
			}
		}
		else if (_mouseEvent.getButton() == MouseEvent.BUTTON1)
		{
			selected = getDotNear(_mouseEvent.getX(), _mouseEvent.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent _mouseEvent)
	{
		selected = null;
	}

	@Override
	public void mouseDragged(MouseEvent _mouseEvent)
	{
		mouseMoved(_mouseEvent);
	}

	@Override
	public void mouseMoved(MouseEvent _mouseEvent)
	{
		if (selected != null)
		{
			selected.x = _mouseEvent.getX();
			selected.y = _mouseEvent.getY();
		}
	}

}
