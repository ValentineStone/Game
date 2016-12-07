package com.valentine.game.entity.objects;

import java.awt.*;

import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

public class Explosion extends Entity
{
	private double r = 0;
	private double R = MathExt.random(50, 100);
	private double dr = MathExt.random(10, 20);
	private double rI = 0;
	private boolean fake = false;

	public Explosion(Container _container, double _x, double _y)
	{
		this(_container, _x, _y, false);
	}

	public Explosion(Container _container, double _x, double _y, double _R)
	{
		this(_container, _x, _y, false, _R);
	}

	public Explosion(Container _container, double _x, double _y, boolean _fake, double _R)
	{
		this(_container, _x, _y, _fake);

		R = _R;
	}

	public Explosion(Container _container, double _x, double _y, boolean _fake)
	{
		super(_container);

		setX(_x);
		setY(_y);

		r = 0;
		fake = _fake;

		setDrawColor(Color.RED);
		setFillColor(ColorExt.makeTransparent(getDrawColor(), 40));
	}

	public void paint(Screen _screen)
	{
		rI = r + Interpolation.make(dr);

		_screen.setColor(getDrawColor());

		_screen.drawOval(getX() - rI, getY() - rI, 2 * rI, 2 * rI);

		_screen.setColor(getFillColor());

		_screen.fillOval(getX() - rI, getY() - rI, 2 * rI, 2 * rI);
	}

	public void update()
	{
		if (r > R)
		{
			kill(this);
		}

		r += dr;

		if (fake)
		{
			return;
		}

		for (Entity entity : getContainer())
		{
			if (entity instanceof Explodable)
			{
				if (((EntityBasicAI) entity).isCenterClose(getX(), getY(), r))
				{
					if (entity.kill(this))
					{
						new Explosion(getContainer(), entity.getCenterX(), entity.getCenterY(), fake);
					}
				}
			}
		}
	}

}
