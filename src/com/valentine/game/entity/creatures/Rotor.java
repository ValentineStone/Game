package com.valentine.game.entity.creatures;

import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.objects.*;
import com.valentine.game.utils.*;

public class Rotor extends EntityBasicAI implements Explodable
{

	private double r = 0;
	private double x1 = 0;
	private double y1 = 0;
	private double x2 = 0;
	private double y2 = 0;
	private double x3 = 0;
	private double y3 = 0;

	private double innerRotation = 0;

	private static final double VELOCITY_MAX = 7;
	private static final double ACCELERATION = 0.07;
	private static final double FRICTION = 1;
	private double INNER_ROTATION_ACCELERATION = 0.1;

	public Rotor(Container _container, double _x, double _y, double _r)
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
		innerRotation = 0;
		setDrawColor(ColorExt.randomColor(20, 255));
		setFillColor(ColorExt.makeTransparent(getDrawColor(), 50));
	}

	public Rotor(Container _container)
	{
		this(_container, 0, 0, MathExt.random(20, 10));
		INNER_ROTATION_ACCELERATION = (Math.random() > 0.5 ? -1 : 1) * (Math.random() * Math.PI / 8 + Math.PI / 32);

		setPositionRandom();
	}

	public void update()
	{
		accelerate();
		move();

		if (keepContained())
		{
			setVelocity(getVelocity() / 2);
			INNER_ROTATION_ACCELERATION *= -1;
		}

		for (Entity entity : getContainer())
		{
			if (entity.getId() < getId())
			{
				if (entity instanceof Rotor)
				{
					collide((Rotor) entity);
				}
			}
		}

		innerRotation += (getVelocity() / getVelocityMax()) * INNER_ROTATION_ACCELERATION;
	}

	public void paint(Screen _screen)
	{
		double polatedInnerRotation = innerRotation + Interpolation.make((getVelocity() / getVelocityMax()) * INNER_ROTATION_ACCELERATION);

		x1 = getCenterX() + r * Math.cos(polatedInnerRotation);
		y1 = getCenterY() + r * Math.sin(polatedInnerRotation);
		x2 = getCenterX() + r * Math.cos(MathExt.rotationNormalize(polatedInnerRotation + 2 * Math.PI / 3));
		y2 = getCenterY() + r * Math.sin(MathExt.rotationNormalize(polatedInnerRotation + 2 * Math.PI / 3));
		x3 = getCenterX() + r * Math.cos(MathExt.rotationNormalize(polatedInnerRotation + 4 * Math.PI / 3));
		y3 = getCenterY() + r * Math.sin(MathExt.rotationNormalize(polatedInnerRotation + 4 * Math.PI / 3));

		_screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		_screen.setColor(getFillColor());
		_screen.fillOval(getX(), getY(), getWidth(), getHeight());
		_screen.setColor(getDrawColor());
		_screen.drawOval(getX(), getY(), getWidth(), getHeight());
		_screen.drawLine(x1, y1, x2, y2);
		_screen.drawLine(x2, y2, x3, y3);
		_screen.drawLine(x3, y3, x1, y1);
		_screen.drawLine(getCenterX(), getCenterY(), x1, y1);
		_screen.drawLine(getCenterX(), getCenterY(), x2, y2);
		_screen.drawLine(getCenterX(), getCenterY(), x3, y3);
		_screen.drawLine(getCenterX() + Math.cos(getRotation()) * r, getCenterY() + Math.sin(getRotation()) * r, getCenterX() + Math.cos(getRotation()) * r + 2 * getVelocityX(), getCenterY() + Math.sin(getRotation()) * r + 2 * getVelocityY());
		_screen.drawString(getId() + "", getX() + getWidth() + 3, getY() - 3);
		_screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}

	public boolean collide(Rotor _rotor)
	{
		if (Math.pow(Math.pow(getX() - _rotor.getX(), 2) + Math.pow(getY() - _rotor.getY(), 2), 0.5) < (r + _rotor.r))
		{
			setRotation(MathExt.rotationMake(getCenterX() - _rotor.getCenterX(), getCenterY() - _rotor.getCenterY()));
			_rotor.setRotation(MathExt.rotationFlip(getRotation()));

			INNER_ROTATION_ACCELERATION *= -1;
			_rotor.INNER_ROTATION_ACCELERATION *= -1;

			setVelocity(getVelocity() / 2);
			_rotor.setVelocity(getVelocity() / 2);

			move();
			_rotor.move();

			return true;
		}

		return false;
	}

	public double getR()
	{
		return r;
	}

	public void setR(double _r)
	{
		r = _r;
		setSize(2*r, 2*r);
	}
}
