package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class HelloWorld extends Entity
{
	double dx;
	double dy;
	String text;
	Color color;
	
	public HelloWorld(Container _container)
	{
		container = _container;
		x = Math.random() * container.getWidth();
		y = Math.random() * container.getHeight();
		height = 12;
		width = 75;
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
		
		if (x + width > container.getWidth()) {
			x = container.getWidth() - width;
			dx = -dx;
		}
		
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		
		if (y > container.getHeight()) {
			y = container.getHeight();
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
