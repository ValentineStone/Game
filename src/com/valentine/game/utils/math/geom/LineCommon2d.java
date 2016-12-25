package com.valentine.game.utils.math.geom;

public class LineCommon2d extends Line2d
{
	public double A = 0;
	public double B = 0;
	public double C = 0;
	
	public LineCommon2d(double _A, double _B, double _C)
	{
		A = _A;
		B = _B;
		C = _C;
	}


	
	
	
	
	public double getA()
	{
		return A;
	}

	public double getB()
	{
		return B;
	}

	public double getC()
	{
		return C;
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
		double C = -(A * _dot.x + B * _dot.y);
		
		return new LineCommon2d(A, B, C);
	}

}
