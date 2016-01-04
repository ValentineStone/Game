package com.valentine.game.entity.base;

public abstract class __Entity
{
	private static int idGlobal = 0;
	
	private Container container = null;
	
	private boolean paintable = false;
	private boolean updatable = false;
	private boolean dead = false;
	
	private int id;
	
	
	
	public __Entity()
	{
		id = idGlobal++;
	}
	
	
	
	public abstract void paint();
	
	public abstract void update();
	
	protected abstract void reset();

	
	
	
	
	
	public Container getContainer()
	{
		return container;
	}
	
	protected __Entity setContainer(Container _container)
	{
		container = _container;
		return this;
	}
	
	
	
	

	public boolean isPaintable()
	{
		return paintable;
	}

	protected __Entity setPaintable(boolean _paintable)
	{
		paintable = _paintable;
		return this;
	}

	public boolean isUpdatable()
	{
		return updatable;
	}

	protected __Entity setUpdatable(boolean _updatable)
	{
		updatable = _updatable;
		return this;
	}

	public boolean isDead() {
		return dead;
	}
	
	public void kill()
	{
		dead = true;
		updatable = false;
		paintable = false;
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
		if (obj instanceof EntityObject) if (((EntityObject)obj).getId() == getId()) return true;
		return false;
	}
	
	public String toString()
	{
		return (paintable ? "paintable " : "NOTpaintable ") + (updatable ? "updatable " : "NOTupdatable ") + "entity " + id + " in container " + container.toString();
	}
	
}
