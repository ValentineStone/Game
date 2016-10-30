package com.valentine.game.entity.creatures;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.objects.*;
import com.valentine.game.utils.*;

public class Collider extends EntityBasicAI implements Explodable
{	
	private static double VELOCITY_MAX = 5;
	private static double ACCELERATION = 0.1;
	
	private double rotationVelocity;
	
	public Collider(Container _container)
	{
		this(_container, 0,0);
		setPositionRandom();
	}
	
	
	
	public Collider(Container _container, double _x, double _y)
	{
		super(_container);
		setX(_x);
		setY(_y);
		setVelocityMax(VELOCITY_MAX);
		setAcceleration(ACCELERATION);
		setActive(true);
		setRotationRandom();
		
		setWidth(Math.random() * 40 + 20);
		setHeight(Math.random() * 40 + 20);
		setRotationVelocity((Math.random() > 0.5 ? 1 : -1) * (Math.random() * 0.01 + 0.05));
		
		setDrawColor(ColorExt.randomColor(10, 255));
		setFillColor(ColorExt.makeTransparent(getDrawColor(), 20));
	}
	
	public void update()
	{		
		setRotation(getRotation() + getRotationVelocity());
		
		accelerate();
		
		move();

		keepContained();
				
		for (Entity entity : getContainer())
		{
			if (entity instanceof Collider)
				if (entity.getId() < getId())
					collide((Collider)entity);
		}
	}	
	
	
	
	public void paint()
	{		
		Screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		
		Screen.setColor(getFillColor());
		
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.setColor(getDrawColor());
		
		Screen.drawRect(getX(), getY(),  getWidth(), getHeight());
		
		Screen.drawString(String.valueOf(getId()), getX() + getWidth() + 3, getY() - 3);

		Screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getVelocityX(), getCenterY() + getVelocityY());
		
		if (getWidth() < getHeight()) 
			Screen.drawOval(getX(), getCenterY() - getWidth()/2, getWidth(), getWidth());
		else
			Screen.drawOval(getCenterX() - getHeight()/2, getY(), getHeight(), getHeight());
		
		Screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}
	
	private boolean isColliding(Collider _collider)
	{
		if
		(
				(Math.abs(getCenterX() - _collider.getCenterX()) < (getWidth() + _collider.getWidth())/2)
				&&
				(Math.abs(getCenterY() - _collider.getCenterY()) < (getHeight() + _collider.getHeight())/2)
		) return true;
		return false;
	}
	
	public boolean collide(Collider _collider)
	{
		
		if (isColliding(_collider))
		{
			setRotation(MathExt.rotationFlip(MathExt.rotationMake(_collider.getX() - getX(), _collider.getY() - getY())));
			_collider.setRotation(MathExt.rotationFlip(getRotation()));
						
			setDrawColor(ColorExt.randomColor(10, 255));
			setFillColor(ColorExt.makeTransparent(getDrawColor(), 20));
			_collider.setDrawColor(ColorExt.randomColor(10, 255));
			_collider.setFillColor(ColorExt.makeTransparent(_collider.getDrawColor(), 20));
			
			accelerate();
			move();
			_collider.accelerate();
			_collider.move();
			
			return true;
		}

		return false;
	}



	public double getRotationVelocity()
	{
		return rotationVelocity;
	}



	public void setRotationVelocity(double _rotationVelocity)
	{
		rotationVelocity = _rotationVelocity;
	}	
	
	
}
