package com.valentine.game.utils;

public final class Painter
{
	private static Thread thread;	
	private static boolean isRunning = false;
	
	private static long periodMs = 5;
	
	
	private static long lastTickNs;
	
	public static void init()
	{
		lastTickNs = System.nanoTime();
		
		thread = new Thread(
							new Runnable()
							{
								public void run()
								{
									while (true)
									{
										if (isRunning)
										{
											lastTickNs = System.nanoTime();
											Interpolation.set();
											Display.repaint();
										}
										
										try
										{
											Thread.sleep(periodMs);
										}
										catch (InterruptedException e)
										{
											e.printStackTrace();
										}
									}
								}
							}
							);
		pause();
		thread.start();
		
		System.err.println("[Painter]");
	}
	
	public static void start()
	{
		System.err.println("[Painter].start()");
		isRunning = true;
	}
	
	public static void pause()
	{
		System.err.println("[Painter].pause()");
		isRunning = false;
	}
	
	public static void paint()
	{
		Game.instance().paint();
	}
	
	public static long getLastTickNs()
	{
		return lastTickNs;
	}

}
