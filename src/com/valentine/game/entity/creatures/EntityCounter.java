package com.valentine.game.entity.creatures;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.*;

public class EntityCounter extends Entity
{

	private int maxCount = 0;
	
	private int count = 0;
	
	private Class targetClass;
	
	public EntityCounter(Container _container, Class _targetClass, double _x, double _y)
	{
		super(_container);
		
		setPosition(_x, _y);
		
		targetClass = _targetClass;
	}

	public void paint()
	{
		double dy = Screen.getGraphics().getFontMetrics().getHeight() + 1;
		
		Screen.setColor(getDrawColor());
		
		Screen.drawString(" " + targetClass.getSimpleName() + "s:", getX(), getY() + 2 * dy);
		Screen.drawString(" current_:_" + count, getX(), getY() + 3 * dy);
		Screen.drawString(" max_____:_" + maxCount, getX(), getY() + 4 * dy);
		
		Screen.drawRect(getX(), getY(), 200, dy * 5);
	}

	public void update()
	{
		count = 0;
		
		for (Entity entity : getContainer())
		{
			if (targetClass.isInstance(entity)) count++;
		}
		
		if (count > maxCount)
		{
			maxCount = count;
		}
	}
}
