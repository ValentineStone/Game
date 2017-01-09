package com.valentine.game.utils.math.range;

import java.util.*;

public class PointRange implements Range
{
	private double point;
	
	public PointRange(double _point)
	{
		point = _point;
	}
	
	public double getPoint()
	{
		return point;
	}
	
	public void setPoint(double _point)
	{
		point = _point;
	}

	public double getLow()
	{
		return point;
	}

	public double getHigh()
	{
		return point;
	}
	
	public boolean test(double _x)
	{
		return _x == point;
	}

	public boolean isInfinite()
	{
		return Double.isInfinite(point);
	}
	
	public Iterator<Double> iterator(double _step)
	{
		if (isInfinite())
			return Collections.emptyIterator();
		else
			return new Iterator<Double>()
			{
				boolean quitFlag = false;
				
				public Double next()
				{
					return point;
				}
				
				public boolean hasNext()
				{
					return quitFlag = !quitFlag;
				}
			};
	}

	public String toString()
	{
		return "PointRange[" + point + "]";
	}
}