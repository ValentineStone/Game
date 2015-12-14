package com.valentine.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.valentine.game.gameworld.*;

public abstract class GameWorld implements GameInputListener, OldEntity {
	
	protected static Dimension dimension;
	protected static boolean ready = false;
	
	private static GameWorld gameWorld;
	
	public static void init() {
		
		gameWorld = new GameWorldImpl_EntityOverhaul();
		
		System.err.println("[GameWorld]");
	}

	public static boolean isReady() {
		return ready;
	}
	
	public static Dimension getDimension() {
		return dimension;
	}
	
	public static void setDimension(Dimension _dimension) {
		dimension = _dimension;
	}
	
	public static GameWorld instance() {
		return gameWorld;
	}
	
	public abstract void assemble();

	// Keyboard events
	
	public void keyPressed(KeyEvent _event) {}

	public void keyReleased(KeyEvent _event) {}

	public void keyTyped(KeyEvent _event) {}
	
	// Mouse events

	public void mouseClicked(MouseEvent _event) {}

	public void mouseEntered(MouseEvent _event) {}

	public void mouseExited(MouseEvent _event) {}

	public void mousePressed(MouseEvent _event) {}

	public void mouseReleased(MouseEvent _event) {}

	public void mouseDragged(MouseEvent _event) {}

	public void mouseMoved(MouseEvent _event) {}
}