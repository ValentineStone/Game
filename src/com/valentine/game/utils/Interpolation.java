package com.valentine.game.utils;

public class Interpolation
{
	private static double interpolation = 1.;
	
	private static long updaterLastTickNs;
	private static long painterLastTickNs;
	
	public static synchronized void set()
	{
		updaterLastTickNs = Updater.getLastTickNs();
		painterLastTickNs = Painter.getLastTickNs();
		
		if (painterLastTickNs < updaterLastTickNs || (painterLastTickNs - updaterLastTickNs) > Updater.getPeriodNs()) return;
		
		interpolation = (painterLastTickNs - updaterLastTickNs) / (double)Updater.getPeriodNs();
	}
	
	public static double get()
	{
		return interpolation;
	}
	
	public static double make(double _value, double _dvalue)
	{
		return _value + get() * _dvalue;
	}
	
	public static double make(double _dvalue)
	{
		return get() * _dvalue;
	}
}
