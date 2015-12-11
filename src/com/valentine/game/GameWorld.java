package com.valentine.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.valentine.game.gameworld.Entity;
import com.valentine.game.gameworld.GameInputListener;

public abstract class GameWorld implements GameInputListener, Entity {
	
	protected Dimension dimension;
	protected boolean ready;
	protected double interpolation;
	
	{
		System.err.println("[GameWorld]");
		dimension = new Dimension(0,0);
		ready = false;
		interpolation = 1;
	}
	
	public abstract void assemble();
	
	public Dimension getDimension() {
		return dimension;
		
	}

	public double getInterpolation() {
		return interpolation;
	}

	public void setDimension(Dimension _dimension) {
		dimension = _dimension;
		
	}

	public void setInterpolation(double _interpolation) {
		interpolation = _interpolation;
		
	}

	public boolean isReady() {
		return ready;
	}

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