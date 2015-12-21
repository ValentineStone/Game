package com.valentine.game.entity.line;

import com.valentine.game.entity.Box;
import com.valentine.game.utils.Screen;

public class BezierLine extends Line {
	
	public BezierLine(Box _box, int _n, double _r)
	{
		super(_box, _n, _r);
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

	public void paint() {		
		
		Screen.setColor(color);
		
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