package com.valentine.game;

public abstract class Game {

	public static GameWorld gameWorld;
	
	static {	
		GameUpdater.init();
		GamePainter.init();
		GameInputHandler.init();
		GameInterface.init();

		System.err.println("[Game]");
	}
	
	public static void main(String[] args) {
		
	}
}
