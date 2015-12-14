package com.valentine.game.gameworld.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.valentine.game.gameworld.OldEntity;

public class MouseClick implements OldEntity {
	int x;
	int y;
	Color color;
	String text;
	boolean visibility;
	
	{
		color = Color.WHITE;
		visibility = false;
	}
	
	public void point(int _x, int _y) {
		x = _x;
		y = _y;
		text = "(" + _x + ", " + _y + ")";
		visibility = true;
	}

	public void update() {
		//nothing
	}

	public void paint(Graphics _graphics) {
		if (visibility == false) return;
		_graphics.setColor(Color.BLACK);
		_graphics.fillRect(x - 10 , y - 20, 90, 30);
		_graphics.setColor(color);
		_graphics.drawRect(x - 10 , y - 20, 90, 30);
		_graphics.drawString(text, x, y);
	}

}
