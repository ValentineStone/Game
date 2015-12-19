package com.valentine.game.utils;

public final class Updater
{
	private static Thread thread;
	private static boolean isRunning;
	
	private static long periodMs = 40;
	private static long periodNs = periodMs * 1000000;
	
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
											//Interpolation.set();
											Game.instance().update();
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
		
		System.err.println("[Updater]");
	}
	
	public static void start()
	{
		System.err.println("[Updater].start()");
		isRunning = true;
	}
	
	public static void pause()
	{
		System.err.println("[Updater].pause()");
		isRunning = false;
	}
	
	public synchronized static long getLastTickNs()
	{
		return lastTickNs;
	}
	
	public static long getPeriodNs()
	{
		return periodNs;
	}


}
