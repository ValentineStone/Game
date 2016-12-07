package com.valentine.game.entity.geometry;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class Rect extends EntityBasicAI
{

	public Rect(Container _container)
	{
		super(_container);
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRect(getX(), getY(), getWidth(), getHeight());

		_screen.setColor(getDrawColor());
		_screen.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		setUpdatable(false);
	}

}
