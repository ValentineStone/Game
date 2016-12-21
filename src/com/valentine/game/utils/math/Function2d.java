package com.valentine.game.utils.math;

public abstract class Function2d extends MathFunction
{
	public Double evaluate(Double _param)
	{
		if (_param == null)
			return null;
		return evaluate(_param.doubleValue());
	}
	
	public Double evaluate(Double... _params)
	{
		if (_params == null || _params.length > 0)
			return null;
		return evaluate(_params[0]);
	}
	
	public abstract double evaluate(double _x);
}