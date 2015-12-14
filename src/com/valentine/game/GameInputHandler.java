package com.valentine.game;

import java.awt.event.*;

public class GameInputHandler implements KeyListener, MouseListener, WindowListener, ComponentListener, MouseMotionListener {
	
	private static GameInputHandler gameInputHandler = new GameInputHandler();
	
	public static void init() {
		System.err.println("[InputHandler]");
	}
	
	public static GameInputHandler instance() {
		return gameInputHandler;
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

	public void windowActivated(WindowEvent arg0) {
		
	}

	public void windowClosed(WindowEvent arg0) {
		
	}

	public void windowClosing(WindowEvent arg0) {
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		
	}

	public void windowIconified(WindowEvent arg0) {
		
	}

	public void windowOpened(WindowEvent arg0) {
	}

	public void componentHidden(ComponentEvent arg0) {
		
	}

	public void componentMoved(ComponentEvent arg0) {
		
	}

	public void componentResized(ComponentEvent _componentEvent) {
		if (GameInterface.getDimension().height == 0 || GameWorld.isReady()) return;
		GameWorld.init();
		GameWorld.instance().assemble();
		GameUpdater.start();
		GamePainter.start();
	}

	public void componentShown(ComponentEvent arg0) {

	}

	public void mouseDragged(MouseEvent _mouseEvent) {
		GameWorld.instance().mouseDragged(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent) {
		GameWorld.instance().mouseMoved(_mouseEvent);
		
	}
}
