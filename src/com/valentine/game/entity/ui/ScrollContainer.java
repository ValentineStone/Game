package com.valentine.game.entity.ui;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.valentine.game.core.Input;
import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;

public class ScrollContainer extends Container implements MouseWheelListener
{
	private double xScroll = 0;
	private double yScroll = 0;

	public ScrollContainer(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container, _x, _y, _width, _height);
		Input.addMouseWheelListener(this);
	}
	
	public void paint()
	{
		Screen.setColor(getFillColor());
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.localize(getX() + xScroll, getY() + yScroll);
		Screen.setClip(0, 0, getWidth(), getHeight());
		
		for (Entity entity : items)
		{
			if (entity.isPaintable()) entity.paint();
		}
		
		Screen.setClip(null);
		Screen.delocalize(getX() + xScroll, getY() + yScroll);
		
		Screen.setColor(getDrawColor());
		Screen.drawRect(getX(), getY(), getWidth()-1, getHeight()-1);
	}

	public void mouseWheelMoved(MouseWheelEvent _evt)
	{
		if (_evt.isAltDown() || _evt.isControlDown() || _evt.isShiftDown())
		{
			yScroll += _evt.getUnitsToScroll() * 3;
		}
		else
		{
			xScroll += _evt.getUnitsToScroll() * 3;
		}
		System.err.println(xScroll + " : " + yScroll);
	}
	
	public void kill()
	{
		Input.removeMouseWheelListener(this);
	}

}
