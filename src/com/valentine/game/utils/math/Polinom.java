package com.valentine.game.utils.math;

import java.util.function.*;

public class Polinom implements DoubleUnaryOperator, DoubleFunction<Double>
{
	double[] coefficients;
	
	public Polinom(double ... _coefficients)
	{
		coefficients = Matrix.copy(_coefficients);
	}

	public double applyAsDouble(double _x)
	{
		double result = 0;
		double xpow = 1;
		
		for (int i = 0; i < coefficients.length; i++)
		{
			result += xpow * coefficients[i];
			xpow *= _x;
		}
		return result;
	}

	public Double apply(double _value)
	{
		return applyAsDouble(_value);
	}
	
	public double[] getCoefficients()
	{
		return coefficients;
	}
}