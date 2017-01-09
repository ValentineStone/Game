package com.valentine.game.utils.math;

import java.util.*;

public class Matrix
{
	public static double determinant(double[][] _matrix)
	{ //method sig. takes a matrix (two dimensional array), returns determinant.
		double sum=0; 
		double s;
		if(_matrix.length==1)
		{	//bottom case of recursion. size 1 matrix determinant is itself.
			return(_matrix[0][0]);
		}
		for(int i=0;i<_matrix.length;i++)
		{ //finds determinant using row-by-row expansion
			double[][]smaller= new double[_matrix.length-1][_matrix.length-1]; //creates smaller matrix- values not in same row, column
			for(int a=1;a<_matrix.length;a++)
			{
				for(int b=0;b<_matrix.length;b++)
				{
					if(b<i){
						smaller[a-1][b]=_matrix[a][b];
					}
					else if(b>i){
						smaller[a-1][b-1]=_matrix[a][b];
					}
				}
			}
			if(i%2==0)
			{ //sign changes based on i
				s=1;
			}
			else
			{
				s=-1;
			}
			sum+=s*_matrix[0][i]*(determinant(smaller)); // recursive step: determinant of larger determined by smaller.
		}
		return(sum); //returns determinant value. once stack is finished, returns final determinant.
	}
	
	public static double[][] solve(double[][] _slau, boolean _copy)
	{
		double[][] slau = pipe(_slau, _copy);
		
		if (slau.length < 1 || slau[0].length < 2)
			return null;
		
		int rows = slau.length;
		//int cols = slau[0].length;
		
		for (int row = 0; row < rows; row++)
		{
			//System.err.println("----------------------------------------------------------");
			//System.err.println(Arrays.toString(slau[row]) + " / " + slau[row][row] + " =");
			divide(slau[row], slau[row][row], false);
			//System.err.println(Arrays.toString(slau[row]));
			
			for (int row2 = 0; row2 < rows; row2++)
			{
				if (row2 == row)
					continue;
				
				add(slau[row2], multiply(slau[row], -slau[row2][row], true), false);
				
				//System.err.println(row + " " + row2);
				//System.err.println(toString(slau));
			}
		}
		
		return slau;
	}
	
	public static double[] extract(double[][] _matrix, int _col)
	{
		double[] col = new double[_matrix.length];
		for (int i = 0; i < _matrix.length; i++)
			col[i] = _matrix[i][_col];
		return col;
	}
	
	public static double[] extractLast(double[][] _matrix)
	{
		return extract(_matrix, _matrix[0].length - 1);
	}
	
	public static double[] copy(double[] _row)
	{
		return Arrays.copyOf(_row,_row.length);
	}
	
	public static double[] pipe(double[] _row, boolean _copy)
	{
		if (_copy)
			return copy(_row);
		else
			return _row;
	}
	
	public static double[][] copy(double[][] _matrix)
	{
		double[][] matrix = new double[_matrix.length][];
		for (int i = 0; i < matrix.length; i++)
			matrix[i] = Arrays.copyOf(_matrix[i],_matrix[i].length);
		return matrix;
	}
	
	public static double[][] pipe(double[][] _matrix, boolean _copy)
	{
		if (_copy)
			return copy(_matrix);
		else
			return _matrix;
	}
	
	public static double[] multiply(double[] _row, double _mult, boolean _copy)
	{
		double[] row = pipe(_row, _copy);
		
		for (int i = 0; i < row.length; i++)
			row[i] *= _mult;
		
		return row;
	}
	
	public static double[] divide(double[] _row, double _div, boolean _copy)
	{
		double[] row = pipe(_row, _copy);
		
		for (int i = 0; i < row.length; i++)
			row[i] /= _div;
		
		return row;
	}
	
	public static double[] add(double[] _row1, double[] _row2, boolean _copy)
	{
		double[] row1 = pipe(_row1, _copy);
		
		for (int i = 0; i < row1.length; i++)
			row1[i] += _row2[i];
		
		return row1;
	}
	
	public static double[] multiply(double[] _row1, double[] _row2, boolean _copy)
	{
		double[] row1 = pipe(_row1, _copy);
		
		for (int i = 0; i < row1.length; i++)
			row1[i] *= _row2[i];
		
		return row1;
	}
	
	public static double summRow(double[][] _matrix, int _row)
	{
		double summ = 0;
		for (int i = 0; i < _matrix.length; i++)
			summ += _matrix[_row][i];
		return summ;
	}
	
	public static double summCol(double[][] _matrix, int _col)
	{
		double summ = 0;
		for (int i = 0; i < _matrix.length; i++)
			summ += _matrix[i][_col];
		return summ;
	}
	
	public static String toString(double[][] _matrix)
	{
		StringBuilder stringBuilder = new StringBuilder(Arrays.toString(_matrix[0]));
		for (int i = 1; i < _matrix.length; i++)
			stringBuilder
				.append('\n')
				.append(Arrays.toString(_matrix[i]));
		
		return stringBuilder.toString();
	}
}
