package com.valentine.game.utils.math.geom;

import com.valentine.game.utils.math.*;

public class Circle2d extends Dot2d
{
	private double r;

	public Circle2d(Dot2d _center, double _r)
	{
		super(_center.getX(), _center.getY());
		setR(_r);
	}
	
	public Circle2d(double _x, double _y, double _r)
	{
		super(_x, _y);
		setR(_r);
	}
	
	public Circle2d(Circle2d _c)
	{
		this(_c.getX(), _c.getY(), _c.getR());
	}
	
	public double getR()
	{
		return r;
	}

	public void setR(double _r)
	{
		r = _r;
	}

	public String toString()
	{
		return
			new StringBuilder()
				.append("Circle2d[x:")
				.append(getX())
				.append(", y:")
				.append(getY())
				.append(", r:")
				.append(getR())
				.append(']')
			.toString();
	}
	
	
	
	
	
	
	
	
	
	public static boolean contains(Circle2d _c, Dot2d _d)
	{
		return MathExt.distanceMake(_c, _d) <= _c.getR();
	}
}