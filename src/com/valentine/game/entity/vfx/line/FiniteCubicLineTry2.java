package com.valentine.game.entity.vfx.line;

import java.util.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class FiniteCubicLineTry2 extends Line
{	
	private double[][] A = new double[0][0];
	private double[] b = new double[0];
	
	private boolean changed = true;
	
	public FiniteCubicLineTry2(Container _container, int _amountOfDots, double _dotRadius)
	{
		super(_container, _amountOfDots, _dotRadius);
	}
	
	public void update()
	{
		super.update();
		
		for (int i = 0; i < dots.size() - 1; i++)
		{
			for (int j = i + 1; j < dots.size(); j++)
			{
				if (dots.get(j).x < dots.get(i).x)
				{
					Dot tmp = dots.get(i);
					dots.set(i, dots.get(j));
					dots.set(j, tmp);
				}
			}
		}
		
		if (changed) remakeMatrix();
	}
	
	public void paint()
	{
		paintDots();
		
		double x1 = dots.get(0).x;
		double y1 = dots.get(0).y;
		
		for (int i = 1; i < dots.size(); i++)
		{
			double distance = dots.get(i).x - dots.get(i-1).x;
			for (double y2, x2 = dots.get(i-1).x; x2 < dots.get(i).x; x2 += 0.01)
			{
				y2 = dots.get(i-1).y * (dots.get(i).x - x2)/distance + dots.get(i).y * (x2 - dots.get(i-1).x)/distance;
				Screen.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
		}
	}
	
	private void remakeMatrix()
	{
		//debugLine();
		
		refillMatrix();
		debugLine();
		
		calculateMatrix();
		debugLine();
	}
	
	private void calculateMatrix()
	{
		for (int i = 0; i < A.length; i++)
		{
			// divide the line by the current element making it 1
			
			double element = A[i][i];
			for (int j = 0; j < A.length; j++)
				A[i][j] = A[i][j] / element;
			b[i] = b[i] / element;
			
			// subtract the current line from others with multiplier
			
			for (int j = 0; j < A.length; j++)
			{
				if (i == j) continue;
				
				double multiplier = A[j][i];
				for (int k = 0; k < A.length; k++)
				{
					A[j][k] -= A[i][k] * multiplier;
				}
				b[j] -= b[i] * multiplier;
			}
			
			//debugLine();
		}
	}
	
	private void refillMatrix()
	{
		if (A.length != dots.size()*4)
		{
			A = new double[dots.size()*4][dots.size()*4];
			b = new double[dots.size()*4];
		}
		
		for (int i = 0; i < dots.size(); i++)
		{
			int p = 4*i;
			
			b[p+0] = 0;
			b[p+1] = 0;
			b[p+2] = 0;
			b[p+3] = 2*dots.get(i).y;
			
			Arrays.fill(A[p+0], 0);
			Arrays.fill(A[p+1], 0);
			Arrays.fill(A[p+2], 0);
			Arrays.fill(A[p+3], 0);
		}
		
		for (int i = 0; i < dots.size(); i++)
		{
			int p = 4*i;
			
			double x = dots.get(i).x;
			double x2 = x * x;
			double x3 = x2 * x;
			
			// i-1
			try
			{
				A[p+0][p+0-4] = 6;
	
				A[p+1][p+0-4] = 6*x;
				A[p+1][p+1-4] = 2;
				
				A[p+2][p+0-4] = 3*x2;
				A[p+2][p+1-4] = 2*x;
				A[p+2][p+2-4] = 1;
				
				A[p+3][p+0-4] = x3;
				A[p+3][p+1-4] = x2;
				A[p+3][p+2-4] = x;
				A[p+3][p+3-4] = 1;
			}
			catch (Exception _exc) {}
			
			// i
			
			A[p+0][p+0] = -6;

			A[p+1][p+0] = -6*x;
			A[p+1][p+1] = -2;
			
			A[p+2][p+0] = -3*x2;
			A[p+2][p+1] = -2*x;
			A[p+2][p+2] = -1;
			
			A[p+3][p+0] = x3;
			A[p+3][p+1] = x2;
			A[p+3][p+2] = x;
			A[p+3][p+3] = 1;
		}
		
		/*
		
		A[0][0] = 3*dots.get(0).x*dots.get(0).x;
		A[0][1] = 2*dots.get(0).x;
		A[0][2] = 1;
		
		int k = 4*(dots.size())-1;
		int p = 4*(dots.size()-1);
		
		*/
		
		/*
		A[k][p+0] = 6*dots.get(0).x;
		A[k][p+1] = 2;
		b[k] = 0;
		*/
		
		
		//debugLine();
		
		/*
		
		A[0][0] += 6*dots.get(0).x;
		A[0][1] += 2;
		//System.err.println("A[" + (dots.size()-1) + "][" + (4*(dots.size())-1) + "]");
		A[dots.size()-1][4*(dots.size())-4] += 6*dots.get(dots.size()-1).x;
		A[dots.size()-1][4*(dots.size())-3] += 2;
		
		debugLine();
		
		*/
	}
	
	public void debugLine()
	{
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A[0].length; j++)
			{
				System.err.print(A[i][j] + " ");
			}
			System.err.println(" | " + b[i]);
		}
		System.err.println("==============================================================================");
	}
}
