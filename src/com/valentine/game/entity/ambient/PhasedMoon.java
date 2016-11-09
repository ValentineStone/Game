package com.valentine.game.entity.ambient;

import java.awt.*;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;

public class PhasedMoon extends Entity implements KeyListener
{
	private double phase = 0;
	private double dPhase = -0.002;
	private double r;
	private boolean paused;

	public PhasedMoon(Container _container, double _r)
	{
		super(_container);

		Input.addKeyListener(this);

		setR(_r);
		setSize(getR() * 2, getR() * 2);
		setPositionCentered();

		setFillColor(new Color(255, 255, 100));
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillOval(getX(), getY(), getWidth(), getHeight());
		_screen.setColor(getContainer().getFillColor());
		_screen.fillOval(getX() + getWidth() * getPhase(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		if (!isPaused())
		{
			updatePhase();
		}
	}

	private void updatePhase()
	{
		setPhase(getPhase() + getdPhase());

		if (getPhase() < -1)
		{
			setPhase(1);
		}
		else if (getPhase() > 1)
		{
			setPhase(-1);
		}
	}

	public double getPhase()
	{
		return phase;
	}

	public void setPhase(double _phase)
	{
		phase = _phase;
	}

	public double getdPhase()
	{
		return dPhase;
	}

	public void setdPhase(double _dPhase)
	{
		dPhase = _dPhase;
	}

	public double getR()
	{
		return r;
	}

	public void setR(double _r)
	{
		r = _r;
	}

	public boolean isPaused()
	{
		return paused;
	}

	public void setPaused(boolean _paused)
	{
		paused = _paused;
	}

	public void keyPressed(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			{
				if (getdPhase() < 0)
				{
					setdPhase(-getdPhase());
				}
				updatePhase();
				break;
			}
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			{
				if (getdPhase() > 0)
				{
					setdPhase(-getdPhase());
				}
				updatePhase();
				break;
			}
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
			{
				setdPhase(getdPhase() + getdPhase() / 10);
				break;
			}
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			{
				setdPhase(getdPhase() - getdPhase() / 10);
				break;
			}
			case KeyEvent.VK_SPACE:
			{
				setPaused(isPaused() ? false : true);
				break;
			}
		}
	}

	public void keyReleased(KeyEvent _keyEvent)
	{}

	public void keyTyped(KeyEvent _keyEvent)
	{}

}
