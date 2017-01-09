package com.valentine.game.utils.math.function;

import java.util.function.*;

public class FunctionTrimmer extends FunctionReference
{
	private double ymin;
	private double ymax;
	
	public FunctionTrimmer(DoubleFunction<Double> _function, double _ymin, double _ymax)
	{
		super(_function);
		setLimits(_ymin, _ymax);
	}

	public Double apply(double _value)
	{
		Double y = super.apply(_value);
		if (y == null)
			return null;
		else
			return trim(y);
	}
	
	public double trim(double _y)
	{
		return _y <= ymax ? (_y >= ymin ? _y : ymin) : ymax;
	}
	
	public void setLimits(double _ymin, double _ymax)
	{
		setYmin(_ymin);
		setYmax(_ymax);
	}
	
	public double getYmin()
	{
		return ymin;
	}

	public void setYmin(double _ymin)
	{
		ymin = _ymin;
		if (ymin > ymax) ymax = ymin;
	}

	public double getYmax()
	{
		return ymax;
	}

	public void setYmax(double _ymax)
	{
		ymax = _ymax;
		if (ymin > ymax) ymin = ymax;
	}
}
