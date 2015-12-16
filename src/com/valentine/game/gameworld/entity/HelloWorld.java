package com.valentine.game.gameworld.entity;

import java.awt.*;
import com.valentine.game.*;
import com.valentine.game.gameworld.*;

public class HelloWorld implements Entity {
	double x;
	double y;
	double height = 12;
	double width = 75;
	double dx;
	double dy;
	String text;
	Color color;
	
	public HelloWorld() {
		x = Math.random() * GameWorld.getDimension().width;
		y = Math.random() * GameWorld.getDimension().height;
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
		
		if (x + width > GameWorld.getDimension().width) {
			x = GameWorld.getDimension().width - width;
			dx = -dx;
		}
		
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		
		if (y > GameWorld.getDimension().height) {
			y = GameWorld.getDimension().height;
			dy = -dy;
		}
		
		if (y - height< 0) {
			y = height;
			dy = -dy;
		}

	}

	public void paint(Graphics _graphics) {
		_graphics.setColor(color);
		_graphics.drawString(text, (int)(x + dx * GamePainter.getInterpolation() + .5), (int)(y + dy * GamePainter.getInterpolation() + .5));
	}
}
