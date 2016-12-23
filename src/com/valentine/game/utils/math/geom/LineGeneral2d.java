package com.valentine.game.utils.math.geom;

public class LineGeneral2d implements Line2dInterface
{
	public double A = 0;
	public double B = 0;
	public double C = 0;
	
	public LineGeneral2d(double _A, double _B, double _C)
	{
		A = _A;
		B = _B;
		C = _C;
	}
	
	public boolean isHorisontal()
	{
		return B == 0;
	}
	
	public boolean isVertical()
	{
		return A == 0;
	}
	
	public double yFromX(double _x)
	{
		return - (A * _x + C) / B;
	}
	
	public double xFromY(double _y)
	{
		return - (B * _y + C) / A;
	}
	
	
	
	
	
	
	
	
	
	
	public static LineGeneral2d perpendicular(LineGeneral2d _l)
	{
		return new LineGeneral2d(_l.B, _l.A, _l.C);
	}
	
	public static LineGeneral2d toLine(Dot2d _d1, Dot2d _d2)
	{
		if (_d1 == null || _d2 == null || _d1.equals(_d2))
			return null;
		
		return new LineGeneral2d(0,0,0);
	}

	public boolean isHorizontal()
	{
		return false;
	}

	public double getA()
	{
		return 0;
	}

	public double getB()
	{
		return 0;
	}

	public double getC()
	{
		return 0;
	}

	public double evalEquasion(Dot2d _d)
	{
		return 0;
	}

}
