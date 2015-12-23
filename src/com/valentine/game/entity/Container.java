package com.valentine.game.entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.valentine.game.utils.Screen;

public class Container extends Entity
{
	protected List<Entity> entities;
	
	protected Color backgroundColor;
	protected Color borderColor;
	
	
	public Container(Container _entities, double _x, double _y, double _width, double _height)
	{
		super(_entities, _x, _y, 0, 0, 0, 1, 1, _width, _height, true, true, false);
		backgroundColor = Screen.COLORS.TRANSPARENT;
		borderColor = Screen.COLORS.TRANSPARENT;
		entities = new ArrayList<Entity>();
	}
	
	public void paint()
	{
		super.paint();
		
		Screen.setColor(backgroundColor);
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.localize(getX(), getY());
		
		for (int i = 0; i < size(); i++)
		{
			entities.get(i).paint();
		}
		
		Screen.delocalize(getX(), getY());
		
		Screen.setColor(borderColor);
		Screen.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		super.update();
		
		for (int i = 0; i < size(); i++)
		{
			entities.get(i).update();
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