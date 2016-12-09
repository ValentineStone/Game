package com.valentine.game.entity.winter;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class SnowFlake extends EntityBasicAI
{
	private final double[][] lines;
	
	private final SnowHeapManager snowHeapManager;
	
	public SnowFlake(Container _container, SnowHeapManager _snowHeapManager, double _x, double _y)
	{
		super(_container);
		
		snowHeapManager = _snowHeapManager;
		
		setPosition(_x, _y);
		
		setDrawColor(ColorExt.randomColor(240, 255));
		
		double width = MathExt.random(3, 10);
		
		int lineCount = (int) MathExt.random(width, 15);
		
		setSize(width, width);
		
		setVelocity(20/width);
		
		lines = new double[lineCount][4];
		
		for (double[] line : lines)
		{
			line[0] = MathExt.random(getWidth());
			line[1] = MathExt.random(getHeight());
			line[2] = MathExt.random(getWidth());
			line[3] = MathExt.random(getHeight());
		}
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());
		
		_screen.localize(getX(), getY());
		
		for (double[] line : lines)
		{
			_screen.drawLine(line[0], line[1], line[2], line[3]);
		}
		
		_screen.delocalize(getX(), getY());
	}

	public void update()
	{
		fall();
	}
	
	public void fall()
	{
		if (getY() + getHeight() < getContainer().getHeight() - snowHeapManager.getHeightAt((int) getCenterX()))
		{
			setY(getY() + getVelocity());
		}
		else
		{
			snowHeapManager.rase(this);
			
			setUpdatable(false);
		}
	}
}
