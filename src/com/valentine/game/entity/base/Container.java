package com.valentine.game.entity.base;

import java.util.*;

import com.valentine.game.core.Screen;

public class Container extends Entity implements Iterable<Entity>
{
	protected List<Entity> items = new ArrayList<Entity>();
	protected List<Entity> additions = new ArrayList<Entity>();	
	protected List<Entity> deletions = new ArrayList<Entity>();	
	
	
	
	public Container(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container);
		setX(_x);
		setY(_y);
		setWidth(_width);
		setHeight(_height);
	}
	
	
	
	@Override
	public void paint(Screen _screen)
	{		
		_screen.setColor(getFillColor());
		_screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		_screen.localize(getX(), getY());
		_screen.setClip(0, 0, getWidth(), getHeight());
		
		for (Entity entity : items)
		{
			if (entity.isPaintable()) entity.paint(_screen);
		}
		
		_screen.setClip(null);
		_screen.delocalize(getX(), getY());
		
		_screen.setColor(getDrawColor());
		_screen.drawRect(getX(), getY(), getWidth()-1, getHeight()-1);
	}

	@Override
	public void update()
	{
		if (deletions.size() > 0)
		{
			items.removeAll(deletions);
			deletions.clear();
		}
		
		if (additions.size() > 0)
		{
			for (Entity entity : additions)
			{
				entity.setContainer(this);
			}
			items.addAll(additions);
			additions.clear();
		}
		
		for (Entity entity : items)
		{
			if (entity.isUpdatable()) entity.update();
		}
	}	
	
	
	public int size()
	{
		return items.size();
	}
	
	
	public void moveIn(Entity _entity)
	{
		if (!items.contains(_entity))
		{
			if (_entity.getContainer() != null)
			{
				_entity.getContainer().remove(_entity);
			}
	
			additions.add(_entity);
		}
	}
	
	public void remove(Entity _entity)
	{
		deletions.add(_entity);
	}



	@Override
	public Iterator<Entity> iterator()
	{
		return items.iterator();
	}



	public List<Entity> getItems()
	{
		return items;
	}



	public List<Entity> getAdditions()
	{
		return additions;
	}



	public List<Entity> getDeletions()
	{
		return deletions;
	}
	
	
	
	
	



}