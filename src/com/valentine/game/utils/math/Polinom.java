package com.valentine.game.utils.math;

public class Polinom extends Function2d
{
	double[] coefficients;
	
	public Polinom(double[] _coefficients)
	{
		coefficients = Matrix.copy(_coefficients);
	}

	public double evaluate(double _x)
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
}
