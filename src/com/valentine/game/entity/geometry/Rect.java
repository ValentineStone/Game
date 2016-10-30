package com.valentine.game.entity.geometry;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class Rect extends EntityBasicAI
{

	public Rect(Container _container)
	{
		super(_container);
	}

	public void paint()
	{
		Screen.setColor(getFillColor());
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.setColor(getDrawColor());
		Screen.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		setUpdatable(false);
	}

}
