package com.valentine.game.entity.vfx;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class FiniteStraightLine extends Line
{
	public FiniteStraightLine(Container _container, int _amountOfDots, double _dotRadius)
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
}
