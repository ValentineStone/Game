package com.valentine.game.core;

public final class NotchLoop
{
	public Thread thread;
	
	private Paintable paintable;
	private Updatable updatable;
	
	private boolean running = true;

	private final double updatesPerSecond = 25;
	private final double updatePeriodNs = 1000 * 1000 * 1000 / updatesPerSecond;
	
	private volatile int fps = 0;
	private volatile int ups = 0;
	
	private double interpolation;

	private double fakeInterpolation = 0;
	private long lastTick = System.nanoTime();
	private long thisTick = System.nanoTime();
	private long updateTick = System.nanoTime();
	private long second = System.currentTimeMillis();
	private int fpsCounter = 0;
	private int upsCounter = 0;
	
	private boolean isPaintable = true;
	private boolean isUpdatable = true;

	public NotchLoop (Paintable _paintable, Updatable _updatable)
	{
		paintable = _paintable;
		updatable = _updatable;
		
		thread = new Thread
		(
			new Runnable()
			{
				
				@Override
				public void run()
				{
					update();
					
					while (running)
					{
						thisTick = System.nanoTime();
						
						fakeInterpolation += (thisTick - lastTick) / updatePeriodNs;
						
						lastTick = thisTick;
						
						if (fakeInterpolation >= 1)
						{
							updateTick = System.nanoTime();
							
							update();
							
							upsCounter++;
							
							fakeInterpolation--;
							
							continue;
						}
						
						interpolation = (thisTick - updateTick) / updatePeriodNs;
						
						paint();
						
						fpsCounter++;
						
						if (System.currentTimeMillis() - second > 1000)
						{
							second += 1000;
							fps = fpsCounter;
							fpsCounter = 0;
							ups = upsCounter;
							upsCounter = 0;
						}
					}
				}
			}		
		);
		
		thread.setDaemon(false);
		thread.start();
	}
	
	
	
	
	
	
	
	public void stop()
	{
		running = false;
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
