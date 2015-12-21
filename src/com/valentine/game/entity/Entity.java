package com.valentine.game.entity;

public abstract class Entity
{
	protected static int idGlobal = 0;
	
	protected Container container;
	
	protected boolean paintable = false;
	protected boolean updatable = false;
	
	protected int id;
	
	protected double x;
	protected double y;
	protected double rotation;
	
	protected double velocity;
	
	protected double width;
	protected double height;
	
	public Entity()
	{
		id = idGlobal++;
	}
	
	public Entity
	(
		Container _container,
		double _x,
		double _y,
		double _rotation,
		double _velocity,
		double _width,
		double _height,
		boolean _paintable,
		boolean _updatable
	)
	{
		id = idGlobal++;
		
		container = _container;
		x = _x;
		y = _y;
		rotation = _rotation;
		velocity = _velocity;
		width = _width;
		height = _height;
		paintable = _paintable;
		updatable = _updatable;
	}
	
	public void paint()
	{
		if (paintable);
		else return;
	}
	
	public void update()
	{
		if (updatable);
		else return;
	}
	
	
	public boolean hit(double _x, double _y)
	{
		if (_x > x && _x < (x + width) && _y > y && _y < (y + height)) return true;
		return false;
	}
	
	
	
	
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

	public int getId()
	{
		return id;
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

	public double getRotation()
	{
		return rotation;
	}

	public void setRotation(double _rotation)
	{
		rotation = _rotation;
	}

	public double getVelocity()
	{
		return velocity;
	}

	public void setVelocity(double _velocity)
	{
		velocity = _velocity;
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
	
	

	public int hashCode()
	{
		return id;
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof Entity) if (((Entity)obj).id == id) return true;
		return false;
	}
	
	public String toString()
	{
		return (paintable ? "paintable " : "NOTpaintable ") + (updatable ? "updatable " : "NOTupdatable ") + "entity " + id + " in container " + container.toString();
	}
	
}
