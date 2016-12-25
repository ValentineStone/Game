package com.valentine.game.utils.math.geom;

public abstract class Line2d implements Geometry
{
	public abstract double getA();
	public abstract double getB();
	public abstract double getC();
	
	public boolean isHorisontal()
	{
		return getA() == 0;
	}
	
	public boolean isVertical()
	{
		return getB() == 0;
	}
	
	public double yFromX(double _x)
	{
		return - (getA() * _x + getC()) / getB();
	}
	
	public double xFromY(double _y)
	{
		return - (getB() * _y + getC()) / getA();
	}

	public double evalEquasion(Dot2d _d)
	{
		return getA() * _d.x + getB() * _d.y + getC();
	}
	
	
	
	
	public static Dot2d intersect(Line2d _l1, Line2d _l2)
	{
		if (_l1.getA() == 0)
		{
			if (_l2.getA() == 0)
				return null;
			else
				return intersect(_l2, _l1);
			
		}
		
		double y = (_l2.getB() - _l2.getA() * _l1.getB() / _l1.getA());
		
		if (y == 0)
			return null;
		
		y = (_l2.getA() * _l1.getC() / _l1.getA() - _l2.getC()) / y;
		
		double x = -(_l1.getB() * y + _l1.getC()) / _l1.getA();
		
		return new Dot2d(x, y);
	}
}