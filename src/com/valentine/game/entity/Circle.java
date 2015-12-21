package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.Screen;
import com.valentine.game.utils.Interpolation;

public class Circle extends Entity {
	public double r;
	public double dx;
	public double dy;
	public Color color;
	
	Container container;
	
	public Circle(Container _container)
	{
		container = _container;
		x = Math.random() * container.getWidth();
		y = Math.random() * container.getHeight();
		r = Math.random() * 5 + 3;
		dx = (Math.random() > 0.5 ? -1 : 1) * ((Math.random() * 6) + 1);
		dy = (Math.random() > 0.5 ? -1 : 1) * ((Math.random() * 6) + 1);
		color = new Color(((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0));
	}

	
	public void update()
	{
		x += dx;
		y += dy;
		
		if (x+r > container.getWidth())
		{
			x = container.getWidth() - r;
			dx = -dx;
		}
		
		if (x-r < 0)
		{
			x = r;
			dx = -dx;
		}
		
		if (y+r > container.getHeight())
		{
			y = container.getHeight() - r;
			dy = -dy;
		}
		
		if (y-r < 0)
		{
			y = r;
			dy = -dy;
		}
	}

	public void paint()
	{
		Screen.setColor(color);
		Screen.drawOval(Interpolation.make(x,dx) - r, Interpolation.make(y,dy) - r, r*2, r*2);
	}

}
