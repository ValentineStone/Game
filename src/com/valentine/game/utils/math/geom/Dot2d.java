package com.valentine.game.utils.math.geom;

public class Dot2d implements Geometry
{
	public double x;
	public double y;
	
	public Dot2d(double _x, double _y)
	{
		x = _x;
		y = _y;
	}
	
	public Dot2d(Dot2d _d)
	{
		this(_d.x, _d.y);
	}
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Dot2d[")
				.append(x)
				.append(", ")
				.append(y)
				.append(']')
			.toString();
	}
}
