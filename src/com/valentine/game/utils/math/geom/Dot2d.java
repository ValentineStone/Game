package com.valentine.game.utils.math.geom;

import java.util.Map.*;

public class Dot2d implements Geometry
{
	private double x;
	private double y;
	
	public Dot2d(double _x, double _y)
	{
		setX(_x);
		setY(_y);
	}
	
	public Dot2d(Dot2d _d)
	{
		this(_d.getX(), _d.getY());
	}
	
	public Dot2d(Entry<Double, Double> _entry)
	{
		this(_entry.getKey(), _entry.getValue());
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double _y)
	{
		y = _y;
	}

	public void setX(double _x)
	{
		x = _x;
	}

	public boolean equals(Object _o)
	{
		if (_o instanceof Dot2d)
		{
			Dot2d d = (Dot2d) _o;
			return getX() == d.getX() && getY() == d.getY();
		}
		else
			return false;
	}

	public boolean isZero()
	{
		return getX() == 0 && getY() == 0;
	}
	
	
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Dot2d[")
				.append(getX())
				.append(", ")
				.append(getY())
				.append(']')
			.toString();
	}
}
