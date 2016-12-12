package com.valentine.game.entity.vfx.line;

public class Polinom
{
	public final int power;
	public final double[] coefficients;
	
	public Polinom(int _power, double... _coefficients)
	{
		power = _power;
		
		coefficients = new double[power];
		
		for (int i = 0; i < _coefficients.length && i < power; i++)
			coefficients[i] = _coefficients[i];
	}
	
	public double calulate(double _x)
	{
		double y = 0;
		
		for (int i = 0; i < coefficients.length; i++)
			y += coefficients[i] * Math.pow(_x, i);
		
		return y;
	}
	
	public static void main(String ... _args)
	{
		System.err.println(new Polinom(4, new double[]{0,0,0,1}).calulate(-2));
	}
}
