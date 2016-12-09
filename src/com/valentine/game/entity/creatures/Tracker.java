package com.valentine.game.entity.creatures;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class Tracker extends EntityBasicAI
{
	private Entity target;

	private double FULL_THROTTLE = 3;

	public Tracker(Container _container, Entity _target)
	{
		super(_container);

		target = _target;

		setSize(MathExt.random(10, 30), MathExt.random(10, 30));

		setPositionRandom();

		setFillColor(ColorExt.randomColor(0, 240));

		setAcceleration(0.1);
		setVelocityMax(FULL_THROTTLE);
		setActive(true);
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
		if (target != null)
		{

			double velocity = 3 + FULL_THROTTLE * MathExt.distanceMake(target.getX() - getX(), target.getY() - getY()) / 500;

			setVelocityMax(velocity);
			setVelocity(velocity);

			setRotation(MathExt.rotationMake(target.getX() - getX(), target.getY() - getY()));

			accelerate();

			move();
		}
	}
}
