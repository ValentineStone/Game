package com.valentine.game.entity.ui;

import java.util.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.fuzzy.*;
import com.valentine.game.utils.math.geom.*;

public class FuzzyNumberGraph extends EntityBasicAI
{
	public static final double DEFAULT_X_STEP  = 1;
	public static final double DEFAULT_DOT_R   = 3;
	public static final double DEFAULT_PADDING = 10;
	
	private FuzzyNumber fn;
	
	private List<Dot2d> generatedDots;
	
	private double padding = DEFAULT_PADDING;
	
	public FuzzyNumberGraph(Container _container, double _x, double _y, double _width, double _height, FuzzyNumber _fn)
	{
		super(_container);
		
		setPosition(_x, _y);
		setSize(_width, _height);
		
		fn = _fn;
	}

	public void paint(Screen _screen)
	{
		_screen.localize(getX(), getY());
		_screen.setColor(getDrawColor());
		_screen.drawRect(0, 0, getWidth(), getHeight());
		_screen.drawLinesAndDots(generatedDots, DEFAULT_DOT_R);
		_screen.delocalize(getX(), getY());
	}

	public void update()
	{
		recalculate();
		setUpdatable(false);
	}
	
	public void recalculate()
	{
		double minx = fn.getMinX();
		double range = fn.getMaxX() - minx;
		double multiplier = getInnerWidth() / range;
		
		generatedDots = IterableExt.asArrayList(IterableExt.asIterable(fn, DEFAULT_X_STEP / multiplier));
		
		for (Dot2d dot : generatedDots)
		{
			if (dot == null) continue;
			dot.setX((dot.getX() - minx) * multiplier + getPadding());
			dot.setY(getHeight() - dot.getY() * getInnerHeight() - getPadding());
		}
	}
	
	public double getInnerHeight()
	{
		return getHeight() - 2 * getPadding();
	}
	
	public double getInnerWidth()
	{
		return getWidth() - 2 * getPadding();
	}
	
	public double getPadding()
	{
		return padding;
	}
	
	public void setPadding(double _padding)
	{
		padding = _padding;
	}
}
