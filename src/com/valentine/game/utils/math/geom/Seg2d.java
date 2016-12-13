package com.valentine.game.utils.math.geom;

public class Seg2d
{
	public Dot2d a;
	public Dot2d b;
	
	public Seg2d(Dot2d _a, Dot2d _b)
	{
		a = _a;
		b = _b;
	}
	
	public Dot2d center()
	{
		return new Dot2d(centerX(), centerY());
	}
	
	public double centerX()
	{
		return a.x + (b.x - a.x) / 2;
	}
	
	public double centerY()
	{
		return a.y + (b.y - a.y) / 2;
	}
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Seg2d[a:")
				.append(a)
				.append(", b:")
				.append(b)
				.append(']')
			.toString();
	}
}
