package com.valentine.game.utils.math.function;

import java.util.function.*;

public class FunctionShifter extends FunctionReference
{
	private double yoffset = 0;
	private double xoffset = 0;
	
	public FunctionShifter(DoubleFunction<Double> _function, double _yoffset, double _xoffset)
	{
		super(_function);
		setYoffset(_yoffset);
		setXoffset(_xoffset);
	}
	
	public FunctionShifter()
	{
		this(null, 0, 0);
	}

	public Double apply(double _value)
	{
		Double y = super.apply(_value + getXoffset());
		if (y == null)
			return null;
		else
			return shift(y);
	}
	
	private double shift(double _y)
	{
		return _y + getYoffset();
	}
	
	public double getYoffset()
	{
		return yoffset;
	}

	public void setYoffset(double _yoffset)
	{
		yoffset = _yoffset;
	}

	public double getXoffset()
	{
		return xoffset;
	}

	public void setXoffset(double _xoffset)
	{
		xoffset = _xoffset;
	}
	
	
}
