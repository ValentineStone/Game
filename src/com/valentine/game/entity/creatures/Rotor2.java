package com.valentine.game.entity.creatures;

import java.awt.*;

import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.objects.*;
import com.valentine.game.utils.*;

public class Rotor2 extends EntityBasicAI implements Explodable
{
	private double r;

	private static final double VELOCITY_MAX = 1;
	private static final double ACCELERATION = 1;
	private static final double FRICTION = 1;

	public Rotor2(Container _container, double _x, double _y, double _r)
	{
		super(_container);

		setX(_x);
		setY(_y);
		setWidth(_r * 2);
		setHeight(_r * 2);
		setActive(true);

		setVelocityMax(VELOCITY_MAX);
		setAcceleration(ACCELERATION);
		setFriction(FRICTION);

		setRotationRandom();
		r = _r;
		setDrawColor(ColorExt.randomColor(20, 255));
		setFillColor(new Color(getDrawColor().getRed(), getDrawColor().getGreen(), getDrawColor().getBlue(), 50));
	}

	public Rotor2(Container _container)
	{
		this(_container, 0, 0, MathExt.random(20, 10));
		setPositionRandom();
	}

	public void update()
	{
		accelerate();
		move();

		keepContained();

		for (Entity entity : getContainer())
		{
			if (entity.getId() < getId())
			{
				if (entity instanceof Rotor2)
				{
					collide((Rotor2) entity);
				}
			}
		}
	}

	public void paint(Screen _screen)
	{

		_screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		_screen.setColor(getDrawColor());
		_screen.drawOval(getX(), getY(), getWidth(), getHeight());
		_screen.drawLine(getCenterX(), getCenterY(), getCenterX() + 2 * getVelocityX(), getCenterY() + 2 * getVelocityY());
		_screen.setColor(getFillColor());
		_screen.drawRect(getX(), getY(), getWidth(), getHeight());

		_screen.fillOval(getX(), getY(), r * 2, r * 2);
		_screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}

	public boolean collide(Rotor2 _rotor)
	{
		if (Math.pow(Math.pow(Math.abs(getCenterX() - _rotor.getCenterX()), 2.)
				+ Math.pow(Math.abs(getCenterY() - _rotor.getCenterY()), 2.), .5) < (r + _rotor.r))

		{
			setRotation(MathExt.rotationMake(getCenterX() - _rotor.getCenterX(), getCenterY() - _rotor.getCenterY()));
			_rotor.setRotation(MathExt.rotationFlip(getRotation()));

			// setVelocity(getVelocity()/2);
			// _rotor.setVelocity(getVelocity()/2);

			// Looper.pause();

			return true;
		}

		return false;
	}
}
