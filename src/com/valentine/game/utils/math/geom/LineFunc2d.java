package com.valentine.game.utils.math.geom;

public class LineFunc2d implements Geometry
{
	public double k;
	public double b;
	
	public LineFunc2d(double _k, double _b)
	{
		k = _k;
		b = _b;
	}
	
	public double yFromX(double _x)
	{
		return k * _x + b;
	}
	
	public double xFromY(double _y)
	{
		return (_y - b) / k;
	}
	
	public Dot2d intersect(LineFunc2d _l)
	{
		return intersect(this, _l);
	}

	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Line2d[")
				.append(k)
				.append("x + ")
				.append(b)
				.append(']')
			.toString();
	}
	
	
	
	
	
	
	public static Dot2d intersect(LineFunc2d _l1, LineFunc2d _l2)
	{	
		double x = (_l2.b - _l1.b) / (_l1.k - _l2.k);
		return new Dot2d(x, _l1.yFromX(x));
	}
	
	public static LineFunc2d toLine(Dot2d _d1, Dot2d _d2)
	{
		double k = (_d1.y - _d2.y) / (_d1.x - _d2.x);
		return new LineFunc2d(k, _d1.y - k * _d1.x);
	}
	
	public static LineFunc2d toLine(Seg2d _s)
	{
		return toLine(_s.d1, _s.d2);
	}
	
	public static LineFunc2d perpAtX(LineFunc2d _l, double _x)
	{
		double k = -1 / _l.k;
		return new LineFunc2d(k, _l.yFromX(_x) - k * _x);
	}
	
	public static LineFunc2d perpAtY(LineFunc2d _l, double _y)
	{
		double k = -1 / _l.k;
		return new LineFunc2d(k, _y - k * _l.xFromY(_y));
	}
}