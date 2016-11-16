package com.valentine.game.entity.vfx.line;

import java.awt.event.*;
import java.util.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class Line extends Entity implements MouseListener, MouseMotionListener, KeyListener
{
	protected class Dot
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

	protected List<Dot> dots;
	private List<Dot> dotsForDeletion;

	private Dot selected;

	public Line(Container _container, int _amountOfDots, double _dotRadius)
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
		}

		dotsForDeletion.clear();
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());

		for (int i = 1; i < size(); i++)
		{
			_screen.drawLine(getDot(i - 1).x, getDot(i - 1).y, getDot(i).x, getDot(i).y);
		}

		paintDots(_screen);
	}

	public void paintDots(Screen _screen)
	{
		_screen.setColor(getDrawColor());

		for (int i = 0; i < size(); i++)
		{
			_screen.drawOval((getDot(i).x - dotRadius), (getDot(i).y - dotRadius), (dotRadius + dotRadius), (dotRadius + dotRadius));
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
