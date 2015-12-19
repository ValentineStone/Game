package com.valentine.game.entity;

public class Box extends Layer implements Entity
{
	protected double width;
	protected double height;
	
	public Box(double _x, double _y, double _width, double _height)
	{
		super(_x, _y);
		setWidth(_width);
		setHeight(_height);
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
