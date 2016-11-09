package com.valentine.game.core;

public final class NotchLoop extends Loop
{
	private double fakeInterpolation = 0;
	private long lastTick = System.nanoTime();
	private long thisTick = System.nanoTime();
	private long updateTick = System.nanoTime();
	private long second = System.currentTimeMillis();
	private int fpsCounter = 0;
	private int upsCounter = 0;
	
	public void run()
	{
		update();
		
		while (state == State.RUNNING)
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
