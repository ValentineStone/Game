package com.valentine.game.gameworld.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.valentine.game.Game;
import com.valentine.game.gameworld.Entity;

public class Circle implements Entity {
	public double x;
	public double y;
	public double r;
	public double dx;
	public double dy;
	public Color color;
	
	public Circle() {
		x = Math.random() * Game.myGameWorld.getDimension().width;
		y = Math.random() * Game.myGameWorld.getDimension().height;
		r = Math.random() * 5 + 3;
		dx = (Math.random() > 0.5 ? -1 : 1) * ((Math.random() * 4) + 3);
		dy = (Math.random() > 0.5 ? -1 : 1) * ((Math.random() * 4) + 3);
		color = new Color(((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0));
	}

	
	public void update() {
		x += dx;
		y += dy;
		
		if (x+r > Game.myGameWorld.getDimension().width) {
			x = Game.myGameWorld.getDimension().width - r;
			dx = -dx;
		}
		
		if (x-r < 0) {
			x = r;
			dx = -dx;
		}
		
		if (y+r > Game.myGameWorld.getDimension().height) {
			y = Game.myGameWorld.getDimension().height - r;
			dy = -dy;
		}
		
		if (y-r < 0) {
			y = r;
			dy = -dy;
		}
	}

	public void paint(Graphics _graphics) {
		_graphics.setColor(color);
		_graphics.drawOval((int)(x + dx * Game.myGameWorld.getInterpolation() + .5 - r), (int)(y + dy * Game.myGameWorld.getInterpolation() + .5 - r), (int)r*2, (int)r*2);
	}

}
