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
	
	private static long second = 0;
	private static long _second = 0;
	
	public static int fps = 0;
	public static int ups = 0;
	private static int _fps = 0;
	private static int _ups = 0;
	
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
												Game.instance().update();
												
												lastUpdateTick = System.nanoTime();
												
												//Interpolation.set(lastPaintTick, lastUpdateTick, updatePeriodNs);
												
												_ups++;
												
												continue;
											}
											
											Window.repaint();
											
											_fps++;
											
											if (second != (_second = lastPaintTick / 1000000000))
											{
												fps = _fps;
												_fps = 0;
												ups = _ups;
												_ups = 0;
												second = _second;
											}
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
