package com.valentine.game.entity;

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.valentine.game.utils.Screen;

public class Container extends Entity implements Set<Entity>
{
	protected Set<Entity> entities;
	
	protected Color backgroundColor;
	protected Color borderColor;
	
	
	public Container(Container _entitiesontainer, double _x, double _y, double _width, double _height)
	{
		super(_entitiesontainer, _x, _y, 0, 0, _width, _height, true, true);
		backgroundColor = Screen.COLORS.TRANSPARENT;
		borderColor = Screen.COLORS.TRANSPARENT;
		entities = new LinkedHashSet<Entity>();
	}
	
	public void paint()
	{
		
		Screen.setColor(backgroundColor);
		Screen.fillRect(x, y, width, height);
		
		Screen.localize(x, y);
		
		for (Entity entity : entities)
		{
			entity.paint();
		}
		
		Screen.delocalize(x, y);
		
		Screen.setColor(borderColor);
		Screen.fillRect(x, y, width, height);
	}

	public void update()
	{
		for (Entity entity : entities)
		{
			entity.update();
		}
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(Color _backgroundColor)
	{
		backgroundColor = _backgroundColor;
	}

	public Color getBorderColor()
	{
		return borderColor;
	}

	public void setBorderColor(Color _borderColor)
	{
		borderColor = _borderColor;
	}

	public boolean add(Entity _entity)
	{
		return entities.add(_entity);
	}

	public boolean addAll(Collection<? extends Entity> _entities)
	{
		return entities.addAll(_entities);
	}

	public void clear()
	{
		entities.clear();
	}

	public boolean contains(Object _object)
	{
		return entities.contains(_object);
	}

	public boolean containsAll(Collection<?> _objects)
	{
		return entities.containsAll(_objects);
	}

	public boolean isEmpty()
	{
		return entities.isEmpty();
	}

	public Iterator<Entity> iterator()
	{
		return entities.iterator();
	}

	public boolean remove(Object _object)
	{
		return entities.remove(_object);
	}

	public boolean removeAll(Collection<?> _objects)
	{
		return entities.removeAll(_objects);
	}

	public boolean retainAll(Collection<?> _objects)
	{
		return entities.retainAll(_objects);
	}

	public int size()
	{
		return entities.size();
	}

	public Object[] toArray()
	{
		return entities.toArray();
	}

	public <T> T[] toArray(T[] _array)
	{
		return entities.toArray(_array);
	}
}