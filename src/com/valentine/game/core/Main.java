package com.valentine.game.core;

public abstract class Main
{

	public static void main(String[] args)
	{
		Screen.init();
		Game.init();
		Looper.init();
		Input.init();
		Window.init();
		
		System.err.println("[Game]");

	}

}
