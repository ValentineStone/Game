package com.valentine.game.entity.vfx.line;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class PolynomLine extends Line
{
	private double[][] A = new double[0][0];
	private double[] b = new double[0];

	private double xMin = 0;
	private double xMax = 0;

	public PolynomLine(Container _container, int _amountOfDots, double _dotRadius)
	{
		super(_container, _amountOfDots, _dotRadius);
	}

	public void update()
	{
		super.update();

		if (A.length != dots.size())
		{
			A = new double[dots.size()][dots.size()];
			b = new double[dots.size()];
		}

		for (int i = 0; i < A.length; i++)
		{
			b[i] = dots.get(i).y;
			A[i][0] = 1;
			A[i][1] = dots.get(i).x;
			for (int j = 2; j < A.length; j++)
			{
				A[i][j] = A[i][j - 1] * dots.get(i).x;
			}
		}

		//debugLine();

		for (int i = 0; i < A.length; i++)
		{
			// divide the line by the current element making it 1

			double element = A[i][i];
			for (int j = 0; j < A.length; j++)
			{
				A[i][j] = A[i][j] / element;
			}
			b[i] = b[i] / element;

			// subtract the current line from others with multiplier

			for (int j = 0; j < A.length; j++)
			{
				if (i == j)
				{
					continue;
				}

				double multiplier = A[j][i];
				for (int k = 0; k < A.length; k++)
				{
					A[j][k] -= A[i][k] * multiplier;
				}
				b[j] -= b[i] * multiplier;
			}

			xMin = getContainer().getWidth();
			xMax = 0;

			for (Dot dot : dots)
			{
				xMin = xMin > dot.x ? dot.x : xMin;
				xMax = xMax < dot.x ? dot.x : xMax;
			}
		}

		//debugLine();
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());

		double x1 = xMin;
		double x2 = x1;
		double y1 = calculateY(x1);
		double y2 = y1;

		for (x2 = xMin; x2 <= xMax; x2 += 0.01)
		{
			y2 = calculateY(x2);
			_screen.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}

		paintDots(_screen);
	}

	private double calculateY(double _x)
	{
		double y = b[0];
		double xPowered = 1;
		for (int i = 1; i < b.length; i++)
		{
			y += (xPowered *= _x) * b[i];
		}

		return y;
	}

	public void debugLine()
	{
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A.length; j++)
			{
				System.err.print(A[i][j] + " ");
			}
			System.err.println(" | " + b[i]);
		}
		System.err.println("==============================================================================");
	}
}
