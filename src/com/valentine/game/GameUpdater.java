package com.valentine.game;

public final class GameUpdater implements Runnable {
	public Thread myThread;
	public boolean isRunning;
	public int delay;
	public long lastUpdateNanos;
	public double delayNanos;
	
	
	{
		delay = 40;
		delayNanos = delay * 1000000.;
		
		lastUpdateNanos = System.nanoTime();
		myThread = new Thread(this);
		pause();
		myThread.start();
	}
	
	public GameUpdater() {
		System.err.println("[Updater]");
	}
	
	public void start() {
		System.err.println("[Updater].start()");
		isRunning = true;
	}
	
	public void pause() {
		System.err.println("[Updater].pause()");
		isRunning = false;
	}

	public void run() {
		while (true) {
			if (isRunning) {
				lastUpdateNanos = System.nanoTime();
				Game.myGameWorld.update();
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
