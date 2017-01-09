package com.valentine.game.utils.math.geom;

public class LineCommon2d extends Line2d
{
	private double A = 0;
	private double B = 0;
	private double C = 0;
	
	public LineCommon2d(double _A, double _B, double _C)
	{
		setA(_A);
		setB(_B);
		setC(_C);
	}
	
	public double getA()
	{
		return A;
	}

	public void setA(double _a)
	{
		A = _a;
	}

	public double getB()
	{
		return B;
	}

	public void setB(double _b)
	{
		B = _b;
	}
	
	public double getC()
	{
		return C;
	}
	
	public void setC(double _c)
	{
		C = _c;
	}
	
	

	public static LineCommon2d toLine(Dot2d _d1, Dot2d _d2)
	{
		if (_d1 == null || _d2 == null || _d1.equals(_d2))
			return null;
		
		return new LineCommon2d(0,0,0);
	}
	
	
	
	public static LineCommon2d perpendicular(Line2d _l)
	{
		return new LineCommon2d(-_l.getB(), _l.getA(), _l.getC());
	}
	
	public static LineCommon2d perpendicularAt(Line2d _l, Dot2d _dot)
	{
		double A = -_l.getB();
		double B =  _l.getA();
		double C = -(A * _dot.getX() + B * _dot.getY());
		
		return new LineCommon2d(A, B, C);
	}

}
