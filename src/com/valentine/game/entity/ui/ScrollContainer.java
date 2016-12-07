package com.valentine.game.entity.ui;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class ScrollContainer extends Container implements MouseWheelListener
{
	private double xScroll = 0;
	private double yScroll = 0;

	public ScrollContainer(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container, _x, _y, _width, _height);
		Input.addMouseWheelListener(this);
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRect(getX(), getY(), getWidth(), getHeight());

		_screen.localize(getX() + xScroll, getY() + yScroll);
		_screen.setClip(0, 0, getWidth(), getHeight());

		for (Entity entity : items)
		{
			if (entity.isPaintable())
			{
				entity.paint(_screen);
			}
		}

		_screen.setClip(null);
		_screen.delocalize(getX() + xScroll, getY() + yScroll);

		_screen.setColor(getDrawColor());
		_screen.drawRect(getX(), getY(), getWidth() - 1, getHeight() - 1);
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
