package com.valentine.game.utils;

public abstract class MathFunction implements Function
{
	public Object evaluate(Object... _params)
	{
		return evaluate((Double[])_params);
	}
	
	public abstract Double evaluate(Double... _params);
}
