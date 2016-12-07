package com.valentine.game.entity.ui;

import java.awt.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

public class EntityInfoBox extends Entity
{
	public static double HEIGHT = 0;
	public static double WIDTH = 400;

	private Entity entity;

	private double dy = 0;

	public EntityInfoBox(Container _container, double _x, double _y, Entity _entity)
	{
		super(_container);
		setX(_x);
		setY(_y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setUpdatable(false);
		setFillColor(new Color(0, 0, 0, 100));
		setDrawColor(Color.CYAN);

		entity = _entity;
	}

	public void paint(Screen _screen)
	{
		if (dy == 0)
		{
			dy = _screen.getGraphics().getFontMetrics().getHeight() + 1;
		}

		HEIGHT = dy * 8;
		setHeight(HEIGHT);

		_screen.setColor(getFillColor());

		_screen.fillRect(getX(), getY(), getWidth(), getHeight());

		_screen.setColor(getDrawColor());

		_screen.drawString(" Type_____:_" + entity.getClass().getSimpleName(), getX(), getY() + 2 * dy);
		_screen.drawString(" ID_______:_" + entity.getId(), getX(), getY() + 3 * dy);
		_screen.drawString(" X________:_" + entity.getX(), getX(), getY() + 4 * dy);
		_screen.drawString(" Y________:_" + entity.getY(), getX(), getY() + 5 * dy);
		_screen.drawString(" Velocity_:_" + entity.getVelocity(), getX(), getY() + 6 * dy);
		_screen.drawString(" Rotation_:_" + entity.getRotation(), getX(), getY() + 7 * dy);

		_screen.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		setUpdatable(false);
	}

	public Entity getEntity()
	{
		return entity;
	}

	public void setEntity(Entity _entity)
	{
		entity = _entity;

		if (entity != null)
		{
			setDrawColor(entity.getDrawColor());
			setFillColor(ColorExt.makeTransparent(entity.getFillColor(), 30));
		}
	}

}
