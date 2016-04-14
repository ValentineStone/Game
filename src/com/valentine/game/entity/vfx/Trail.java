package com.valentine.game.entity.vfx;

import java.util.ArrayList;
import java.util.List;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.entity.base.EntityBasicAI;
import com.valentine.game.utils.ColorExt;

public class Trail extends Entity
{
	private EntityBasicAI target;
	private int maxAge;
	private long age = 0;
	
	private class Dot
	{
		public Dot(double _x, double _y, boolean _isMarked)
		{
			x = _x;
			y = _y;
			isMarked = _isMarked;
			
		}
		public double x;
		public double y;
		public boolean isMarked;
	}
	
	private List<Dot> path = new ArrayList<Dot>();
	
	public Trail(Container _container, EntityBasicAI _target, int _maxAge)
	{
		super(_container);
		target = _target;
		maxAge = _maxAge;
		setDrawColor(ColorExt.randomColor(70, 255));
	}

	public void paint()
	{
		if (path.size() < 2)
		{
			return;
		}
		
		Screen.setColor(getDrawColor());
		
		for (int i = 1; i < path.size(); i++)
		{
			Screen.drawLine(path.get(i-1).x, path.get(i-1).y, path.get(i).x, path.get(i).y);
			if (path.get(i).isMarked)
			{
				Screen.drawOval(path.get(i).x - 5, path.get(i).y - 5, 10, 10);
			}
		}
	}

	public void update()
	{
		path.add(new Dot(target.getCenterX(), target.getCenterY(), target.isTouchingEdge()));
		
		age++;
		
		if (age > maxAge)
		{
			path.remove(path.get(0));
		}
		
	}

}
