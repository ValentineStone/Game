package com.valentine.game.entity.ambient;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.utils.MathExt;

public class BlinkingStar extends Entity
{
	private double maxr;
	private double r;
	private double dr;

	public BlinkingStar(Container _container)
	{
		super(_container);
		setPositionRandom();
		
		maxr = MathExt.random(1, 3);
		dr = MathExt.random(maxr / 100, maxr / 5);
	}

	@Override
	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());
		_screen.fillRect(getX() - r, getY() - r, r, r);
	}

	@Override
	public void update()
	{
		r += dr;
		if (r > maxr || r < 0)
		{
			dr *= -1;
			if (r < 0) setPositionRandom();
		}
	}
}