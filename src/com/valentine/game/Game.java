package com.valentine.game;

import com.valentine.game.gameworld.*;

public abstract class Game {

	public static GameUpdater myGameUpdater;
	public static GamePainter myGamePainter;
	public static GameInputHandler myGameInputHandler;
	public static GameInterface myGameInterface;
	public static GameWorld myGameWorld;
	
	static {	
		myGameUpdater = new GameUpdater();
		myGamePainter = new GamePainter();
		myGameInputHandler = new GameInputHandler();
		myGameInterface = new GameInterface();
		myGameWorld = new GameWorldImpl_Line();

		
		System.err.println("[Game]");
	}
	
	public static void main(String[] args) {
		
	}
}
