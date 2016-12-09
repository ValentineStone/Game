package com.valentine.game.utils.math;

public class Matrix
{
	public double solve(double[][] a, double[] a)
	{
		
		double res =
			  a[0][0]*a[1][1]*a[2][2]
			+ a[0][1]*a[1][2]*a[2][0]
			+ a[0][2]*a[1][0]*a[2][1]
			- a[0][2]*a[1][1]*a[2][0]
			- a[0][0]*a[1][2]*a[2][1]
			- a[0][1]*a[1][0]*a[2][2];
		
		return 0;
	}
}
