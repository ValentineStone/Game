package com.valentine.game.entity.vfx.line;

import java.util.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class FiniteCubicLine extends Line
{	
	private double[][] A = new double[0][0];
	private double[] b = new double[0];
	
	private boolean changed = true;
	
	public FiniteCubicLine(Container _container, int _amountOfDots, double _dotRadius)
	{
		super(_container, _amountOfDots, _dotRadius);
	}
	
	@Override
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
	
	@Override
	public void paint(Screen _screen)
	{
		paintDots(_screen);
		
		double x1 = dots.get(0).x;
		double y1 = dots.get(0).y;
		
		for (int i = 1; i < dots.size(); i++)
		{
			double distance = dots.get(i).x - dots.get(i-1).x;
			for (double y2, x2 = dots.get(i-1).x; x2 < dots.get(i).x; x2 += 0.01)
			{
				y2 = dots.get(i-1).y * (dots.get(i).x - x2)/distance + dots.get(i).y * (x2 - dots.get(i-1).x)/distance;
				_screen.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
		}
	}
	
	private void remakeMatrix()
	{
		
		
		
		
		refillMatrix();
		
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
			
			debugLine();
		}
	}
	
	private void refillMatrix()
	{
		if (A.length != dots.size())
		{
			A = new double[dots.size()][dots.size()*4];
			b = new double[dots.size()];
		}
		
		for (int i = 0; i < A.length; i++)
		{
			b[i] = dots.get(i).y;
			Arrays.fill(A[i], 0);
		}
		
		for (int j, i = 0; i < A.length; i++)
		{
			j = 4*i;
			double x = dots.get(i).x;
			double x2 = x * dots.get(i).x;
			
			try
			{
				A[i][j+0] = x2*x + 3*x2 + 6*x;
				A[i][j+1] = x2 + 2*x + 2;
				A[i][j+2] = x + 1;
				A[i][j+3] = 1;
			}
			catch (Exception _exc)
			{
				_exc.printStackTrace();
			}
			
			try
			{
				A[i][j-4] = -A[i][j+0];
				A[i][j-3] = -A[i][j+1];
				A[i][j-2] = -A[i][j+2];
				A[i][j-1] = -A[i][j+3];
			}
			catch (Exception _exc)
			{
				_exc.printStackTrace();
			}
		}
		
		debugLine();
		
		A[0][0] += 6*dots.get(0).x;
		A[0][1] += 2;
		//System.err.println("A[" + (dots.size()-1) + "][" + (4*(dots.size())-1) + "]");
		A[dots.size()-1][4*(dots.size())-4] += 6*dots.get(dots.size()-1).x;
		A[dots.size()-1][4*(dots.size())-3] += 2;
		
		debugLine();
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
