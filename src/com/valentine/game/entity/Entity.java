package com.valentine.game.entity;

public abstract class Entity
{
	protected static int idGlobal = 0;
	
	protected Container container;
	
	protected boolean paintable = false;
	protected boolean updatable = false;
	
	protected int id;
	
	Entity(Container _container)
	{
		container = _container;
		id = idGlobal++;
		paintable = true;
		updatable = true;
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
