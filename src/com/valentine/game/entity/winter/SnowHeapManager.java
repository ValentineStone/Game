package com.valentine.game.entity.winter;

public class SnowHeapManager
{
	private final double[] heights;
	
	public SnowHeapManager(int _width)
	{
		heights = new double[_width];
	}
	
	public double getHeightAt(int _pos)
	{
		if (_pos > 0 && _pos < heights.length)
			return heights[_pos];
		else
			return 0;
	}
	
	public void rase(SnowFlake _showFlake)
	{
		int pos    = (int) _showFlake.getX();
		int posmax = (int) _showFlake.getCornerX();
		
		for
		(	;
			pos < heights.length && pos <= posmax;
			pos++
		)
		{
			heights[pos] += _showFlake.getHeight() / 2;
		}
	}
	
	public void rase(double _height)
	{
		for (int i = 0; i < heights.length; i++)
			heights[i] += _height;
	}
}
