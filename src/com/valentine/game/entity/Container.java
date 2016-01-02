package com.valentine.game.entity;

import java.util.ArrayList;
import java.util.List;

import com.valentine.game.utils.Screen;

public class Container extends EntityObject
{
	protected List<Entity> entities;	
	
	
	
	public Container(Container _entities, double _x, double _y, double _width, double _height)
	{
		setX(_x);
		setY(_y);
		setWidth(_width);
		setHeight(_height);
		entities = new ArrayList<Entity>();
	}
	
	
	
	public void paint()
	{		
		Screen.setColor(getFillColor());
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.localize(getX(), getY());
		Screen.setClip(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < size(); i++)
		{
			if (entities.get(i).isPaintable()) entities.get(i).paint();
		}
		
		Screen.setClip(null);
		Screen.delocalize(getX(), getY());
		
		Screen.setColor(getDrawColor());
		Screen.drawRect(getX(), getY(), getWidth()-1, getHeight()-1);
	}

	public void update()
	{
		for (int i = 0; i < size(); i++)
		{
			if (entities.get(i).isUpdatable()) entities.get(i).update();
		}
	}
	
	protected void reset() {}	
	
	
	public int size()
	{
		return entities.size();
	}
	
	public Entity get(int _index)
	{
		return entities.get(_index);
	}
	
	public Container add(Entity _entity)
	{
		entities.add(_entity);
		_entity.setContainer(this);
		_entity.setPaintable(true);
		_entity.setUpdatable(true);
		_entity.reset();
		
		return this;
	}
	
	public boolean remove(Entity _entity)
	{
		return entities.remove(_entity);
	}

}