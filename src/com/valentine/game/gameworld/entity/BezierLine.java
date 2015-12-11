package com.valentine.game.gameworld.entity;

import java.awt.Graphics;



public class BezierLine extends Line {

	
	public BezierLine(int _n, double _r) {
		super(_n, _r);
	}
	
	
	public BezierLine(Line _line) {
		super(_line);
	}
	
	private long factorial(int _n) {
		long factorial = 1;
		for (int i = 2; i <= _n; i++)
			factorial *= i;
		return factorial;
	}
	
	protected void makeT() {
		int n = dots.size();
		for (int i = 0; i < n; i++) {
			get(i).t = factorial(n-1) / (double)(factorial(i) * factorial(n-1-i));
		}
	}
	
	public void update() {
		
	}

	public void paint(Graphics _graphics) {		
		
		_graphics.setColor(color);
		
		int n = dots.size();
		
		double x;
		double y;
		double coeficent;
		
		double x_old = get(0).x;
		double y_old = get(0).y;
		
		for (double t = 0, dt = 0.001; t <= 1+dt; t += dt) {
			
			x = 0;
			y = 0;
			
			for (int i = 0; i < n; i++) {
				coeficent = get(i).t * Math.pow(t, i) * Math.pow((1-t), (n-1)-i);
				x +=  coeficent * get(i).x;
				y +=  coeficent * get(i).y;
			}
			
			_graphics.drawLine((int)x_old, (int)y_old, (int)x, (int)y);
			
			x_old = x;
			y_old = y;
		}
		
		
		for (int i = 0; i < dots.size(); i++) {
			_graphics.drawOval((int)(get(i).x - r), (int)(get(i).y - r), (int)(2 * r), (int)(2 * r));
			_graphics.drawString(i + "", (int)(get(i).x + r), (int)(get(i).y - r));
		}
		
	}
}