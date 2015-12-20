package com.valentine.game.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

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
											
											BufferStrategy b = Display.getCanvas().getBufferStrategy();
											if (b == null)
											{
												Display.getCanvas().createBufferStrategy(3);
												b = Display.getCanvas().getBufferStrategy();
											}
											Graphics2D g = (Graphics2D) b.getDrawGraphics();
											
											Screen.setGraphics(g);
											Game.instance().paint();
											
											g.dispose();
											b.show();
											
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
	
	public static long getLastTickNs()
	{
		return lastTickNs;
	}

}
