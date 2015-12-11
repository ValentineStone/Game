package com.valentine.game.gameworld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.valentine.game.Game;
import com.valentine.game.GameWorld;
import com.valentine.game.gameworld.entity.*;

public class GameWorldImpl_Line extends GameWorld{
	private Line line;
	
	{
		ready = false;
	}

	public void assemble() {
		dimension = Game.myGameInterface.getMyJPanel().getSize();
		line = new LagrangeLine(5, 10);
		
		ready = true;
	}

	public void paint(Graphics _graphics) {
		if (ready == false) return;
		line.paint(_graphics);
		
	}
	
	public void update() {
		line.update();
	}

	public void keyPressed(KeyEvent _event) {
		if (_event.getKeyCode() == KeyEvent.VK_R) line.madMake();
		else if (_event.getKeyCode() == KeyEvent.VK_C) line.randColor();
		else if (_event.getKeyCode() == KeyEvent.VK_D) line.setMode(-1);
		else if (_event.getKeyCode() == KeyEvent.VK_A) line.setMode(1);
		else if (_event.getKeyCode() == KeyEvent.VK_L) { if (line instanceof BezierLine) line = new LagrangeLine(line); }
		else if (_event.getKeyCode() == KeyEvent.VK_B) { if (line instanceof LagrangeLine) line = new BezierLine(line); }
	}
	
	public void keyReleased(KeyEvent _event) {
		line.setMode(0);
	}

	public void mousePressed(MouseEvent _mouseEvent) {
		if (line.getMode() == -1) { if (line.select(_mouseEvent.getX(), _mouseEvent.getY()) != -1) line.deleteSelected(); }
		else {
			if (line.getMode() == 1) line.add(_mouseEvent.getX(), _mouseEvent.getY());
			else if (line.select(_mouseEvent.getX(), _mouseEvent.getY()) != -1) line.setMode(2);
		}
	}

	public void mouseReleased(MouseEvent _mouseEvent) {
		line.setMode(0);
	}

	public void mouseDragged(MouseEvent _e) {
		if (line.getMode() == 2) line.dragSelected(_e.getX(), _e.getY());
	}	

}
