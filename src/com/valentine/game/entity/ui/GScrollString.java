package com.valentine.game.entity.ui;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class GScrollString extends GString implements MouseWheelListener
{
	public GScrollString(Container _container, String _text, double _x, double _y, double _width, double _height)
	{
		super(_container, _text, _x, _y, _width, _height);
		Input.addMouseWheelListener(this);
	}

	public void mouseWheelMoved(MouseWheelEvent _evt)
	{
		if (isGettingHit(_evt.getX() - getContainer().getTrueX(), _evt.getY() - getContainer().getTrueY()))
		{
			textx -= _evt.getUnitsToScroll();
		}
	}
	
	public boolean kill(Entity _killer)
	{
		Input.removeMouseWheelListener(this);
		return super.kill(_killer);
	}

}
