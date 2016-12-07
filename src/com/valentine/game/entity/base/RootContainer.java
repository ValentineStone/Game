package com.valentine.game.entity.base;

import java.awt.Dimension;

public class RootContainer extends Container
{
	public RootContainer(Dimension _dimension)
	{
		this(_dimension.getWidth(), _dimension.getHeight());
	}
	
	public RootContainer(double _width, double _height)
	{
		super(null, 0, 0, _width, _height);
	}
}
