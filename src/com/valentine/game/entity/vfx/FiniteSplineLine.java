package com.valentine.game.entity.vfx;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.vfx.line.*;
import com.valentine.game.utils.MathExt;

public class FiniteSplineLine extends Line
{
	public FiniteSplineLine(Container _container, int _amountOfDots, double _dotRadius)
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
				double done = (x2 - dots.get(i-1).x)/distance;
				double down = (Math.sin(MathExt.PI_1_1 * done + MathExt.PI_1_2) + 1) / 2.;
				double up   = (Math.sin(MathExt.PI_1_1 * done - MathExt.PI_1_2) + 1) / 2.;
				//System.err.println(down + " " + up);
				y2 = dots.get(i-1).y * down + dots.get(i).y * up;
				_screen.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
		}
	}
	
	public double pseudoSin(double _min, double _max, double _current)
	{
		return 1;
	}
}
