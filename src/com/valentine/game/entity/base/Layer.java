package com.valentine.game.entity.base;

import com.valentine.game.core.Screen;

public class Layer extends Container {

	public Layer(Container _container, double _x, double _y)
	{
		super(_container, _x, _y, 0, 0);
	}
	
	public void paint()
	{
		
		Screen.localize(getX(), getY());
		
		for (Entity entity : items)
		{
			if (entity.isPaintable()) entity.paint();
		}
		
		Screen.delocalize(getX(), getY());
	}

}
