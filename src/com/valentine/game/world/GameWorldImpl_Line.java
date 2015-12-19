package com.valentine.game.world;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.valentine.game.entity.*;
import com.valentine.game.entity.line.BezierLine;
import com.valentine.game.entity.line.LagrangeLine;

public class GameWorldImpl_Line// extends GameWorld
{
/*
	
	private Line line;

	public void assemble() {
		
		line = new Line(5, 10);
		
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
		else if (_event.getKeyCode() == KeyEvent.VK_L) line = new LagrangeLine(line);
		else if (_event.getKeyCode() == KeyEvent.VK_B) line = new BezierLine(line);
		else if (_event.getKeyCode() == KeyEvent.VK_P) line = new Line(line);
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
*/
}
