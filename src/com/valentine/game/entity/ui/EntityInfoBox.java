package com.valentine.game.entity.ui;

import java.awt.Color;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.*;
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
		setFillColor(new Color(0,0,0,100));
		setDrawColor(Color.CYAN);
		
		entity = _entity;
	}
	
	public void paint()
	{
		if (dy == 0) dy = Screen.getGraphics().getFontMetrics().getHeight() + 1;
		
		HEIGHT = dy * 8;
		setHeight(HEIGHT);
		
		Screen.setColor(getFillColor());
		
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.setColor(getDrawColor());
		
		Screen.drawString(" Type_____:_" + entity.getClass().getSimpleName(), getX(), getY() + 2 * dy);
		Screen.drawString(" ID_______:_" + entity.getId(), getX(), getY() + 3 * dy);
		Screen.drawString(" X________:_" + entity.getX(), getX(), getY() + 4 * dy);
		Screen.drawString(" Y________:_" + entity.getY(), getX(), getY() + 5 * dy);
		Screen.drawString(" Velocity_:_" + entity.getVelocity(), getX(), getY() + 6 * dy);
		Screen.drawString(" Rotation_:_" + entity.getRotation(), getX(), getY() + 7 * dy);
		
		Screen.drawRect(getX(), getY(), getWidth(), getHeight());
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
