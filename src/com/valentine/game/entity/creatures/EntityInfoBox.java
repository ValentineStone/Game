package com.valentine.game.entity.creatures;

import java.awt.Color;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.*;

public class EntityInfoBox extends Entity
{
	public static final double HEIGHT = 120;
	public static final double WIDTH = 250;
	
	private Entity entity;
	
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
		double dy = Screen.getGraphics().getFontMetrics().getHeight();
		
		Screen.setColor(entity.getFillColor());
		
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.setColor(entity.getDrawColor());
		
		Screen.drawString("|---------------------------------------------------------\n", getX() + 5, getY() + dy);
		Screen.drawString("| ID_______:___" + entity.getId() + " " + entity.getClass().getSimpleName(), getX() + 5, getY() + 2 * dy);
		Screen.drawString("| X________:___" + entity.getX(), getX() + 5, getY() + 3 * dy);
		Screen.drawString("| Y________:___" + entity.getY(), getX() + 5, getY() + 4 * dy);
		Screen.drawString("| Velocity_:___" + entity.getVelocity(), getX() + 5, getY() + 5 * dy);
		Screen.drawString("| Rotation_:___" + entity.getRotation(), getX() + 5, getY() + 6 * dy);
		Screen.drawString("|---------------------------------------------------------\n", getX() + 5, getY() + 7 * dy);
		
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
	}
	
	
}
