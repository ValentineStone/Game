package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.MathExt;
import com.valentine.game.utils.Screen;

public class Collider extends EntityLiving
{	
	private static double VELOCITY_MAX = 5;
	
	private static boolean hugeExists = true;
	
	public double rotationVelocity;
	
	private Color drawColor;
	private Color fillColor;
	
	public Collider()
	{
		this(0,0);
	}
	
	
	
	public Collider(double _x, double _y)
	{
		setX(_x);
		setY(_y);
		setVelocityMax(VELOCITY_MAX);
		setAcceleration(0.1);
		setFriction(1);
		setActive(true);
		
		setVelocityRandom(0, 5);
		setRotationRandom();
		
		if (!hugeExists)
		{
			setWidth(Math.random() * 100 + 200);
			setHeight(Math.random() * 100 + 200);	
			rotationVelocity = 0;
			hugeExists = true;
		}
		else
		{
			setWidth(Math.random() * 40 + 20);
			setHeight(Math.random() * 40 + 20);
			rotationVelocity = (Math.random() > 0.5 ? 1 : -1) * (Math.random() * 0.01 + 0.05);
		}
		
		drawColor = ColorExt.randomColor(10, 255);
		fillColor = new Color(drawColor.getRed(), drawColor.getGreen(), drawColor.getBlue(), 20);
	}
	
	public void update()
	{		
		setRotation(getRotation() + rotationVelocity);
		
		accelerate();
		
		move();

		keepContained();
		
		Entity entity;
		
		for (int i = 0; i < getContainer().size(); i++)
		{
			entity = getContainer().get(i);
			
			if (entity instanceof Collider)
				if (entity.getId() < getId())
					collide((Collider)entity);
		}
	}
	
	
	
	protected void reset()
	{
		setPositionRandom();
	}
	
	
	
	
	public void paint()
	{		
		Screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		
		Screen.setColor(fillColor);
		
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.setColor(drawColor);
		
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
						
			drawColor = ColorExt.randomColor(10,255);
			fillColor = new Color(drawColor.getRed(), drawColor.getGreen(), drawColor.getBlue(), 20);
			_collider.drawColor = ColorExt.randomColor(10,255);
			_collider.fillColor = new Color(_collider.drawColor.getRed(), _collider.drawColor.getGreen(), _collider.drawColor.getBlue(), 20);
			
			accelerate();
			move();
			_collider.accelerate();
			_collider.move();
			
			return true;
		}

		return false;
	}



	public Color getDrawColor()
	{
		return drawColor;
	}
	
	
	
}
