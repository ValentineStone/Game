package com.valentine.game.entity.ui;

import java.awt.Color;

import com.valentine.game.core.*;
import com.valentine.game.core.basic.EntityNew;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class ColorGrader extends Entity
{
	private Entity entity;
	
	private Color targetForegroundColor;
	private Color targetBackgroundColor;
	
	private boolean doForeground;
	private boolean doBackground;
	
	public ColorGrader(Container _container, Entity _entity, boolean _doForeground, boolean _doBackground)
	{
		super(_container);
		
		entity = _entity;
		doForeground = _doForeground;
		doBackground = _doBackground;
		
		if (doForeground)
		{
			targetForegroundColor = entity.getDrawColor();
		}
		if (doBackground)
		{
			targetBackgroundColor = entity.getFillColor();
		}
	}

	public void paint()
	{
		setPaintable(false);
	}

	public void update()
	{
		if (doForeground)
		{
			if (targetForegroundColor.equals(entity.getDrawColor()))
			{
				targetForegroundColor = ColorExt.randomColor(30, 155);
			}
			entity.setDrawColor(ColorExt.fadeto(entity.getDrawColor(), targetForegroundColor));
		}
		
		if (doBackground)
		{
			if (targetBackgroundColor.equals(entity.getFillColor()))
			{
				targetBackgroundColor = ColorExt.randomColor(30, 155);
			}
			entity.setFillColor(ColorExt.fadeto(entity.getFillColor(), targetBackgroundColor));
		}
	}
	
}
