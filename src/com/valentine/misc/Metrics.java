package com.valentine.misc;

public final class Metrics
{
	private Metrics()
	{}
	
	public static double centimetersToMeters(double _centimeters)
	{
		return _centimeters / 100;
	}
	
	public static double squareCentimetersToSquarecMeters(double _squareCentimeters)
	{
		return _squareCentimeters / 10000;
	}
	
	public static double cubicCentimetersToCubicMeters(double _cubicCentimeters)
	{
		return _cubicCentimeters / 1000000;
	}
	
	
	public static double microseconsToSeconds(double _microsecons)
	{
		return _microsecons / 1000000;
	}
	
	public static double nanoseconsToSecons(double _nanosecons)
	{
		return _nanosecons / 1000000000;
	}
}
