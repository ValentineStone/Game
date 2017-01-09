package com.valentine.game.utils.math.function;

import java.util.function.*;

public class FunctionReference implements DoubleFunction<Double>
{
	private DoubleFunction<Double> function;
	
	public FunctionReference(DoubleFunction<Double> _function)
	{
		setFunction(_function);
	}

	public Double apply(double _value)
	{
		if (getFunction() == null)
			return null;
		return function.apply(_value);
	}

	public DoubleFunction<Double> getFunction()
	{
		return function;
	}

	public void setFunction(DoubleFunction<Double> _function)
	{
		function = _function;
	}
}
