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
	


	public boolean equals(Object _o)
	{
		if (_o instanceof Dot2d)
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
				.append("Dot2d[")
				.append(x)
				.append(", ")
				.append(y)
				.append(']')
			.toString();
	}
}
