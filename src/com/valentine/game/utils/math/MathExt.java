package com.valentine.game.utils.math;

import com.valentine.game.utils.math.geom.*;

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

	public static final double PI_1_12 = 1 * Math.PI / 12;
	
	
	
	public static double flip(double _a)
	{
		return 1 / _a;
	}
	
	

	public static double random(double _min, double _max)
	{
		return Math.random() * (_max - _min) + _min;
	}

	public static double random(double _value)
	{
		return Math.random() * _value;
	}

	public static double randomSigned(double _min, double _max)
	{
		return (Math.random() > 0.5 ? 1 : -1) * (Math.random() * (_max - _min) + _min);
	}

	public static double randomSigned(double _value)
	{
		return (Math.random() > 0.5 ? 1 : -1) * Math.random() * _value;
	}

	public static boolean randomIf(double _trueProbablity)
	{
		return Math.random() < _trueProbablity ? true : false;
	}

	public static boolean randomIf()
	{
		return Math.random() < 0.5 ? true : false;
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
			_rotation -= (int) (_rotation / (PI_2_1)) * PI_2_1;
		}
		else if (_rotation < 0)
		{
			_rotation -= ((int) (_rotation / (PI_2_1) - 1)) * PI_2_1;
		}
		return _rotation;
	}
	
	public static double rotationMake(Dot2d _a, Dot2d _b)
	{
		return rotationMake(_a.getX(), _a.getY(), _b.getX(), _b.getY());
	}

	public static double rotationMake(double _x1, double _y1, double _x2, double _y2)
	{
		return rotationMake(_x2 - _x1, _y2 - _y1);
	}

	public static double rotationMake(double _x, double _y)
	{
		double lenght = Math.sqrt(_x * _x + _y * _y);

		if (lenght == 0)
		{
			return 0;
		}

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

	public static double rotationMakeX(double _rotation)
	{
		return Math.cos(_rotation);
	}

	public static double rotationMakeY(double _rotation)
	{
		return Math.sin(_rotation);
	}

	public static double distanceMake(double _x, double _y)
	{
		double distance = Math.sqrt(_x * _x + _y * _y);

		return distance;
	}

	public static double distanceMake(double _x1, double _y1, double _x2, double _y2)
	{
		return distanceMake(_x2 - _x1, _y2 - _y1);
	}
	
	public static double distanceMake(Dot2d _a, Dot2d _b)
	{
		return distanceMake(_a.getX(), _a.getY(), _b.getX(), _b.getY());
	}

	@Deprecated
	public static long factorial(int _n)
	{
		return factorial(1, _n);
	}
	
	@Deprecated
	public static long factorial(int _begindex, int _endindex)
	{
		if (_begindex < 0)
			return 0;
		
		if (_begindex == _endindex)
			return 1;
		
		long factorial = 1;
		for (int i = _begindex+1; i <= _endindex; i++)
			factorial *= i;
		
		return factorial;
	}
	
	
	public static double[] getPowers(double _x, int _k)
	{
		double[] powers = new double[_k + 1];
		
		powers[0] = 1;
		
		for (int i = 1; i < powers.length; i++)
			powers[i] = powers[i-1] * _x;
		
		return powers;
	}
	
	
	public static Dot2d makeVectorTowards(Dot2d _source, Dot2d _dest, double _radius)
	{
		double rotation = rotationMake(_source, _dest); 
		
		return makeVectorTowards(rotation, _radius);
	}
	
	public static Dot2d makeVectorTowards(double _rotation, double _radius)
	{
		return new Dot2d(rotationMakeX(_rotation) * _radius, rotationMakeY(_rotation) * _radius);
	}
	

	public static enum DIRECTION
	{
		EAST(0), NORTHEAST(PI_1_4), NORTH(PI_1_2), NORTHWEST(PI_3_4), WEST(PI_1_1), SOUTHWEST(PI_5_4), SOUTH(PI_3_2), SOUTHEAST(PI_7_4);

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
