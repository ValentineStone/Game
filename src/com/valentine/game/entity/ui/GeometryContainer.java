package com.valentine.game.entity.ui;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.geom.*;
import com.valentine.game.utils.painters.*;

public class GeometryContainer extends EntityBasicAI
{
	protected Map<Geometry, Color> items = new HashMap<>();
	protected List<Geometry> additions = new ArrayList<>();
	protected List<Geometry> deletions = new ArrayList<>();

	public GeometryContainer(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container);
		setX(_x);
		setY(_y);
		setWidth(_width);
		setHeight(_height);
	}
	
	

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRect(getX(), getY(), getWidth(), getHeight());

		_screen.localize(getX(), getY());
		_screen.setClip(0, 0, getWidth(), getHeight());
		

		_screen.setColor(getDrawColor());
		_screen.drawLine(getCenterX(), 0, getCenterX(), getHeight());
		_screen.drawLine(0, getCenterY(), getWidth(), getCenterY());

		_screen.localize(getCenterX(), getCenterY());
		
		for (Entry<Geometry, Color> entry : items.entrySet())
		{
			_screen.setColor(entry.getValue());
			GeometryPainter.paint(_screen, entry.getKey(), this);
		}

		_screen.delocalize(getCenterX(), getCenterY());

		_screen.setClip(null);
		_screen.delocalize(getX(), getY());

		_screen.setColor(getDrawColor());
		_screen.drawRect(getX(), getY(), getWidth() - 1, getHeight() - 1);
	}

	public void update()
	{
		if (deletions.size() > 0)
		{
			items.keySet().removeAll(deletions);
			deletions.clear();
		}

		if (additions.size() > 0)
		{
			for (Geometry geometry : additions)
				items.put(geometry, ColorExt.randomColor(20, 255));
			additions.clear();
		}
	}

	public int size()
	{
		return items.size();
	}

	public void add(Geometry _geometry)
	{
		if (items.get(_geometry) == null)
			additions.add(_geometry);
	}

	public void remove(Geometry _geometry)
	{
		deletions.add(_geometry);
	}

	public Iterator<Geometry> iterator()
	{
		return items.keySet().iterator();
	}

	public Map<Geometry, Color> getItems()
	{
		return items;
	}

	public List<Geometry> getAdditions()
	{
		return additions;
	}

	public List<Geometry> getDeletions()
	{
		return deletions;
	}

}
