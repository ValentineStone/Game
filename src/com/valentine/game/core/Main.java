package com.valentine.game.core;

public abstract class Main
{

	public static void main(String[] args)
	{
		Game.init();
		Looper.init();
		Input.init();
		Window.init();
		Screen.init();
		
		System.err.println("[Game]");

	}

}
