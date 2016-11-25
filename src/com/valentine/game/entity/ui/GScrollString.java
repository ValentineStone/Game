package com.valentine.game.entity.ui;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class GScrollString extends GString implements MouseWheelListener
{
	private double scrollSpeed = 2;
	
	public GScrollString(Container _container, String _text, double _x, double _y, double _width, double _height)
	{
		super(_container, _text, _x, _y, _width, _height);
		Input.addMouseWheelListener(this);
	}

	public void mouseWheelMoved(MouseWheelEvent _evt)
	{
		if (isGettingHit(_evt.getX() - getContainer().getTrueX(), _evt.getY() - getContainer().getTrueY()))
		{
			textx -= _evt.getUnitsToScroll() * scrollSpeed;
		}
	}
	
	protected void calcTextPos()
	{
		super.calcTextPos();
		
		if (textx < charHeight)
			textx = charHeight;
	}
	
	public boolean kill(Entity _killer)
	{
		Input.removeMouseWheelListener(this);
		return super.kill(_killer);
	}

	public double getScrollSpeed()
	{
		return scrollSpeed;
	}

	public void setScrollSpeed(double _scrollSpeed)
	{
		scrollSpeed = _scrollSpeed;
	}
	
	

}
