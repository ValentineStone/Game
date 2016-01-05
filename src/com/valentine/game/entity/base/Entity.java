package com.valentine.game.entity.base;

import java.awt.Color;

import com.valentine.game.utils.*;

public abstract class Entity
{
	private static int idGlobalTop = 0;
	
	//private static int[] idGlobalFree = new int[Integer.MAX_VALUE];
	
	
	
	
	
	
	
	private int id = 0;
	
	private Container container = null;
	
	private boolean paintable = true;
	private boolean updatable = true;
	private boolean dead = false;
	
	private double x = 0;
	private double y = 0;
	
	private double width = 0;
	private double height = 0;
	
	private double rotation = 0;
	
	private double velocity = 0;
	
	private double _velocityX = 0;
	private double _velocityY = 0;
	
	private Color drawColor = ColorExt.TRANSPARENT;
	private Color fillColor = ColorExt.TRANSPARENT;
	
	
	
	
	
	
	
	public Entity(Container _container)
	{
		setContainer(_container);
		if(getContainer() != null) getContainer().moveIn(this);
		
		id = idGlobalTop++;
	}
	
	public abstract void paint();
	
	public abstract void update();
	
	
	
	
	
	
	
	
	
	public Container getContainer()
	{
		return container;
	}
	
	public void setContainer(Container _container)
	{
		container = _container;
	}
	
	
	
	

	public boolean isPaintable()
	{
		return paintable;
	}

	public void setPaintable(boolean _paintable)
	{
		paintable = _paintable;
	}

	public boolean isUpdatable()
	{
		return updatable;
	}

	public void setUpdatable(boolean _updatable)
	{
		updatable = _updatable;
	}

	
	
	
	
	
	
	public double getX()
	{
		return x;
	}

	public void setX(double _x)
	{
		x = _x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double _y)
	{
		y = _y;
	}
	
	public double getCenterX()
	{
		return getX() + getWidth()/2;
	}
	
	public double getCenterY()
	{
		return getY() + getHeight()/2;
	}
	
	public void setPosition(double _x, double _y)
	{
		setX(_x);
		setY(_y);
	}
	
	public void setPositionRandom()
	{
		setX(MathExt.random(getContainer().getWidth() - getWidth()));
		setY(MathExt.random(getContainer().getHeight() - getHeight()));
	}
	
	public void setPositionCentered()
	{
		setX(getContainer().getWidth()/2 - getWidth()/2);
		setY(getContainer().getHeight()/2 - getHeight()/2);
	}
	
	public double getTrueX()
	{
		if (getContainer() == null)
			return getX();
		else
			return getX() + getContainer().getTrueX();
	}
	
	public double getTrueY()
	{
		if (getContainer() == null)
			return getY();
		else
			return getY() + getContainer().getTrueY();
	}
	
	public double getTrueCenterX()
	{
		return getTrueX() + getHeight()/2;
	}
	
	public double getTrueCenterY()
	{
		return getTrueY() + getHeight()/2;
	}
	
	
	
	
	
	
	public double getWidth()
	{
		return width;
	}

	public void setWidth(double _width)
	{
		width = _width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double _height)
	{
		height = _height;
	}
	
	
	
	
	
	
	
	public double getRotation()
	{
		return rotation;
	}

	public void setRotation(double _rotation)
	{
		rotation = MathExt.rotationNormalize(_rotation);
		_makeVelocityXY();
	}
	
	public void setRotationRandom()
	{
		setRotation(MathExt.random(MathExt.PI_2_1));
	}
	
	public void rotationFlip()
	{
		setRotation(MathExt.rotationFlip(getRotation()));
	}
	
	public void rotationFlipX()
	{
		setRotation(MathExt.rotationFlipX(getRotation()));
	}
	
	public void rotationFlipY()
	{
		setRotation(MathExt.rotationFlipY(getRotation()));
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

	public void setVelocity(double _velocity)
	{
		velocity = _velocity;
		_makeVelocityXY();
	}
	
	private void _makeVelocityXY()
	{
		_velocityX = getVelocity() * Math.cos(getRotation());
		_velocityY = getVelocity() * Math.sin(getRotation());		
	}

	
	
	
	
	
	
	
	public Color getFillColor()
	{
		return fillColor;
	}

	public void setFillColor(Color _fillColor)
	{
		fillColor = _fillColor;
	}

	public Color getDrawColor()
	{
		return drawColor;
	}

	public void setDrawColor(Color _drawColor)
	{
		drawColor = _drawColor;
	}
	
	
	
	
	
	
	
	
	
	
	public boolean kill()
	{
		if (dead) return false;
		setUpdatable(false);
		setPaintable(false);
		dead = true;
		return true;
	}

	public int getId()
	{
		return id;
	}
		
	public int hashCode()
	{
		return getId();
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof Entity) if (((Entity)obj).getId() == getId()) return true;
		return false;
	}
	
	public String toString()
	{
		return "Entity " + getId() + ": " + getClass().getSimpleName();
	}
	
}
