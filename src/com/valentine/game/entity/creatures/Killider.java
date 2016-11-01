package com.valentine.game.entity.creatures;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.objects.*;
import com.valentine.game.utils.MathExt;

public class Killider extends Collider
{
	private static double VELOCITY_MAX = 5;
	
	private EntityBasicAI target;
	
	private double targetRotation;
	
	private long _tick = 0;
	
	public Killider(Container _container, EntityBasicAI _target)
	{
		this(_container, _target, 0, 0);
		
		setPositionRandom();
		while (target.isCenterClose(getCenterX(), getCenterY(), 400))
		{
			setPositionRandom();
		}
	}

	public Killider(Container _container, EntityBasicAI _target, double _x, double _y)
	{
		super(_container);
		setX(_x);
		setY(_y);
		setRotationVelocity(MathExt.randomSigned(0.01, 0.5));
		setVelocityMax(VELOCITY_MAX);
		setAcceleration(1);
		setFriction(1);
		target = _target;
	}
	
	@Override
	public void update()
	{
		targetRotation = MathExt.rotationMake(target.getCenterX() - getCenterX(), target.getCenterY() - getCenterY());
		
		//if (Math.abs(targetRotation - getRotation()) < getRotationVelocity())
		if (++_tick % 10 == 0)
		{
			new Bullet(getContainer(), this, getCenterX(), getCenterY(), targetRotation);
		}
		
		super.update();
	}
	
	@Override
	public boolean kill(Entity _killer)
	{
		if (_killer instanceof Explosion) return false;
		
		if (_killer instanceof Bullet)
		{
			if (((Bullet)_killer).getMaster() == target) multiply();
		}
		return super.kill(_killer);
	}
	
	public void multiply()
	{
		new Killider(getContainer(), target, getX(), getY() + MathExt.random(100, 300));
		new Killider(getContainer(), target, getX(), getY() - MathExt.random(100, 300));
	}

}
