package com.valentine.game.utils.math;

import java.util.*;

import com.valentine.game.utils.math.geom.*;

public final class Approximation
{
	private Approximation()
	{}
	
	@Deprecated
	public static Polinom approximate(Dot2d[] _dots, int _power)
	{
		double[] x = new double[_dots.length];
		double[] y = new double[_dots.length];
		
		for (int i = 0; i < _dots.length; i++)
		{
			x[i] = _dots[i].getX();
			y[i] = _dots[i].getY();
		}
		
		return approximate(x, y, _power);
	}
	
	public static Polinom approximate(double[] _x, double[] _y, int _power)
	{
		double[][] xpowers = new double[_x.length][];
		for (int i = 0; i < xpowers.length; i++)
			xpowers[i] = MathExt.getPowers(_x[i], 2*_power);
		
		double[] xpowersumms = new double[2*_power];
		for (int pow = 0; pow < xpowersumms.length; pow++)
			xpowersumms[pow] = Matrix.summCol(xpowers, pow);
		
		double[][] ypowers = new double[_y.length][];
		for (int i = 0; i < ypowers.length; i++)
		{
			ypowers[i] = new double[_power];
			Arrays.fill(ypowers[i], _y[i]);
			Matrix.multiply(ypowers[i], xpowers[i], false);
		}
		
		double[] ypowersumms = new double[_power];
		for (int pow = 0; pow < ypowersumms.length; pow++)
			ypowersumms[pow] = Matrix.summCol(ypowers, pow);
		
		double[][] matrix = new double[_power][_power + 1];
		
		for (int row = 0; row < _power; row++)
		{
			for (int col = 0; col < _power; col++)
			{
				matrix[row][col] = xpowersumms[row+col];
			}
			matrix[row][_power] = ypowersumms[row];
		}
		
		Matrix.solve(matrix, false);
		
		return new Polinom(Matrix.extractLast(matrix));
	}
}