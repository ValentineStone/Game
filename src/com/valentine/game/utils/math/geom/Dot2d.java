package com.valentine.game.utils.math.geom;

public class Dot2d
{
	public double x = 0;
	public double y = 0;
	
	public Dot2d(double _x, double _y)
	{
		x = _x;
		y = _y;
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
