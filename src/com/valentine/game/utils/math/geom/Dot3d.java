package com.valentine.game.utils.math.geom;

public class Dot3d extends Dot2d
{
	public double z;
	
	public Dot3d(double _x, double _y, double _z)
	{
		super(_x, _y);
		z = _z;
	}
	
	public Dot3d(Dot3d _d)
	{
		this(_d.x, _d.y, _d.z);
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
