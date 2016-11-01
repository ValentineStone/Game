package com.valentine.game.core;

public abstract class Loop implements Runnable
{
	public Thread thread;
	
	public enum State
	{
		
	}
	
	private Paintable paintable;
	private Updatable updatable;
	
	private boolean running = true;

	private final double updatesPerSecond = 25;
	private final double updatePeriodNs = 1000 * 1000 * 1000 / updatesPerSecond;
	
	private volatile int fps = 0;
	private volatile int ups = 0;
	
	private double interpolation;
	
	private boolean isPaintable = true;
	private boolean isUpdatable = true;

	public Loop (Paintable _paintable, Updatable _updatable)
	{
		paintable = _paintable;
		updatable = _updatable;
		
		thread = new Thread(this);
		
		thread.setDaemon(false);
	}
	
	
	

	public void paint()
	{
		if (isPaintable()) paintable.paint();
		
	}

	public void update()
	{
		if (isUpdatable()) updatable.update();
	}
	
	
	
	
	
	
	
	public double getInterpolation()
	{
		return interpolation;
	}
	
	
	
	
	
	
	
	
	public boolean isPaintable()
	{
		return isPaintable;
	}

	public void setPaintable(boolean _isPaintable)
	{
		isPaintable = _isPaintable;
	}

	public boolean isUpdatable()
	{
		return isUpdatable;
	}

	public void setUpdatable(boolean _isUpdatable)
	{
		isUpdatable = _isUpdatable;
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
