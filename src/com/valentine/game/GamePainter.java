package com.valentine.game;

import java.awt.*;

public final class GamePainter implements Runnable {
	public Thread myThread;	
	public boolean isRunning;
	public int delay;
	
	{
		delay = 5;		
		
		myThread = new Thread(this);
		pause();
		myThread.start();
		
	}
	
	public GamePainter() {
		System.err.println("[Painter]");
	}
	
	public void start() {
		System.err.println("[Painter].start()");
		isRunning = true;
	}
	
	public void pause() {
		System.err.println("[Painter].pause()");
		isRunning = false;
	}
	
	public void run() {
		while (true) {
			if (isRunning) {
				Game.myGameInterface.getMyJPanel().repaint();
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics _graphics){
		Game.myGameWorld.setInterpolation((System.nanoTime() - Game.myGameUpdater.lastUpdateNanos) / Game.myGameUpdater.delayNanos);
		Game.myGameWorld.paint(_graphics);

	}

}
