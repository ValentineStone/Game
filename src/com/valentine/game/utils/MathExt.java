package com.valentine.game.utils;

public class MathExt
{
	public static final double PI_1_1 = 1 * Math.PI / 1;
	
	public static final double PI_2_1 = 2 * Math.PI / 1;
	public static final double PI_1_2 = 1 * Math.PI / 2;
	
	public static final double PI_3_2 = 3 * Math.PI / 2;
	public static final double PI_1_3 = 1 * Math.PI / 3;
	public static final double PI_2_3 = 2 * Math.PI / 3;
	public static final double PI_4_3 = 4 * Math.PI / 3;
	public static final double PI_5_3 = 5 * Math.PI / 3;
	
	public static final double PI_1_4 = 1 * Math.PI / 4;
	public static final double PI_3_4 = 3 * Math.PI / 4;
	public static final double PI_5_4 = 5 * Math.PI / 4;
	public static final double PI_7_4 = 7 * Math.PI / 4;
	

	
	public static double random(double _min, double _max)
	{
		return Math.random() * (_max - _min) + _min;
	}
	
	public static double random(double _value)
	{
		return Math.random() * _value;
	}
	
	public static double randomSigned(double _value)
	{
		return (Math.random() > 0.5 ? 1 : -1) *Math.random() * _value;
	}
	
	
	public static double rotationFlipX(double _rotation)
	{
		return PI_2_1 - _rotation;
	}
	
	public static double rotationFlipY(double _rotation)
	{
		return Math.PI - _rotation;
	}
	
	public static double rotationFlip(double _rotation)
	{
		return _rotation + Math.PI;
	}
	
	
	public static double rotationNormalize(double _rotation)
	{
		if (_rotation > PI_2_1)
		{
			_rotation -= (int)(_rotation/(PI_2_1)) * PI_2_1;
		}
		else if (_rotation < 0)
		{
			_rotation -= ((int)(_rotation/(PI_2_1) - 1)) * PI_2_1;
		}
		return _rotation;
	}
	
	public static double rotationMake(double _x, double _y)
	{		
		double lenght = Math.sqrt(_x*_x + _y*_y);
		
		if (lenght == 0) return 0;
		
		_x /= lenght;
		_y /= lenght;
		
		if (_x > 0)
		{
			return MathExt.rotationNormalize(Math.asin(_y));
		}
		else
		{
			return MathExt.rotationNormalize(rotationFlipY(Math.asin(_y)));
		}
	}
	
	
	
	public static enum DIRECTION
	{
		EAST 		( 0      ),
		NORTHEAST 	( PI_1_4 ),
		NORTH 		( PI_1_2 ),
		NORTHWEST 	( PI_3_4 ),
		WEST 		( PI_1_1 ),
		SOUTHWEST 	( PI_5_4 ),
		SOUTH 		( PI_3_2 ),
		SOUTHEAST 	( PI_7_4 );
		
		private final double rotation;
		
		DIRECTION(double _rotation)
		{
			rotation = _rotation;
		}
		
		public double getRotation()
		{
			return rotation;
		}
		
	}
	
	
}