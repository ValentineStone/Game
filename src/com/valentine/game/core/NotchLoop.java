package com.valentine.game.core;

public final class NotchLoop extends Entity
{
	public Thread thread;
	
	private Paintable paintable;
	private Updatable updatable;
	
	private /*volatile*/ boolean running = true;

	public final double updatesPerSecond = 25;
	public final double updatePeriodNs = 1000 * 1000 * 1000 / updatesPerSecond;
	
	public volatile int fps = 0;
	public volatile int ups = 0;
	
	private double interpolation;

	public NotchLoop(Paintable _paintable, Updatable _updatable)
	{
		paintable = _paintable;
		updatable = _updatable;
		
		thread = new Thread
		(
			new Runnable()
			{
				private double fakeInterpolation = 0;
				private long lastTick = System.nanoTime();
				private long thisTick = System.nanoTime();
				private long updateTick = System.nanoTime();
				private long second = System.currentTimeMillis();
				private int _fps = 0;
				private int _ups = 0;
				
				public void run()
				{
					while (running)
					{
						{
							thisTick = System.nanoTime();
							
							fakeInterpolation += (thisTick - lastTick) / updatePeriodNs;
							
							lastTick = thisTick;
							
							if (fakeInterpolation >= 1)
							{
								updateTick = System.nanoTime();
								
								update();
								
								_ups++;
								
								fakeInterpolation--;
								
								continue;
							}
							
							interpolation = (thisTick - updateTick) / updatePeriodNs;
							
							paint();
							
							_fps++;
							
							if (System.currentTimeMillis() - second > 1000)
							{
								second += 1000;
								fps = _fps;
								_fps = 0;
								ups = _ups;
								_ups = 0;
							}
						}
					}
				}
			}		
		);
		
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

}
