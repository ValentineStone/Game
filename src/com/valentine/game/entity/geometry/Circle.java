package com.valentine.game.entity.geometry;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class Circle extends EntityBasicAI
{
	private double r;

	public Circle(Container _container, double _r)
	{
		super(_container);
		setR(_r);
		setPositionRandom();
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillOval(getX(), getY(), getWidth(), getHeight());
		_screen.setColor(getDrawColor());
		_screen.drawOval(getX(), getY(), getWidth(), getHeight());
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
		setSize(2 * getR(), 2 * getR());
	}
}
