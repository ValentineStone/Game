package com.valentine.game.entity.vfx.line;

import java.awt.event.*;
import java.util.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class LineBezier1d extends Entity implements MouseListener, MouseMotionListener, KeyListener
{
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

	private double dotRadius;

	private List<Dot> dots;
	private List<Dot> dotsForDeletion;

	private Dot selected;

	public LineBezier1d(Container _container, int _amountOfDots, double _dotRadius)
	{
		super(_container);

		Input.addKeyListener(this);
		Input.addMouseListener(this);
		Input.addMouseMotionListener(this);

		setDotRadius(_dotRadius);

		setDrawColorRandom();

		dotsForDeletion = new ArrayList<>();
		dots = new ArrayList<>();

		for (int i = 0; i < _amountOfDots; i++)
		{
			addDot(MathExt.random(getContainer().getWidth() / _amountOfDots) + i * getContainer().getWidth() / _amountOfDots, MathExt.random(getContainer().getHeight()));
		}
	}

	private void makeT()
	{
		int n = dots.size();
		for (int i = 0; i < n; i++)
		{
			int max = Math.max(n - 1 - i, i);
			int min = Math.min(n - 1 - i, i);
			//getDot(i).t = MathExt.factorial(n - 1) / (double) (MathExt.factorial(i) * MathExt.factorial(n - 1 - i));
			getDot(i).t = MathExt.factorial(max, n - 1) / (double) (MathExt.factorial(min));
			/*
			System.err.println("["+i+"]----------------");
			System.err.println("!(" + (n - 1)+") / !(" + i + ") * !(" + (n - 1 - i) + ")");
			System.err.println(MathExt.factorial(n - 1) / (double) (MathExt.factorial(i) * MathExt.factorial(n - 1 - i)));
			System.err.println(MathExt.factorial(n - 1 - i, n - 1) / (double) (MathExt.factorial(i)));
			*/
		}
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

		for (Dot dot : dots)
		{
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

	public void update()
	{
		if (!dotsForDeletion.isEmpty())
		{
			for (Dot dot : dotsForDeletion)
			{
				dots.remove(dot);
			}
			makeT();
		}
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());

		paintBezier(_screen);
		
		paintDots(_screen);
	}

	private void paintBezier(Screen _screen)
	{
		int n = dots.size();

		double x;
		double y;
		double coeficent;

		double x_old = getDot(0).x;
		double y_old = getDot(0).y;

		for (double t = 0, dt = 0.001; t <= 1 + dt; t += dt)
		{

			x = 0;
			y = 0;

			for (int i = 0; i < n; i++)
			{
				coeficent = getDot(i).t * Math.pow(t, i) * Math.pow((1 - t), (n - 1) - i);
				x += coeficent * getDot(i).x;
				y += coeficent * getDot(i).y;
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
			//_screen.drawString(i + "", getDot(i).x + getDotRadius(), getDot(i).y - getDotRadius());
		}
	}

	public boolean kill(Entity _killer)
	{
		Input.removeKeyListener(this);
		Input.removeMouseListener(this);
		Input.removeMouseMotionListener(this);
		return kill(_killer);
	}

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
		}

	}

	public void keyReleased(KeyEvent _keyEvent)
	{}

	public void keyTyped(KeyEvent _keyEvent)
	{}

	public void mouseClicked(MouseEvent _mouseEvent)
	{}

	public void mouseEntered(MouseEvent _mouseEvent)
	{}

	public void mouseExited(MouseEvent _mouseEvent)
	{}

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

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		selected = null;
	}

	public void mouseDragged(MouseEvent _mouseEvent)
	{
		mouseMoved(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent)
	{
		if (selected != null)
		{
			selected.x = _mouseEvent.getX();
			selected.y = _mouseEvent.getY();
		}
	}

}
