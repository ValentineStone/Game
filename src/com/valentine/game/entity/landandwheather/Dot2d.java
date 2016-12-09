package com.valentine.game.entity.landandwheather;

public class Dot2d
{
	public double x = 0;
	public double y = 0;
	
	public Dot2d(double _x, double _y)
	{
		x = _x;
		y = _y;
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
