package com.valentine.game.core;

public final class Looper
{
	public static Thread thread;
	
	private static boolean running = false;

	private static long updatePeriodMs = 40;
	private static long updatePeriodNs = updatePeriodMs * 1000000;
	
	
	private static long paintPeriodMs = 5;
	
	private static long lastPaintTick = System.nanoTime();
	
	private static long lastUpdateTick = System.nanoTime();
	
	public static void init()
	{
		thread = new Thread(
							new Runnable()
							{
								public void run()
								{
									while (true)
									{
										if (isRunning())
										{
											lastPaintTick = System.nanoTime();
											
											Interpolation.set(lastPaintTick, lastUpdateTick, updatePeriodNs);
											
											if (Interpolation.get() >= 1)
											{
												lastUpdateTick = System.nanoTime();
												
												Game.instance().update();
												
												continue;
											}
											
											Window.repaint();
										}
										
										try
										{
											Thread.sleep(paintPeriodMs);
										}
										catch (InterruptedException e)
										{
											e.printStackTrace();
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
		Interpolation.set(0, 0, 1);
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
	
	public static boolean isRunning() {
		return running;
	}

}