package com.valentine.game.entity.ui;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class DragHandler extends Entity implements MouseListener, MouseMotionListener
{
	public enum Orientation
	{
		X, Y, XY
	}

	private EntityBasicAI entity;

	private boolean selected = false;

	private double offsetX = 0;
	private double offsetY = 0;

	private double x;
	private double y;

	private Orientation orientation;

	public DragHandler(Container _container, EntityBasicAI _entity, Orientation _orientation)
	{
		super(_container);

		Input.addMouseListener(this);
		Input.addMouseMotionListener(this);

		entity = _entity;
		appoint();

		orientation = _orientation;
	}

	public DragHandler(Container _container, EntityBasicAI _entity)
	{
		this(_container, _entity, Orientation.XY);
	}

	public void update()
	{
		if (selected)
		{
			entity.setActive(false);
			if (orientation != Orientation.Y)
			{
				entity.setX(x);
			}
			if (orientation != Orientation.X)
			{
				entity.setY(y);
			}
		}
	}

	public void paint(Screen _screen)
	{
		setPaintable(false);
	}

	public void appoint()
	{
		x = entity.getX();
		y = entity.getY();
	}

	public boolean kill(Entity _killer)
	{
		Input.removeMouseListener(this);
		Input.removeMouseMotionListener(this);
		return true;
	}

	public void mouseClicked(MouseEvent _mouseEvent)
	{}

	public void mouseEntered(MouseEvent _mouseEvent)
	{}

	public void mouseExited(MouseEvent _mouseEvent)
	{}

	public void mousePressed(MouseEvent _mouseEvent)
	{
		if (entity.isGettingHit(_mouseEvent.getX() - entity.getContainer().getTrueX(),
				_mouseEvent.getY() - entity.getContainer().getTrueY()))
		{
			selected = true;
			offsetX = _mouseEvent.getX() - entity.getTrueX();
			offsetY = _mouseEvent.getY() - entity.getTrueY();
		}
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		selected = false;
	}

	public void mouseDragged(MouseEvent _mouseEvent)
	{
		mouseMoved(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent)
	{
		if (selected)
		{
			x = _mouseEvent.getX() - entity.getContainer().getTrueX() - offsetX;
			y = _mouseEvent.getY() - entity.getContainer().getTrueY() - offsetY;
		}
	}

}
