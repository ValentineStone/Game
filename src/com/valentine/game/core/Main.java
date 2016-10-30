package com.valentine.game.core;

public abstract class Main
{

	public static void main(String[] args)
	{
		// Create a new screen and initialize the static wrapper with it.
		Screen screen = new SwingScreen();
		Screen.setStaticRedirect(screen);
		
		// Create new yame painted in the given screen.
		// Will call all the constructors recursively
		Yame yame = new Yame(screen);
		
		// Add the given yame as the one being painted by given screen.
		screen.setPaintable(yame);
		
		PUDummy puDummy = new PUDummy();
		
		// Acreate and start a notch loop on yame and screen
		NotchLoop notchLoop = new NotchLoop(puDummy, puDummy);
	}

}
