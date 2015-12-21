package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.*;

public class HelloWorld implements Entity {
	double x;
	double y;
	double height = 12;
	double width = 75;
	double dx;
	double dy;
	String text;
	Color color;
	Box box;
	
	public HelloWorld(Box _box) {
		box = _box;
		x = Math.random() * box.getWidth();
		y = Math.random() * box.getHeight();
		dx = (Math.random() > 0.5 ? -1 : 1) * ((Math.random()) + 1);
		dy = (Math.random() > 0.5 ? -1 : 1) * ((Math.random()) + 1);
		text = "Hello world!";
		color = 
				Math.random() > 0.05
				?
				new Color(((int)(Math.random() * 55) + 0),((int)(Math.random() * 55) + 0),((int)(Math.random() * 55) + 0))
				:
				new Color(((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0))
				;
	}

	public void update() {
		x += dx;
		y += dy;
		
		if (x + width > box.getWidth()) {
			x = box.getWidth() - width;
			dx = -dx;
		}
		
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		
		if (y > box.getHeight()) {
			y = box.getHeight();
			dy = -dy;
		}
		
		if (y - height< 0) {
			y = height;
			dy = -dy;
		}

	}

	public void paint() {
		Screen.setColor(color);
		Screen.drawString(text, Interpolation.make(x,dx), Interpolation.make(y,dy));
	}
}
