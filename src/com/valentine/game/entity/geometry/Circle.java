package com.valentine.game.entity.geometry;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.EntityBasicAI;

public class Circle extends EntityBasicAI
{
	private double r;
	
	public Circle(Container _container, double _r)
	{
		super(_container);
		setR(_r);
		setPositionRandom();
	}

	public void paint()
	{
		Screen.setColor(getFillColor());
		Screen.fillOval(getX(), getY(), getWidth(), getHeight());
		Screen.setColor(getDrawColor());
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		setUpdatable(false);
	}

	public double getR()
	{
		return r;
	}

	public void setR(double _r)
	{
		r = _r;
		setSize(2*getR(), 2*getR());
	}
}
