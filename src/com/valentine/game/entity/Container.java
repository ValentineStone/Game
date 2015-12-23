package com.valentine.game.entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.valentine.game.utils.Screen;

public class Container extends Entity
{
	protected List<Entity> entities;
	
	protected Color fillColor;
	protected Color drawColor;
	
	
	public Container(Container _entities, double _x, double _y, double _width, double _height)
	{
		super(_entities, _x, _y, 0, 0, 0, 1, 1, _width, _height, true, true, false);
		fillColor = Screen.COLORS.TRANSPARENT;
		drawColor = Screen.COLORS.TRANSPARENT;
		entities = new ArrayList<Entity>();
	}
	
	public void paint()
	{
		super.paint();
		
		Screen.setColor(fillColor);
		Screen.fillRect(getX(), getY(), getWidth()-1, getHeight()-1);
		
		Screen.localize(getX(), getY());
		Screen.setClip(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < size(); i++)
		{
			entities.get(i).paint();
		}
		
		Screen.setClip(null);
		Screen.delocalize(getX(), getY());
		
		Screen.setColor(drawColor);
		Screen.drawRect(getX(), getY(), getWidth()-1, getHeight()-1);
	}

	public void update()
	{
		super.update();
		
		for (int i = 0; i < size(); i++)
		{
			entities.get(i).update();
		}
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
	
	
	

	public boolean add(Entity _entity)
	{
		return entities.add(_entity);
	}
	
	public Entity get(int _index)
	{
		return entities.get(_index);
	}
	
	public int size()
	{
		return entities.size();
	}
	
	public boolean remove(Entity _entity)
	{
		return entities.remove(_entity);
	}

}