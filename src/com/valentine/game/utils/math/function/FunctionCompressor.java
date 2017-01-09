package com.valentine.game.utils.math.function;

import java.util.function.*;

public class FunctionCompressor extends FunctionReference
{
	private double ymultiplier = 1;
	private double yfocus = 0;
	private double xmultiplier = 1;
	private double xfocus = 0;
	
	public FunctionCompressor()
	{
		super(null);
	}
	
	public FunctionCompressor(DoubleFunction<Double> _function, double _ymultiplier)
	{
		this(_function, _ymultiplier, 0);
	}
	
	public FunctionCompressor(DoubleFunction<Double> _function, double _ymultiplier, double _yfocus)
	{
		super(_function);
		setYLimits(_ymultiplier, _yfocus);
	}

	public Double apply(double _value)
	{
		Double y = super.apply((_value - xfocus) / xmultiplier);
		if (y == null)
			return null;
		else
			return compress(y);
	}
	
	private double compress(double _y)
	{
		return (_y - getYfocus()) * getYmultiplier() + getYfocus();
	}
	
	public void setYLimits(double _ymultiplier, double _yfocus)
	{
		setYmultiplier(_ymultiplier);
		setYfocus(_yfocus);
	}
	
	public double getYmultiplier()
	{
		return ymultiplier;
	}

	public void setYmultiplier(double _ymultiplier)
	{
		ymultiplier = _ymultiplier;
	}

	public double getYfocus()
	{
		return yfocus;
	}

	public void setYfocus(double _yfocus)
	{
		yfocus = _yfocus;
	}

	public double getXmultiplier()
	{
		return xmultiplier;
	}

	public void setXmultiplier(double _xmultiplier)
	{
		xmultiplier = _xmultiplier;
	}

	public double getXfocus()
	{
		return xfocus;
	}

	public void setXfocus(double _xfocus)
	{
		xfocus = _xfocus;
	}
}
