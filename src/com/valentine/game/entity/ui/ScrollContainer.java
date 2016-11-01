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
	
	@Override
	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		_screen.localize(getX() + xScroll, getY() + yScroll);
		_screen.setClip(0, 0, getWidth(), getHeight());
		
		for (Entity entity : items)
		{
			if (entity.isPaintable()) entity.paint(null);
		}
		
		_screen.setClip(null);
		_screen.delocalize(getX() + xScroll, getY() + yScroll);
		
		_screen.setColor(getDrawColor());
		_screen.drawRect(getX(), getY(), getWidth()-1, getHeight()-1);
	}

	@Override
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
