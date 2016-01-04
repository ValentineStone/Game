package com.valentine.game.entity.base;

import java.awt.Color;

import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.MathExt;

public abstract class __EntityObject extends Entity
{
	private double x = 0;
	private double y = 0;
	
	private double width = 0;
	private double height = 0;
	
	private double rotation = 0;
	
	private Color fillColor = ColorExt.TRANSPARENT;
	private Color drawColor = ColorExt.TRANSPARENT;
		
	public boolean isHitting(double _x, double _y)
	{
		if (_x > x && _x < (x + width) && _y > y && _y < (y + height)) return true;
		return false;
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
		
		
	
	
	
	
	public double getX()
	{
		return x;
	}

	public __EntityObject setX(double _x)
	{
		x = _x;
		return this;
	}

	public double getY()
	{
		return y;
	}

	public __EntityObject setY(double _y)
	{
		y = _y;
		return this;
	}
	
	public double getCenterX()
	{
		return x + width/2;
	}
	
	public double getCenterY()
	{
		return y + height/2;
	}
	
	public __EntityObject setPosition(double _x, double _y)
	{
		x = _x;
		y = _y;
		return this;
	}
	
	public __EntityObject setPositionRandom()
	{
		x = Math.random() * (getContainer().getWidth() - width);
		y = Math.random() * (getContainer().getHeight() - height);
		return this;
	}
	
	public __EntityObject setPositionCentered()
	{
		x = getContainer().getWidth()/2 - width/2;
		y = getContainer().getHeight()/2 - height/2;;
		return this;
	}
	
	public double getTrueX()
	{
		if (getContainer() == null)
			return getX();
		else
			return x + getContainer().getTrueX();
	}
	
	public double getTrueY()
	{
		if (getContainer() == null)
			return getY();
		else
			return y + getContainer().getTrueY();
	}
	
	public double getTrueCenterX()
	{
		return getTrueX() + width/2;
	}
	
	public double getTrueCenterY()
	{
		return getTrueY() + height/2;
	}
	
	
	
	
	
	
	
	public double getRotation()
	{
		return rotation;
	}

	public __EntityObject setRotation(double _rotation)
	{
		rotation = MathExt.rotationNormalize(_rotation);
		return this;
	}
	
	public __EntityObject setRotationRandom()
	{
		setRotation(Math.random() * MathExt.PI_2_1);
		return this;
	}
	
	public void rotationFlip()
	{
		setRotation(MathExt.rotationFlip(rotation));
	}
	
	public void rotationFlipX()
	{
		setRotation(MathExt.rotationFlipX(rotation));
	}
	
	public void rotationFlipY()
	{
		setRotation(MathExt.rotationFlipY(rotation));
	}
	
	
	
	

	public double getWidth()
	{
		return width;
	}

	public __EntityObject setWidth(double _width)
	{
		width = _width;
		return this;
	}

	public double getHeight()
	{
		return height;
	}

	public __EntityObject setHeight(double _height)
	{
		height = _height;
		return this;
	}
	
	
	
	
	
	public Color getFillColor()
	{
		return fillColor;
	}

	public __EntityObject setFillColor(Color _fillColor)
	{
		fillColor = _fillColor;
		return this;
	}

	public Color getDrawColor()
	{
		return drawColor;
	}

	public __EntityObject setDrawColor(Color _drawColor)
	{
		drawColor = _drawColor;
		return this;
	}


}
