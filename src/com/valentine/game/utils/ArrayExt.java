package com.valentine.game.utils;

public final class ArrayExt
{
	private ArrayExt()
	{}
	
	public static double getFromSymmetricArray(double[][] _array, int _i, int _j)
	{
		if
		(
			   _array != null
			&& _array.length > 0
			&& _array[0] != null
		)
		{
			if (_i < _array.length && _j < _array[_i].length)
			{
				return _array[_i][_j];
			}
			if (_j < _array.length && _i < _array[_j].length)
			{
				return _array[_j][_i];
			}
		}
		throw new Error("Index out of bounds.");
	}
}
