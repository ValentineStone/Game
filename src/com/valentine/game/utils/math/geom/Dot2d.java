package com.valentine.game.utils.math.geom;

public class Dot2d implements Cloneable
{
	public final double x;
	public final double y;
	
	public Dot2d(double _x, double _y)
	{
		x = _x;
		y = _y;
	}
	
	public Dot2d(Dot2d _d)
	{
		this(_d.x, _d.y);
	}
	
	public Dot2d clone()
	{
		return new Dot2d(this);
	}
	
	public boolean equals(Dot2d _d)
	{
		return x == _d.x && y == _d.y;
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
