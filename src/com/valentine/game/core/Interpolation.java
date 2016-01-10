package com.valentine.game.core;

public class Interpolation
{
	private static double interpolation = 0.;
	
	public static void set(double _interpolation)
	{
		interpolation = _interpolation;
	}
	
	public static double get()
	{
		return interpolation;
	}
	
	public static double make(double _dvalue)
	{
		return get() * _dvalue;
	}
}
