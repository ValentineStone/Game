package com.valentine.game.utils;

public abstract class Main
{

	public static void main(String[] args)
	{
		Game.init();
		Updater.init();
		Painter.init();
		InputHandler.init();
		Display.init();
		
		System.err.println("[Game]");

	}

}
