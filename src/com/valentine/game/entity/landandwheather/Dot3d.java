package com.valentine.game.entity.landandwheather;

public class Dot3d
{
	public double x = 0;
	public double y = 0;
	public double z = 0;
	
	public Dot3d(double _x, double _y, double _z)
	{
		x = _x;
		y = _y;
		z = _z;
	}
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("DOT[")
				.append(x)
				.append(", ")
				.append(y)
				.append(", ")
				.append(z)
				.append(']')
			.toString();
	}
}
