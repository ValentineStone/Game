package com.valentine.game.gameworld.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.valentine.game.*;
import com.valentine.game.gameworld.OldEntity;

public abstract class Line implements OldEntity {
	
	
	
	
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
	
	
	protected abstract void makeT();
	
	
	public Line(int _n, double _r) {		
		
		randColor();
		
		r = _r;
		
		mode = 0;
		
		selected = -1;
		
		dots = new ArrayList<Dot>();
		
		add((int)(Math.random() * 220), (int)(Math.random() * 220));
		for (int i = 0; i < _n; i++) {
			add((int)(Math.random() * GameWorld.getDimension().width/(_n*2.) + GameWorld.getDimension().width/(_n*2.)) + dots.get(i).x, (int)(Math.random() * GameWorld.getDimension().height));
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
			dot.x = GameWorld.getDimension().width * Math.random();
			dot.y = GameWorld.getDimension().height * Math.random();
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
	
	
	

	public void paint(Graphics _graphics) {
		_graphics.setColor(color);
		
		for (int i = 1; i < size(); i++) {
			_graphics.drawLine((int)get(i-1).x, (int)get(i-1).y, (int)get(i).x, (int)get(i).y);
		}
		
		for (int i = 0; i < size(); i++) {
			_graphics.drawOval((int)(get(i).x - r), (int)(get(i).y - r), (int)(r + r), (int)(r + r));
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
	
	

}
