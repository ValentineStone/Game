package com.valentine.game.core;

public class Interpolation
{
	private static double interpolation = 1.;
	
	public static void set(long _paintTickNs, long _updateTickNs, long _updatePeriod)
	{				
		interpolation = (_paintTickNs - _updateTickNs) / (double)_updatePeriod;
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
