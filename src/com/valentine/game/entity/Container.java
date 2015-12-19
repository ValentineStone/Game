package com.valentine.game.entity;

import java.util.LinkedList;

public class Container implements Entity
{

	protected LinkedList<Entity> entities = new LinkedList<Entity>();
	
	public Entity add(Entity _entity) {
		entities.add(_entity);
		return _entity;
	}
	
	public Entity get(int _i)
	{
		return entities.get(_i);
	}
	
	public synchronized void remove(int _i)
	{
		entities.remove(_i);
	}
	
	public int size()
	{
		return entities.size();
	}
	
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
}
