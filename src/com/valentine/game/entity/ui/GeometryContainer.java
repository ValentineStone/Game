package com.valentine.game.entity.ui;

import java.util.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.math.geom.*;

public class GeometryContainer extends EntityBasicAI
{
	protected List<Geometry> items = new ArrayList<>();
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
	
	
	
	public void paintGeometry(Screen _screen, Geometry _geometry)
	{
		if      (_geometry instanceof Circle2d)
			paintGeometry(_screen, (Circle2d)_geometry);
		else if (_geometry instanceof Seg2d)
			paintGeometry(_screen, (Seg2d)_geometry);
		else if (_geometry instanceof Dot2d)
			paintGeometry(_screen, (Dot2d)_geometry);
		else
			System.err.println("Unknown geometry: " + _geometry.getClass());
	}
	
	public void paintGeometry(Screen _screen, Circle2d _circle)
	{
		_screen.drawOval(_circle.x - _circle.r, _circle.y - _circle.r, 2*_circle.r, 2*_circle.r);
	}
	
	public void paintGeometry(Screen _screen, Seg2d _seg)
	{
		_screen.drawLine(_seg.d1.x, _seg.d1.y, _seg.d2.x, _seg.d2.y);
		Dot2d center = _seg.center();
		LineGeneral2d.perpendicular(_seg.getLine());
	}
	
	public void paintGeometry(Screen _screen, Dot2d _dot)
	{
		_screen.drawDot(_dot.x, _dot.y);
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
		_screen.invertY();
		
		for (Geometry geometry : items)
		{
			paintGeometry(_screen, geometry.getClass().cast(geometry));
		}

		_screen.invertY();
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
			items.removeAll(deletions);
			deletions.clear();
		}

		if (additions.size() > 0)
		{
			items.addAll(additions);
			additions.clear();
		}
	}

	public int size()
	{
		return items.size();
	}

	public void add(Geometry _geometry)
	{
		if (!items.contains(_geometry))
			additions.add(_geometry);
	}

	public void remove(Geometry _geometry)
	{
		deletions.add(_geometry);
	}

	public Iterator<Geometry> iterator()
	{
		return items.iterator();
	}

	public List<Geometry> getItems()
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
