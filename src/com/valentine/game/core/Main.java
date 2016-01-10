package com.valentine.game.core;

public abstract class Main
{

	public static void main(String[] args)
	{
		Screen.init();
		Input.init();
		Game.init();
		Window.init();
		Looper.init();
		
		System.err.println("[Main]");

	}

}
