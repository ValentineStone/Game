package com.valentine.game.entity;

public abstract class EntityLiving extends EntityObject
{
	private boolean active = false;
	
	private double velocity = 0;
	private double velocityMax = 0;
	private double acceleration = 1;
	private double friction = 1;
	
	private double _velocityX = 0;
	private double _velocityY = 0;
	
	protected boolean keepContained()
	{
		boolean impact = false;
		if (getX() + getWidth() > getContainer().getWidth())
		{
			setX(getContainer().getWidth() - getWidth());
			rotationFlipY();
			impact = true;
		}
		if (getX() < 0)
		{
			setX(0);
			rotationFlipY();
			impact = true;
		}
		if (getY() + getHeight() > getContainer().getHeight())
		{
			setY(getContainer().getHeight() - getHeight());
			rotationFlipX();
			impact = true;
		}
		if (getY() < 0)
		{
			setY(0);
			rotationFlipX();
			impact = true;
		}
		return impact;
	}
	
	protected void move()
	{	
		setX(getX() + getVelocityX());
		setY(getY() + getVelocityY());
	}
	
	protected void accelerate()
	{
		if (isActive())
		{
			if (getVelocity() < getVelocityMax())
			{
				setVelocity( getVelocity() + getVelocityMax() * getAcceleration());
				if (getVelocity() > getVelocityMax()) setVelocity(getVelocityMax());
			}
			
		}
		else
		{
			if (velocity > 0)
			{
				setVelocity( getVelocity() - getVelocityMax() * getFriction());
				if (getVelocity() < 0) setVelocity(0);
			}
		}
	}
	
	protected boolean isTouchingEdge()
	{
		if (
			(getX() + getWidth() >= getContainer().getWidth()) ||
			(getX() <= 0) ||
			(getY() + getHeight() >= getContainer().getHeight()) ||
			(getY() <= 0)
			)
			return true;
		
		return false;
	}
	
	
	
	


	
	public boolean isActive() {
		return active;
	}

	public EntityLiving setActive(boolean active) {
		this.active = active;
		return this;
	}
	
		
	
	public EntityLiving setRotation(double _rotation)
	{
		super.setRotation(_rotation);
		_velocityX = velocity * Math.cos(getRotation());
		_velocityY = velocity * Math.sin(getRotation());
		return this;
	}
	
	
	

	public double getVelocity()
	{
		return velocity;
	}
	
	public double getVelocityX()
	{
		return _velocityX;
	}
	
	public double getVelocityY()
	{
		return _velocityY;
	}

	public EntityLiving setVelocity(double _velocity)
	{
		velocity = _velocity;
		_velocityX = velocity * Math.cos(getRotation());
		_velocityY = velocity * Math.sin(getRotation());
		return this;
	}
	
	public EntityLiving setVelocityRandom(double _min, double _max)
	{
		setVelocity(Math.random() * (_max - _min) + _min);
		return this;
	}

	public double getVelocityMax()
	{
		return velocityMax;
	}

	public EntityLiving setVelocityMax(double _velocityMax)
	{
		velocityMax = _velocityMax;
		return this;
	}

	public double getAcceleration()
	{
		return acceleration;
	}

	public EntityLiving setAcceleration(double _acceleration)
	{
		acceleration = _acceleration;
		return this;
	}

	public double getFriction()
	{
		return friction;
	}

	public EntityLiving setFriction(double _friction)
	{
		friction = _friction;
		return this;
	}

	
}
