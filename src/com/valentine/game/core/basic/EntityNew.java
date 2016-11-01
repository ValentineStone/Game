package com.valentine.game.core.basic;

public abstract class EntityNew implements Updatable, Paintable
{

	private boolean paintable = true;
	private boolean updatable = true;
	
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
}
