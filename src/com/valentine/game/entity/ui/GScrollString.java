package com.valentine.game.entity.ui;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class GScrollString extends GString implements MouseWheelListener//, MouseMotionListener
{
	private double scrollSpeed = 3;
	//private double autoScrollSpeed = 1;
	//private boolean mouseOver = false;
	
	public GScrollString(Container _container, String _text, double _x, double _y, double _width, double _height)
	{
		super(_container, _text, _x, _y, _width, _height);
		Input.addMouseWheelListener(this);
		//Input.addMouseMotionListener(this);
	}
	
	/*
	public void update()
	{
		if (mouseOver || textwidth < getWidth() - 2*charHeight)
			return;
		
		textx += autoScrollSpeed;
		
		if (textx > charHeight)
		{
			autoScrollSpeed *= -1;
			textx = charHeight;
		}
		else if (textx < getWidth() - textwidth - charHeight)
		{
			autoScrollSpeed *= -1;
			textx = getWidth() - textwidth - charHeight;
		}
	}
	*/
	
	protected void calcTextPos()
	{
		super.calcTextPos();
		
		if (textx < charHeight)
			textx = charHeight;
	}
	
	public boolean kill(Entity _killer)
	{
		Input.removeMouseWheelListener(this);
		//Input.removeMouseMotionListener(this);
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

	public void mouseWheelMoved(MouseWheelEvent _evt)
	{
		if (isGettingHit(_evt.getX() - getContainer().getTrueX(), _evt.getY() - getContainer().getTrueY()))
		{
			textx += _evt.getUnitsToScroll() * scrollSpeed;
		}
	}
	
	/*
	
	public void mouseDragged(MouseEvent _evt)
	{
		mouseMoved(_evt);
	}

	public void mouseMoved(MouseEvent _evt)
	{
		if (isGettingHit(_evt.getX() - getContainer().getTrueX(), _evt.getY() - getContainer().getTrueY()))
		{
			mouseOver = true;
		}
		else
		{
			mouseOver = false;
		}
	}
	
	*/

}
