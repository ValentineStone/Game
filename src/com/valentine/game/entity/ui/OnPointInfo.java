package com.valentine.game.entity.ui;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class OnPointInfo extends EntityInfoBox implements MouseMotionListener
{
	Entity target = null;
	
	double mouseX;
	double mouseY;

	public OnPointInfo(Container _container, double _x, double _y)
	{
		super(_container, _x, _y, null);
		setUpdatable(true);
		Input.addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Screen _screen)
	{
		if (target == null || getEntity() == null)
		{		
			setEntity(getContainer());
		}
		super.paint(_screen);
	}
	
	@Override
	public void update()
	{
		boolean found = false;
		for (Entity entity : getContainer())
		{
			if (entity instanceof EntityBasicAI)
			{
				if (((EntityBasicAI)entity).isGettingHit(mouseX, mouseY))
				{
					target = entity;
					found = true;
					break;
				}
			}
		}
		if (!found) target = null;
		setEntity(target);
	}

	@Override
	public void mouseMoved(MouseEvent _mouseEvent)
	{
		mouseX = _mouseEvent.getX() - getContainer().getTrueX();
		mouseY = _mouseEvent.getY() - getContainer().getTrueY();
	}
	
	@Override
	public void mouseDragged(MouseEvent _mouseEvent) {}
	

}
