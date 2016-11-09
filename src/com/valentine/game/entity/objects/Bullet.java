package com.valentine.game.entity.objects;

import java.awt.*;

import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

public class Bullet extends EntityBasicAI implements Explodable
{
	private double x0;
	private double y0;

	private Entity master;

	public Bullet(Container _container, Entity _master, double _x, double _y, double _rotation)
	{
		super(_container);

		setX(_x);
		setY(_y);

		x0 = _x;
		y0 = _y;

		setVelocityMax(50);

		setRotation(_rotation);

		// setRotation(MathExt.randomSigned(MathExt.PI_1_2));

		// setRotationRandom();

		setDrawColor(ColorExt.opposite(getContainer().getFillColor()));

		master = _master;
	}

	public void paint(Screen _screen)
	{
		_screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));

		_screen.setColor(Color.RED);
		_screen.drawLine(x0, y0, getX(), getY());

		_screen.setColor(getDrawColor());

		_screen.drawOval(getX() - 2, getY() - 2, 4, 4);
		_screen.fillOval(getX() - 2, getY() - 2, 4, 4);

		_screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}

	public void update()
	{
		x0 = getX();
		y0 = getY();

		double dx = getVelocityX() / getVelocity();
		double dy = getVelocityY() / getVelocity();

		for (double tmpX = 0, tmpY = 0; Math.abs(tmpX) <= Math.abs(getVelocityX()); tmpX += dx, tmpY += dy)
		{
			for (Entity entity : getContainer())
			{
				if (entity instanceof Explodable && entity != master)
				{
					if (((EntityBasicAI) entity).isGettingHit(getX() + tmpX, getY() + tmpY))
					{
						entity.kill(this);

						kill(this);

						new Explosion(getContainer(), entity.getCenterX(), entity.getCenterY());

						return;
					}
				}
			}
		}

		if (isActive())
		{
			accelerate();
			move();

			if (isTouchingEdge())
			{
				kill(this);

				new Explosion(getContainer(), getX(), getY(), 20);
			}
		}

		setActive(true);
	}

	public Entity getMaster()
	{
		return master;
	}

	public void setMaster(Entity _master)
	{
		master = _master;
	}

}
