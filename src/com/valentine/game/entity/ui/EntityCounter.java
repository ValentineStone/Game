package com.valentine.game.entity.ui;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class EntityCounter extends Entity
{

	private int maxCount = 0;

	private int count = 0;

	private Class targetClass;

	private double dy = 0;

	public EntityCounter(Container _container, Class _targetClass, double _x, double _y)
	{
		super(_container);

		setPosition(_x, _y);

		targetClass = _targetClass;

		setWidth(200);
	}

	public void paint(Screen _screen)
	{
		if (dy == 0)
		{
			dy = _screen.getGraphics().getFontMetrics().getHeight() + 1;
		}

		_screen.setColor(getDrawColor());

		_screen.drawString(" " + targetClass.getSimpleName(), getX(), getY() + 2 * dy);
		_screen.drawString(" current_:_" + count, getX(), getY() + 3 * dy);
		_screen.drawString(" max_____:_" + maxCount, getX(), getY() + 4 * dy);

		setHeight(dy * 5);

		_screen.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		count = 0;

		for (Entity entity : getContainer())
		{
			if (targetClass.isInstance(entity))
			{
				count++;
			}
		}

		if (count > maxCount)
		{
			maxCount = count;
		}
	}
}
