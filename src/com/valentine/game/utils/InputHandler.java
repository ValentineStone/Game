package com.valentine.game.utils;

import java.awt.event.*;

import com.valentine.game.GameWorld;

public class InputHandler implements KeyListener, MouseListener, ComponentListener, MouseMotionListener {
	
	private static InputHandler inputHandler = new InputHandler();
	
	public static void init() {
		System.err.println("[InputHandler]");
	}
	
	public static InputHandler instance() {
		return inputHandler;
	}

	public void keyPressed(KeyEvent _keyEvent) {
		GameWorld.instance().keyPressed(_keyEvent);
	}

	public void keyReleased(KeyEvent _keyEvent) {
		GameWorld.instance().keyReleased(_keyEvent);
	}

	public void keyTyped(KeyEvent _keyEvent) {
		GameWorld.instance().keyTyped(_keyEvent);
	}

	public void mouseClicked(MouseEvent _mouseEvent) {
		GameWorld.instance().mouseClicked(_mouseEvent);
	}

	public void mouseEntered(MouseEvent _mouseEvent) {
		
	}
	
	public void mouseExited(MouseEvent _mouseEvent) {
		
	}

	public void mousePressed(MouseEvent _mouseEvent) {
		GameWorld.instance().mousePressed(_mouseEvent);
	}

	public void mouseReleased(MouseEvent _mouseEvent) {
		GameWorld.instance().mouseReleased(_mouseEvent);
	}

	public void windowActivated(WindowEvent _componentEvent) {
		
	}

	public void componentHidden(ComponentEvent _componentEvent) {
		
	}

	public void componentMoved(ComponentEvent _componentEvent) {
		
	}

	public void componentResized(ComponentEvent _componentEvent) {
		GameWorld.setDimension(Interface.getDimension());
		if (Interface.getDimension().height == 0 || GameWorld.isReady()) return;
		GameWorld.init();
		GameWorld.instance().assemble();
		Updater.start();
		Painter.start();
	}

	public void componentShown(ComponentEvent _componentEvent) {

	}

	public void mouseDragged(MouseEvent _mouseEvent) {
		GameWorld.instance().mouseDragged(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent) {
		GameWorld.instance().mouseMoved(_mouseEvent);
		
	}
}
