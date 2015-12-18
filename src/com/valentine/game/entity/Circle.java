package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.Game;
import com.valentine.game.utils.Canvas;
import com.valentine.game.utils.Painter;

public class Circle implements Entity {
	public int id;
	public double x;
	public double y;
	public double r;
	public double dx;
	public double dy;
	public Color color;
	
	public Circle(int _id) {
		id = _id;
		x = Math.random() * Game.getDimension().width;
		y = Math.random() * Game.getDimension().height;
		r = Math.random() * 5 + 3;
		dx = (Math.random() > 0.5 ? -1 : 1) * ((Math.random() * 6) + 1);
		dy = (Math.random() > 0.5 ? -1 : 1) * ((Math.random() * 6) + 1);
		color = new Color(((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0));
	}

	
	public void update() {
		x += dx;
		y += dy;
		
		if (x+r > Game.getDimension().width) {
			x = Game.getDimension().width - r;
			dx = -dx;
		}
		
		if (x-r < 0) {
			x = r;
			dx = -dx;
		}
		
		if (y+r > Game.getDimension().height) {
			y = Game.getDimension().height - r;
			dy = -dy;
		}
		
		if (y-r < 0) {
			y = r;
			dy = -dy;
		}
	}

	public void paint() {
		Canvas.setColor(color);
		Canvas.drawOval(x + dx * Painter.getInterpolation() + .5 - r, y + dy * Painter.getInterpolation() + .5 - r, r*2, r*2);
	}

}
