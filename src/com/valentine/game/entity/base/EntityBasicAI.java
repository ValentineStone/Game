package com.valentine.game.entity.base;

public abstract class EntityBasicAI extends Entity
{
	private boolean active = false;
	

	private double velocityMax = 0;
	private double acceleration = 1;
	private double friction = 1;
	
	
	public EntityBasicAI(Container _container)
	{
		super(_container);
	}
	
	
	//---------------------------------------------BasicAI------------------------------------------------
	
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
	
	protected boolean keepOffEdges()
	{
		boolean impact = false;
		if (getX() + getWidth() > getContainer().getWidth())
		{
			setX(getContainer().getWidth() - getWidth() - 1);
			rotationFlipY();
			impact = true;
		}
		if (getX() < 0)
		{
			setX(1);
			rotationFlipY();
			impact = true;
		}
		if (getY() + getHeight() > getContainer().getHeight())
		{
			setY(getContainer().getHeight() - getHeight() - 1);
			rotationFlipX();
			impact = true;
		}
		if (getY() < 0)
		{
			setY(1);
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
			if (getVelocity() > 0)
			{
				setVelocity( getVelocity() - getVelocityMax() * getFriction());
				if (getVelocity() < 0) setVelocity(0);
			}
		}
	}
	
	public boolean isTouchingEdge()
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
	
	public boolean isOutOfContainer()
	{
		if (
			(getX() > getContainer().getWidth()) ||
			(getX() + getWidth() < 0) ||
			(getY() > getContainer().getHeight()) ||
			(getY() + getHeight() < 0)
			)
			return true;
		
		return false;
	}
	
	public boolean isGettingHit(double _x, double _y)
	{
		if (_x > getX() && _x < (getX() + getWidth()) && _y > getY() && _y < (getY() + getHeight())) return true;
		return false;
	}
	
	public boolean isCenterClose(double _x, double _y, double _r)
	{
		if (Math.pow(Math.pow(getX() - _x, 2) + Math.pow(getY() - _y, 2), 0.5) <= _r) return true;
		return false;
	}
	
	
	
	
	
	
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	public double getVelocityMax()
	{
		return velocityMax;
	}

	public void setVelocityMax(double _velocityMax)
	{
		velocityMax = _velocityMax;
	}	
	
	public double getAcceleration()
	{
		return acceleration;
	}

	public void setAcceleration(double _acceleration)
	{
		acceleration = _acceleration;
	}

	public double getFriction()
	{
		return friction;
	}

	public void setFriction(double _friction)
	{
		friction = _friction;
	}

	
}
