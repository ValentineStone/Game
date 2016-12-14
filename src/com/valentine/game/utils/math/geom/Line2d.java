package com.valentine.game.utils.math.geom;

public class Line2d
{
	public double A = 0;
	public double B = 0;
	public double C = 0;
	
	public Line2d(double _A, double _B, double _C)
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
	
	
	
	
	
	
	
	
	
	
	public static Line2d perpendicular(Line2d _l)
	{
		return new Line2d(_l.B, _l.A, _l.C);
	}

}
