package com.valentine.game.gameworld.entity;

import java.awt.Graphics;

public class LagrangeLine extends Line {
	
	public LagrangeLine(int _n, double _r) {
		super(_n, _r);
	}
	
	public LagrangeLine(Line _line) {
		super(_line);
	}

	double dt;
	
	protected void makeT() {
		int n = dots.size();
		dt = 1/(n-1.);
		for (int i = 0; i < n; i++) {
			get(i).t = dt * i;
		}
	}
	
	public void update() {
		
	}

	public void paint(Graphics _graphics) {		
		
		_graphics.setColor(color);
		
		int n = dots.size();
		
		double x;
		double y;
		double omega;
		
		double x_old = get(0).x;
		double y_old = get(0).y;
		
		for (double t = 0, dt = 0.001; t <= 1+dt; t += dt) {
			
			x = 0;
			y = 0;
			
			for (int i = 0; i < n; i++) {
				omega = 1;
				
				for (int j = 0; j < i; j++) {
					omega *= ((t - get(j).t)/(get(i).t - get(j).t));
				}
				for (int j = i+1; j < n; j++) {
					omega *= ((t - get(j).t)/(get(i).t - get(j).t));
				}
				
				x += omega * get(i).x;
				y += omega * get(i).y;
				
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