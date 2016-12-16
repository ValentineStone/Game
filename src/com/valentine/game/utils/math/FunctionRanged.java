package com.valentine.game.utils.math;

public abstract class FunctionRanged extends Function2d
{
	public Range range;
	
	public FunctionRanged(Range _range)
	{
		range = _range;
	}

	/*
	public double evaluate(double _x)
	{
		if (range == null || !range.contains(_x))
			return 0;
		else
			return 
	}
	*/
}
