package com.valentine.game.utils;

import com.valentine.game.Game;

public final class Painter
{
	private static Thread thread;	
	private static boolean isRunning = false;
	private static int delay = 5;
	private static Double interpolation = new Double(1);
	
	public static void init()
	{
		
		thread = new Thread(
							new Runnable()
							{
								public void run()
								{
									while (true)
									{
										if (isRunning)
										{
											interpolate();
											Display.repaint();
										}
										
										try
										{
											Thread.sleep(delay);
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
	
	public static synchronized void interpolate()
	{
		interpolation = (System.nanoTime() - Updater.lastUpdateNanos) / (double)Updater.delayNanos;
	}
	
	public static double getInterpolation()
	{
		return interpolation;
	}

}
