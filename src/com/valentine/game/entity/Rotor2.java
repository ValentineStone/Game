package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.MathExt;
import com.valentine.game.utils.Screen;

public class Rotor2 extends EntityLiving {
	private double r;
	
	private static final double VELOCITY_MAX = 7;
	private static final double ACCELERATION = 1;
	private static final double FRICTION = 1;
	
	public Rotor2(double _x, double _y, double _r)
	{
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
	
	public Rotor2()
	{
		this (0,0, MathExt.random(20, 10));
	}
	
	protected void reset()
	{
		setPositionRandom();
	}
	
	public void update()
	{
		accelerate();
		move();
		
		keepContained();
		
		Entity entity;
		
		for (int i = 0; i < getContainer().size() && this != (entity = getContainer().get(i)); i++)
		{			
			if (entity instanceof Rotor2)
				collide((Rotor2)entity);
		}
	}
	
	public void paint()
	{
		
		Screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		Screen.setColor(getDrawColor());
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
		Screen.drawLine(
				getCenterX(),
				getCenterY(),
				getCenterX() + 2 * getVelocityX(),
				getCenterY() + 2 * getVelocityY()
				);
		Screen.setColor(getFillColor());
		Screen.drawRect(getX(), getY(), getWidth(), getHeight());

		Screen.fillOval(getX(), getY(), r*2, r*2);
		Screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}
	
	public boolean collide(Rotor2 _rotor)
	{
		if (Math.pow(Math.pow(Math.abs(getCenterX() - _rotor.getCenterX()),2.) + Math.pow(Math.abs(getCenterY() - _rotor.getCenterY()),2.),.5) < (r + _rotor.r))
		
		{			
			setRotation(MathExt.rotationMake(getCenterX() - _rotor.getCenterX(), getCenterY() - _rotor.getCenterY()));
			_rotor.setRotation(MathExt.rotationFlip(getRotation()));
			
			//setVelocity(getVelocity()/2);
			//_rotor.setVelocity(getVelocity()/2);
			
			//Looper.pause();
			
			return true;
		}

		return false;
	}
}
