package com.valentine.game.gameworld.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.valentine.game.Game;
import com.valentine.game.gameworld.Entity;

public class QuadroSpline implements Entity {
	Color color;
	double r;
	
	private class Dot {
		double x;
		double y;
		public Dot(double _x, double _y) {
			x = _x;
			y = _y;
		}
	}
	
	Dot selected;
	boolean isSelected;
	
	ArrayList<Dot> dots;
	
	{
		isSelected = false;
		r = 10;
		color = new Color(((int)(Math.random() * 220) + 35),((int)(Math.random() * 220) + 35),((int)(Math.random() * 220) + 35));
		dots = new ArrayList<Dot>();
		addDot((int)(Math.random() * 220), (int)(Math.random() * 220));
		int imax = 3;
		for (int i = 0; i < imax; i++) {
			addDot((int)(Math.random() * Game.myGameWorld.getDimension().width/(imax*2.) + Game.myGameWorld.getDimension().width/(imax*2.)) + dots.get(i).x, (int)(Math.random() * Game.myGameWorld.getDimension().height));
		}
	}
	
	public void addDot(double _x, double _y) {
		dots.add(new Dot(_x, _y));
	}
	
	
	public void update() {
		
	}

	public void paint(Graphics _graphics) {		
		
		_graphics.setColor(color);
		
		int i = 0;
		double x;
		double y;
		double x_old = dots.get(i).x;
		double y_old = dots.get(i).y;
		for (double t = 0, dt = 0.001; t <= 1+dt; t += dt) {
			
			x = Math.pow((1-t), 3) * dots.get(i).x + 3 * t * Math.pow((1-t), 2) * dots.get(i+1).x + 3 * Math.pow(t, 2) * (1-t) * dots.get(i+2).x + Math.pow(t, 3) * dots.get(i+3).x;
			y = Math.pow((1-t), 3) * dots.get(i).y + 3 * t * Math.pow((1-t), 2) * dots.get(i+1).y + 3 * Math.pow(t, 2) * (1-t) * dots.get(i+2).y + Math.pow(t, 3) * dots.get(i+3).y;
			
			_graphics.drawLine((int)x_old, (int)y_old, (int)x, (int)y);
			
			x_old = x;
			y_old = y;
		}
		
		for (int i1 = 0; i1 < dots.size(); i1++) {
			_graphics.drawOval((int)(dots.get(i1).x - r), (int)(dots.get(i1).y - r), (int)(2 * r), (int)(2 * r));
			_graphics.drawString(i1 + "", (int)(dots.get(i1).x + r), (int)(dots.get(i1).y - r));
		}
		
	}
	
	public void pointAt(int _x, int _y) {
		
		for (int i = 0; i < dots.size(); i++) {
			if (_x > dots.get(i).x - r &&
				_x < dots.get(i).x + r &&
				_y > dots.get(i).y - r &&
				_y < dots.get(i).y + r ) {
				
				selected = dots.get(i);
				isSelected = true;
				break;
			}
		}
	}
	
	public void deselect() {
		isSelected = false;
	}
	
	public void dragSelected(int _x, int _y) {
		if (isSelected == false) return;
		selected.x = _x;
		selected.y = _y;
	}
	
	
	// Old paint method
	/*
	public void paint(Graphics _graphics) {		
		_graphics.setColor(color);
		
		for (int i = 1; i < dots.size(); i++) {
			double full_dx = dots.get(i).x - dots.get(i-1).x;
			double full_dy = dots.get(i).y - dots.get(i-1).y;
			double dx = full_dx >= 0 ? 1 : -1;
			double dy = full_dy / Math.abs(full_dx);
			if (Math.abs(dx) > Math.abs(dy)) {
			}
			else {
				dx = dx/Math.abs(dy);
				dy = dy > 0 ? 1 : -1;
			}
			double fulld_dx_abs = Math.abs(full_dx);
			for (double y = 0, x = 0; Math.abs(x) < fulld_dx_abs; x += dx, y += dy) {
				_graphics.drawLine((int)(dots.get(i-1).x + x), (int)(dots.get(i-1).y + y), (int)(dots.get(i-1).x + x), (int)(dots.get(i-1).y + y));
			}
		}
	}	
	*/
}
