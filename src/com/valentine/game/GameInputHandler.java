package com.valentine.game;

import java.awt.event.*;

public class GameInputHandler implements KeyListener, MouseListener, WindowListener, ComponentListener, MouseMotionListener {
	
	public GameInputHandler(){
		System.err.println("[InputHandler]");
	}

	public void keyPressed(KeyEvent _keyEvent) {
		Game.myGameWorld.keyPressed(_keyEvent);
	}

	public void keyReleased(KeyEvent _keyEvent) {
		Game.myGameWorld.keyReleased(_keyEvent);
	}

	public void keyTyped(KeyEvent _keyEvent) {
		Game.myGameWorld.keyTyped(_keyEvent);
	}

	public void mouseClicked(MouseEvent _mouseEvent) {
		Game.myGameWorld.mouseClicked(_mouseEvent);
	}

	public void mouseEntered(MouseEvent _mouseEvent) {
		
	}
	
	public void mouseExited(MouseEvent _mouseEvent) {
		
	}

	public void mousePressed(MouseEvent _mouseEvent) {
		Game.myGameWorld.mousePressed(_mouseEvent);
	}

	public void mouseReleased(MouseEvent _mouseEvent) {
		Game.myGameWorld.mouseReleased(_mouseEvent);
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
		Game.myGameWorld.setDimension(Game.myGameInterface.getMyJPanel().getSize());
		if (Game.myGameInterface.getMyJPanel().getHeight() == 0 || Game.myGameWorld.isReady() == true) return;
		Game.myGameWorld.assemble();
		Game.myGameUpdater.start();
		Game.myGamePainter.start();
	}

	public void componentShown(ComponentEvent arg0) {

	}

	public void mouseDragged(MouseEvent _mouseEvent) {
		Game.myGameWorld.mouseDragged(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent) {
		Game.myGameWorld.mouseMoved(_mouseEvent);
		
	}
}
