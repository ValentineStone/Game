package com.valentine.game.utils.math.geom;

public class LineFunc2d implements Geometry
{
	private double k;
	private double b;
	
	public LineFunc2d(double _k, double _b)
	{
		setK(_k);
		setB(_b);
	}
	
	public double getK()
	{
		return k;
	}

	public void setK(double _k)
	{
		k = _k;
	}

	public double getB()
	{
		return b;
	}

	public void setB(double _b)
	{
		b = _b;
	}

	public double yFromX(double _x)
	{
		return getK() * _x + getB();
	}
	
	public double xFromY(double _y)
	{
		return (_y - getB()) / getK();
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
				.append(getK())
				.append("x + ")
				.append(getB())
				.append(']')
			.toString();
	}
	
	
	
	
	
	
	public static Dot2d intersect(LineFunc2d _l1, LineFunc2d _l2)
	{	
		double x = (_l2.getB() - _l1.getB()) / (_l1.getK() - _l2.getK());
		return new Dot2d(x, _l1.yFromX(x));
	}
	
	public static LineFunc2d toLine(Dot2d _d1, Dot2d _d2)
	{
		double k = (_d1.getY() - _d2.getY()) / (_d1.getX() - _d2.getX());
		return new LineFunc2d(k, _d1.getY() - k * _d1.getX());
	}
	
	public static LineFunc2d toLine(Seg2d _s)
	{
		return toLine(_s.getD1(), _s.getD2());
	}
	
	public static LineFunc2d perpAtX(LineFunc2d _l, double _x)
	{
		double k = -1 / _l.getK();
		return new LineFunc2d(k, _l.yFromX(_x) - k * _x);
	}
	
	public static LineFunc2d perpAtY(LineFunc2d _l, double _y)
	{
		double k = -1 / _l.getK();
		return new LineFunc2d(k, _y - k * _l.xFromY(_y));
	}
}