package com.valentine.game.core.loop;

import com.valentine.game.core.interfaces.*;

public abstract class Loop implements Runnable
{
	public Thread thread;

	public enum State
	{
		NOT_STARTED, RUNNING, STOPPED
	}

	protected PaintableSmart paintable;
	protected Updatable updatable;

	protected State state = State.NOT_STARTED;

	protected final double updatesPerSecond = 25;
	protected final double updatePeriodNs = 1000 * 1000 * 1000 / updatesPerSecond;

	protected volatile int fps = 0;
	protected volatile int ups = 0;

	protected double interpolation;

	protected boolean isPaintable = false;
	protected boolean isUpdatable = false;

	public Loop()
	{
		this(null, null);
	}

	public Loop(PaintableSmart _paintable, Updatable _updatable)
	{
		paintable = _paintable;
		updatable = _updatable;

		enable();

		thread = new Thread(this);
		thread.setDaemon(false);

		state = State.RUNNING;
		thread.start();
	}

	public void setPaintable(PaintableSmart _paintable)
	{
		paintable = _paintable;
	}

	public void setUpdatable(Updatable _updatable)
	{
		updatable = _updatable;
	}

	public void setBasicEntity(BasicEntitySmart _basicEntity)
	{
		paintable = _basicEntity;
		updatable = _basicEntity;
	}

	public void paint()
	{
		if (isPaintable())
		{
			paintable.paint();
		}
	}

	public void update()
	{
		if (isUpdatable())
		{
			updatable.update();
		}
	}

	public double getInterpolation()
	{
		return interpolation;
	}

	public boolean isPaintable()
	{
		return isPaintable;
	}

	public void setIsPaintable(boolean _isPaintable)
	{
		isPaintable = paintable != null ? _isPaintable : false;
	}

	public boolean isUpdatable()
	{
		return isUpdatable;
	}

	public void setIsUpdatable(boolean _isUpdatable)
	{
		isUpdatable = updatable != null ? _isUpdatable : false;
	}

	public void enable()
	{
		setIsPaintable(true);
		setIsUpdatable(true);
	}

	public void disable()
	{
		setIsPaintable(false);
		setIsUpdatable(false);
	}

	public double getUpdatesPerSecond()
	{
		return updatesPerSecond;
	}

	public double getUpdatePeriodNs()
	{
		return updatePeriodNs;
	}

	public int getFps()
	{
		return fps;
	}

	public int getUps()
	{
		return ups;
	}
}
