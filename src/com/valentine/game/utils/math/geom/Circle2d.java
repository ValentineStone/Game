package com.valentine.game.utils.math.geom;

public class Circle2d extends Dot2d
{
	public double r;

	public Circle2d(Dot2d _center, double _r)
	{
		super(_center.x, _center.y);
		r = _r;
	}
	
	public Circle2d(double _x, double _y, double _r)
	{
		super(_x, _y);
		r = _r;
	}
	
	public Circle2d(Circle2d _c)
	{
		this(_c.x, _c.y, _c.r);
	}
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Circle2d[x:")
				.append(x)
				.append(", y:")
				.append(y)
				.append(", r:")
				.append(r)
				.append(']')
			.toString();
	}
}