package com.valentine.game.core;

public final class Looper
{
	public static Thread thread;
	
	private static volatile boolean running = false;
	private static volatile boolean working = true;

	public static final double updatesPerSecond = 25;
	public static final double updatePeriodNs = 1000 * 1000 * 1000 / updatesPerSecond;
	
	public static volatile int fps = 0;
	public static volatile int ups = 0;
	
	public static void init()
	{
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
					while (Looper.isWorking())
					{
						//if (Looper.isRunning())
						{
							thisTick = System.nanoTime();
							
							fakeInterpolation += (thisTick - lastTick) / Looper.updatePeriodNs;
							
							lastTick = thisTick;
							
							if (fakeInterpolation >= 1)
							{
								updateTick = System.nanoTime();
								
								if (Looper.isRunning()) Game.instance().update();
								
								_ups++;
								
								fakeInterpolation--;
								
								continue;
							}
							
							Interpolation.set((thisTick - updateTick) / Looper.updatePeriodNs);
							
							if (Looper.isRunning()) Window.repaint();
							
							_fps++;
							
							if (System.currentTimeMillis() - second > 1000 && Looper.isRunning())
							{
								second += 1000;
								Looper.fps = _fps;
								_fps = 0;
								Looper.ups = _ups;
								_ups = 0;
							}
						}
					}
				}
			}		
		);
		
		thread.start();
		
		System.err.println("[Looper]");
	}
	
	public static void loop()
	{
		Interpolation.set(0);
		Game.instance().update();
		Window.repaint();
	}
	
	public static void play()
	{
		System.err.println("[Looper].play()");
		running = true;
	}
	
	public static void pause()
	{
		System.err.println("[Looper].pause()");
		running = false;
	}
	
	public static void kill()
	{
		System.err.println("[Looper].kill()");
		working = false;
	}
	
	public static boolean isRunning() {
		return running;
	}
	
	public static boolean isWorking() {
		return working;
	}

}
