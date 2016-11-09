package com.valentine.game.core;

public class BasicLoop extends Loop
{
	private int sleepTime;

	public BasicLoop(int _sleepTime)
	{
		sleepTime = _sleepTime;
	}
	
	public void run()
	{
		update();
		paint();
		
		try
		{
			Thread.sleep(sleepTime);
		}
		catch (Exception _e) {}
	}

}
