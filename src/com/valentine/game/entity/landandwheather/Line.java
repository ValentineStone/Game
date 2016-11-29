package com.valentine.game.entity.landandwheather;

public class Line
{
	public Vector pos;
	public Vector size;
	
	
	public static boolean isAbove
	(
		double _dotX,
		double _dotY,
		double _lineX,
		double _lineY,
		double _lineDX,
		double _lineDY
	)
	{
		if ()
		
		return false;
	}
	
	public static boolean isBetween(double _a, double _da, double _x)
	{
		double min = Math.min(_a, _a + _da);
		double max = min + Math.abs(_da);
		return _x >= min && _x <= max;
	}
}
