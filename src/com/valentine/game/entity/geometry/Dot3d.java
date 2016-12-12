package com.valentine.game.entity.geometry;

public class Dot3d extends Dot2d
{
	public double z = 0;
	
	public Dot3d(double _x, double _y, double _z)
	{
		super(_x, _y);
		z = _z;
	}
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Dot3d[")
				.append(x)
				.append(", ")
				.append(y)
				.append(", ")
				.append(z)
				.append(']')
			.toString();
	}
}
