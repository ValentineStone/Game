package com.valentine.game.entity.ui;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.math.*;

public class GGraph extends EntityBasicAI
{
	public FunctionRanged function;
	public double step = 0.01;
	public 
	
	public GGraph(Container _container, double _x, double _y, double _width, double _height, FunctionRanged _function)
	{
		super(_container);
		setPosition(_x, _y);
		setSize(_width, _height);
		
		function = _function;
	}

	public void paint(Screen _screen)
	{}

	public void update()
	{}

}
