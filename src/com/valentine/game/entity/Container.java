package com.valentine.game.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Container extends Entity implements List<Entity>
{

	Container(Container _container)
	{
		super(_container);
	}

	protected List<Entity> entities = new LinkedList<Entity>();

	
	public void paint()
	{
		for (Entity entity : entities)
		{
			entity.paint();
		}
	}

	public void update()
	{
		for (Entity entity : entities)
		{
			entity.update();
		}
	}

	public boolean add(Entity _e)
	{
		return entities.add(_e);
	}

	public void add(int _index, Entity _element)
	{
		entities.add(_index, _element);
	}

	public boolean addAll(Collection<? extends Entity> _c)
	{
		return entities.addAll(_c);
	}

	public boolean addAll(int _index, Collection<? extends Entity> _c)
	{
		return entities.addAll(_index, _c);
	}

	public void clear()
	{
		entities.clear();
	}

	public boolean contains(Object _o)
	{
		return entities.contains(_o);
	}

	public boolean containsAll(Collection<?> _c)
	{
		return entities.containsAll(_c);
	}

	public Entity get(int _index)
	{
		return entities.get(_index);
	}

	public int indexOf(Object _o)
	{
		return entities.indexOf(_o);
	}

	public boolean isEmpty()
	{
		return entities.isEmpty();
	}

	public Iterator<Entity> iterator()
	{
		return entities.iterator();
	}

	public int lastIndexOf(Object _o)
	{
		return entities.lastIndexOf(_o);
	}

	public ListIterator<Entity> listIterator()
	{
		return entities.listIterator();
	}

	public ListIterator<Entity> listIterator(int _index)
	{
		return entities.listIterator(_index);
	}

	public boolean remove(Object _o)
	{
		return entities.remove(_o);
	}

	public Entity remove(int _index)
	{
		return entities.remove(_index);
	}

	public boolean removeAll(Collection<?> _c)
	{
		return entities.removeAll(_c);
	}

	public boolean retainAll(Collection<?> _c)
	{
		return entities.retainAll(_c);
	}

	public Entity set(int _index, Entity _element)
	{
		return entities.set(_index, _element);
	}

	public int size()
	{
		return entities.size();
	}

	public List<Entity> subList(int _fromIndex, int _toIndex)
	{
		return entities.subList(_fromIndex, _toIndex);
	}

	public Object[] toArray()
	{
		return entities.toArray();
	}

	public <T> T[] toArray(T[] _a)
	{
		return entities.toArray(_a);
	}

}
