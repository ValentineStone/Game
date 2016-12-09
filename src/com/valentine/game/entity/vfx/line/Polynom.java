package com.valentine.game.entity.vfx.line;

public class Polynom
{
	public final int power;
	public final double[] coefficients;
	
	public Polynom(int _power, double... _coefficients)
	{
		power = _power;
		
		coefficients = new double[power];
		
		for (int i = 0; i < _coefficients.length; i++)
			coefficients[i] = _coefficients[i];
	}
	
	public double calulate(double _x)
	{
		double y = 0;
		
		for (int i = 0; i < coefficients.length; i++)
			y += coefficients[i] * Math.pow(_x, i);
		
		return y;
	}
}
