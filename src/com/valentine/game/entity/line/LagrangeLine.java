package com.valentine.game.entity.line;

import com.valentine.game.entity.Container;
import com.valentine.game.utils.Screen;

public class LagrangeLine extends Line {
	
	public LagrangeLine(Container _box, int _n, double _r)
	{
		super(_box, _n, _r);
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

	public void paint() {		
		
		Screen.setColor(color);
		
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
			
			Screen.drawLine(x_old, y_old, x, y);
			
			x_old = x;
			y_old = y;
		}
		
		for (int i = 0; i < dots.size(); i++) {
			Screen.drawOval((get(i).x - r), (get(i).y - r), (2 * r), (2 * r));
			Screen.drawString(i + "", (get(i).x + r), (get(i).y - r));
		}
		
	}

}