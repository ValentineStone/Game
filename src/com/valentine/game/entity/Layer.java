package com.valentine.game.entity;

import java.util.LinkedList;

import com.valentine.game.utils.*;

public abstract class Layer extends Container implements Entity
{
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	
	LinkedList<Entity> entities = new LinkedList<Entity>();
	
	public Layer(double _x, double _y, double _width, double _height)
	{
		x = _x;
		y = _y;
		width = _width;
		height = _height;
	}
	
	public void paint()
	{
		Canvas.localize(this);
		
		super.paint();

		Canvas.delocalize(this);
		
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
