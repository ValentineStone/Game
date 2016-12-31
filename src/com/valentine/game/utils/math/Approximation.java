package com.valentine.game.utils.math;

import java.util.*;

public final class Approximation
{
	private Approximation()
	{}
	
	public static Function2d approximate(double[] _x, double[] _y, int _power)
	{
		double[][] xpowers = new double[2*_power][];
		
		for (int i = 0; i < xpowers.length; i++)
			xpowers[i] = MathExt.getPowers(_x[i], 2*_power);
		
		double[] xpowersumms = new double[_x.length];
		
		for (int pow = 0; pow < xpowersumms.length; pow++)
			xpowersumms[pow] = Matrix.summCol(xpowers, pow);
		
		double[][] ypowers = new double[_power][];
		
		for (int i = 0; i < ypowers.length; i++)
			ypowers[i] = Matrix.multiply(MathExt.getPowers(_x[i], 2*_power), _y, true);
		
		System.err.println(Matrix.toString(xpowers));
		System.err.println(Matrix.toString(ypowers));
		
		System.err.println(Arrays.toString(xpowersumms));
		
		double[][] matrix = new double[_power][_power + 1];
		
		for (int row = 0; row < _power; row++)
		{
			for (int col = 0; col < _power; col++)
			{
				System.err.print("(x^" + row*col + ")");
				matrix[row][col] = xpowersumms[row+col];
			}
			System.err.println();
		}
		
		System.err.println(Matrix.toString(matrix));
		
		return null;
	}
	
	public static void main(String[] _args)
	{
		double[] x = new double[]{1,  2,  3,  4,  5,  6,  7};
		double[] y = new double[]{1, -1,  1, -1,  1, -1,  1};
		
		approximate(x, y, 3);
	}
}