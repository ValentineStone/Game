package com.valentine.game.entity;

import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Container implements Entity
{
	protected double x = 7;
	protected double y = 7;
	protected double width = 100;
	protected double height = 100;
	
	LinkedList<Entity> entities = new LinkedList<Entity>();
	
	public Container()
	{
		make();
	}
	
	public abstract void make();
	
	public void paint(Graphics _graphics)
	{
		for (Entity entity : entities)
		{
			if (entity instanceof Container)
			{
				_graphics.translate((int)((Container)entity).getX(), (int)((Container)entity).getY());
				
				
				
				_graphics.translate(-(int)((Container)entity).getX(), -(int)((Container)entity).getY());
			}
		}
	}

	public void update() {

	}
	
	public double getX() {
		return x;
	}

	public void setX(double _x) {
		x = _x;
	}

	public double getY() {
		return y;
	}

	public void setY(double _y) {
		y = _y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double _width) {
		width = _width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double _height) {
		height = _height;
	}

}
