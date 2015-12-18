package com.valentine.game;

import com.valentine.game.utils.Display;
import com.valentine.game.utils.InputHandler;
import com.valentine.game.utils.Painter;
import com.valentine.game.utils.Updater;

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
