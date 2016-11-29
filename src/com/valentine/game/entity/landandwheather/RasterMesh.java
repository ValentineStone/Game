package com.valentine.game.entity.landandwheather;

import java.util.*;

public class RasterMesh
{
	private final Double[][] values;
	
	public RasterMesh(int _xCount, int _yCount)
	{
		values = new Double[_xCount][_yCount];
		
		interpolate();
	}
	
	private void load(Double[][] _values)
	{
		
	}
	
	private void interpolate()
	{
		
	}

	
	public static void main(String[] _args)
	{
		for (Double[] row : new RasterMesh(4, 5).values)
		{
			System.err.println(Arrays.toString(row));
		}
	}
}
