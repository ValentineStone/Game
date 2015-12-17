package com.valentine.game;

import com.valentine.game.utils.InputHandler;
import com.valentine.game.utils.Interface;
import com.valentine.game.utils.Painter;
import com.valentine.game.utils.Updater;

public abstract class Game {

	public static GameWorld gameWorld;
	
	static {	
		Updater.init();
		Painter.init();
		InputHandler.init();
		Interface.init();

		System.err.println("[Game]");
	}
	
	public static void main(String[] args) {
		
	}
}
