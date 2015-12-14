package com.valentine.game;

public final class GameUpdater{
	private static Thread thread;
	private static boolean isRunning;
	private static int delay = 40;
	public static long lastUpdateNanos;
	public static long delayNanos = delay * 1000000;
	
	
	public static void init() {
		
		lastUpdateNanos = System.nanoTime();
		
		thread = new Thread(new Runnable(){
			public void run() {
				while (true) {
					if (isRunning) {
						lastUpdateNanos = System.nanoTime();
						GameWorld.instance().update();
					}
					
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}});
		
		pause();
		thread.start();
		
		System.err.println("[Updater]");
	}
	
	public static void start() {
		System.err.println("[Updater].start()");
		isRunning = true;
	}
	
	public static void pause() {
		System.err.println("[Updater].pause()");
		isRunning = false;
	}


}
