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
	


	public boolean equals(Object _o)
	{
		if (_o instanceof Dot3d)
		{
			Dot3d d = (Dot3d) _o;
			return x == d.x && y == d.y && z == d.z;
		}
		else if (_o instanceof Dot2d)
		{
			Dot2d d = (Dot2d) _o;
			return x == d.x && y == d.y;
		}
		else
			return false;
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
