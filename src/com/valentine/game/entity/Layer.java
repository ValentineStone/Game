package com.valentine.game.entity;

public class Layer extends Container implements Entity
{
	protected double x;
	protected double y;
	
	public Layer(double _x, double _y)
	{
		x = _x;
		y = _y;
	}
	
	public void paint()
	{
		//Canvas.localize(getX(), getY());
		
		super.paint();

		//Canvas.delocalize(getX(), getY());
		
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
	
}
