package com.valentine.game.entity.line;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.valentine.game.entity.Box;
import com.valentine.game.entity.Entity;
import com.valentine.game.listener.InputListener;
import com.valentine.game.utils.Canvas;

public class Line implements Entity, InputListener{
	
	protected class Dot {
		public double x;
		public double y;
		public double t;
		public Dot(double _x, double _y) {
			x = _x;
			y = _y;
		}
	}
	
	protected double r;
	
	protected int mode;
	
	protected Color color;

	protected int selected;
	
	protected ArrayList<Dot> dots;
	
	protected Box box;
	
	
	protected void makeT() {}
	
	
	public Line(Box _box, int _n, double _r)
	{
		box = _box;
		
		randColor();
		
		r = _r;
		
		mode = 0;
		
		selected = -1;
		
		dots = new ArrayList<Dot>();
		
		for (int i = 0; i < _n; i++) {
			add(Math.random() * box.getWidth()/_n + i * box.getWidth()/(_n), Math.random() * box.getHeight());
		}
		
	}
	
	
	public Line(Line _line) {		
		
		color = _line.color;
		r = _line.r;
		
		mode = _line.mode;
		
		selected = _line.selected;
		
		dots = _line.dots;
		
		makeT();
		
	}
	
	
	
	
	public void madMake() {
		randColor();
		
		for (Dot dot : dots) {
			dot.x = box.getWidth() * Math.random();
			dot.y = box.getHeight() * Math.random();
		}
	}
	
	
	
	
	public void randColor() {
		color = new Color(((int)(Math.random() * 220) + 35),((int)(Math.random() * 220) + 35),((int)(Math.random() * 220) + 35));
	}
	
	
	
	
	public void add(double _x, double _y) {
		dots.add(new Dot(_x, _y));
		makeT();
	}
	
	
	
	
	private void delete(int _i) {
		if (size() <= 2) return;
		dots.remove(_i);
		makeT();
	}
	
	public void deleteSelected() {
		delete(selected);
	}
	
	
	
	
	protected Dot get(int _i) {
		return dots.get(_i);
	}
	
	private int size() {
		return dots.size();
	}
	
	
	
	
	public synchronized void update() {
		
	}
	
	
	

	public void paint()
	{
		Canvas.setColor(color);
		
		for (int i = 1; i < size(); i++) {
			Canvas.drawLine(get(i-1).x, get(i-1).y, get(i).x, get(i).y);
		}
		
		for (int i = 0; i < size(); i++) {
			Canvas.drawOval((get(i).x - r), (get(i).y - r), (r + r), (r + r));
		}
	}
	
	
	
	
	public int select(int _x, int _y) {
		
		for (int i = 0; i < size(); i++) {
			if (_x > dots.get(i).x - r &&
				_x < dots.get(i).x + r &&
				_y > dots.get(i).y - r &&
				_y < dots.get(i).y + r ) {
				
				selected = i;
				return i;
			}
		}
		return -1;
	}

	
		
	
	public void dragSelected(int _x, int _y) {
		if (mode == 2){
			get(selected).x = _x;
			get(selected).y = _y;
		}
	}
	
	
	
	public void setMode(int _mode) {
		mode = _mode;
	}
	
	
	public int getMode() {
		return mode;
	}

	public void keyPressed(KeyEvent _keyEvent)
	{
		if (_keyEvent.getKeyCode() == KeyEvent.VK_R) madMake();
		else if (_keyEvent.getKeyCode() == KeyEvent.VK_C) randColor();
		else if (_keyEvent.getKeyCode() == KeyEvent.VK_D) setMode(-1);
		else if (_keyEvent.getKeyCode() == KeyEvent.VK_A) setMode(1);
	}

	public void keyReleased(KeyEvent _keyEvent)
	{
		setMode(0);
	}

	public void keyTyped(KeyEvent _keyEvent) {}

	public void mouseClicked(MouseEvent _mouseEvent) {}

	public void mouseEntered(MouseEvent _mouseEvent) {}

	public void mouseExited(MouseEvent _mouseEvent) {}

	public void mousePressed(MouseEvent _mouseEvent)
	{
		if (getMode() == -1) { if (select(_mouseEvent.getX(), _mouseEvent.getY()) != -1) deleteSelected(); }
		else {
			if (getMode() == 1) add(_mouseEvent.getX(), _mouseEvent.getY());
			else if (select(_mouseEvent.getX(), _mouseEvent.getY()) != -1) setMode(2);
		}
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		setMode(0);
	}

	public void mouseDragged(MouseEvent _e)
	{
		if (getMode() == 2) dragSelected(_e.getX(), _e.getY());
	}

	public void mouseMoved(MouseEvent _mouseEvent) {}
	
	

}
