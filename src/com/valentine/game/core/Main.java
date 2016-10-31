package com.valentine.game.core;

public abstract class Main
{

	public static void main(String[] args) throws InterruptedException
	{
		// Create a new screen and initialize the static wrapper with it.
		Screen screen = new SwingScreen();
		Screen.setStaticRedirect(screen);
		
		// Create new yame painted in the given screen.
		// Will call all the constructors recursively
		Game game = new Game(screen);
		
		// Add the given game as the one being painted by given screen.
		screen.setPaintable(game);
		
		PUDummy puDummy = new PUDummy();
		
		// Create and start a notch loop on game and screen
		NotchLoop notchLoop = new NotchLoop(puDummy, puDummy);
		notchLoop.setPaintable(false);
		
		puDummy.setNotchLoop(notchLoop);
		
		//Thread.sleep(60);
		//notchLoop.stop();
	}

}
