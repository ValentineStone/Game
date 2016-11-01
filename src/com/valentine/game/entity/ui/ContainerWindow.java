package com.valentine.game.entity.ui;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;

public class ContainerWindow extends Container
{
	private Rect dragger;
	private DragHandler dragHandler;
	
	public ContainerWindow(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container, _x, _y, _width, _height);
		
		dragger = new Rect(getContainer());
		dragger.setSize(getWidth()-1, 30);
		dragger.setPosition(getX(), getY());
		
		dragHandler = new DragHandler(this, dragger);
		dragHandler.setUpdatable(false);
	}
	
	@Override
	public void update()
	{
		dragHandler.update();
		setPosition(dragger.getX(), dragger.getY());
		super.update();
	}
	
	

}
