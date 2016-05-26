package com.valentine.game.entity.ambient;

import java.awt.Color;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class PhasedMoon extends Entity implements KeyListener
{
	private double phase;
	private double dPhase = 0.015;
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

	public void paint()
	{
		Screen.setColor(getFillColor());
		Screen.fillOval(getX(), getY(), getWidth(), getHeight());
		Screen.setColor(getContainer().getFillColor());
		Screen.fillOval(getX() + getWidth() * getPhase(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		if (!isPaused()) updatePhase();
	}
	
	private void updatePhase()
	{
		setPhase(getPhase() + getdPhase());
		
		if (getPhase() > 1)
		{
			setPhase(1);
			setdPhase(-getdPhase());
		}
		else if (getPhase() < -1)
		{
			setPhase(-1);
			setdPhase(-getdPhase());
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
				if (getdPhase() < 0) setdPhase(-getdPhase());
				updatePhase();
				break;
			}
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			{
				if (getdPhase() > 0) setdPhase(-getdPhase());
				updatePhase();
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
	{
	}

	public void keyTyped(KeyEvent _keyEvent)
	{
	}
	
}
