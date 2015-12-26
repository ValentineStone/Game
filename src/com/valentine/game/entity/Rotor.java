package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class Rotor extends Entity {
	private double r;
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private double x3;
	private double y3;
	
	private double innerRotation;
	
	private static final double VELOCITY_MAX = 7;
	private static final double ACCELERATION = 0.07;
	private static final double FRICTION = 1;
	private double INNER_ROTATION_ACCELERATION = 0.1;
	
	private Color drawColor;
	private Color fillColor;
	
	public Rotor(Container _container, double _r, double _x, double _y)
	{
		super(_container, _x,_y,0,0, VELOCITY_MAX, ACCELERATION, FRICTION, _r*2, _r*2, true,true,true);
		setRotationRandom();
		r = _r;
		innerRotation = 0;
		drawColor = Screen.randomColor(20, 255);
		fillColor = new Color(drawColor.getRed(), drawColor.getGreen(), drawColor.getBlue(), 50);
	}
	
	public Rotor(Container _container)
	{
		this (_container, Math.random() * 10 + 10, 0,0);
		INNER_ROTATION_ACCELERATION = (Math.random() > 0.5 ? -1 : 1) * (Math.random() * Math.PI / 8 + Math.PI / 32);
		setPositionRandom();
	}
	
	public void update()
	{
		super.update();
		accelerate();
		move();
		
		if (keepContained())
		{
			setVelocity(getVelocity()/2);
			INNER_ROTATION_ACCELERATION *= -1;
		}
		
		Entity entity;
		
		for (int i = 0; i < getContainer().size(); i++)
		{
			entity = getContainer().get(i);
			
			if (entity instanceof Rotor)
				if (entity.getId() < getId())
					collide((Rotor)entity);
		}
		
		innerRotation += (getVelocity()/getVelocityMax()) * INNER_ROTATION_ACCELERATION;
	}
	
	public void paint()
	{
		super.paint();
		
		double polatedInnerRotation = innerRotation + Interpolation.make((getVelocity()/getVelocityMax()) * INNER_ROTATION_ACCELERATION);
		
		x1 = getCenterX() + r * Math.cos(polatedInnerRotation);
		y1 = getCenterY() + r * Math.sin(polatedInnerRotation);
		x2 = getCenterX() + r * Math.cos(rotationNormalize(polatedInnerRotation + 2*Math.PI/3));
		y2 = getCenterY() + r * Math.sin(rotationNormalize(polatedInnerRotation + 2*Math.PI/3));
		x3 = getCenterX() + r * Math.cos(rotationNormalize(polatedInnerRotation + 4*Math.PI/3));
		y3 = getCenterY() + r * Math.sin(rotationNormalize(polatedInnerRotation + 4*Math.PI/3));
		
		Screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		Screen.setColor(fillColor);
		Screen.fillOval(getX(), getY(), getWidth(), getHeight());
		Screen.setColor(drawColor);
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
		Screen.drawLine(x1, y1, x2, y2);
		Screen.drawLine(x2, y2, x3, y3);
		Screen.drawLine(x3, y3, x1, y1);
		Screen.drawLine(getCenterX(), getCenterY(), x1, y1);
		Screen.drawLine(getCenterX(), getCenterY(), x2, y2);
		Screen.drawLine(getCenterX(), getCenterY(), x3, y3);
		Screen.drawLine(
						getCenterX() + Math.cos(getRotation())*r,
						getCenterY() + Math.sin(getRotation())*r,
						getCenterX() + Math.cos(getRotation())*r + 2 * getVelocityX(),
						getCenterY() + Math.sin(getRotation())*r + 2 * getVelocityY()
						);
		Screen.drawString(getId()+"", getX() + getWidth() + 3, getY() - 3);
		Screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}
	
	public boolean collide(Rotor _rotor)
	{
		if (Math.pow(Math.pow(getX() - _rotor.getX(),2) + Math.pow(getY() - _rotor.getY(),2),0.5) < (r + _rotor.r))
		{
			if (_rotor.getCenterX() - getCenterX() > 0)
			{
				_rotor.setRotation(rotationMake(_rotor.getCenterX() - getCenterX(), _rotor.getCenterY() - getCenterY()));
				setRotation(rotationFlip(_rotor.getRotation()));
			}
			else
			{
				setRotation(rotationMake(_rotor.getCenterX() - getCenterX(), _rotor.getCenterY() - getCenterY()));
				_rotor.setRotation(rotationFlip(_rotor.getRotation()));
			}
			
			INNER_ROTATION_ACCELERATION *= -1;
			_rotor.INNER_ROTATION_ACCELERATION *= -1;
			
			setVelocity(getVelocity()/2);
			_rotor.setVelocity(getVelocity()/2);
			
			
			return true;
		}

		return false;
	}
}
