package com.valentine.game.entity.vfx;

import java.util.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class Trail extends Entity
{
	private EntityBasicAI target;
	private int maxAge;
	private long age = 0;

	private boolean isScheduledForKill = false;
	private long scheduledKillAge = 0;
	private int consistency;

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

	private List<Dot> path = new ArrayList<>();

	public Trail(Container _container, EntityBasicAI _target, int _maxAge)
	{
		this(_container, _target, _maxAge, 1);
	}
	
	public Trail(Container _container, EntityBasicAI _target, int _maxAge, int _consistency)
	{
		super(_container);
		target = _target;
		maxAge = _maxAge;
		consistency = _consistency;
		setDrawColor(ColorExt.randomColor(70, 255));
	}

	public void paint(Screen _screen)
	{
		if (path.size() < 2)
		{
			return;
		}

		_screen.setColor(getDrawColor());

		for (int i = 1; i < path.size(); i++)
		{
			_screen.drawLine(path.get(i - 1).x, path.get(i - 1).y, path.get(i).x, path.get(i).y);
			if (path.get(i).isMarked)
			{
				_screen.drawOval(path.get(i).x - 5, path.get(i).y - 5, 10, 10);
			}
		}
	}

	public void update()
	{
		if ((age - scheduledKillAge) > maxAge && isScheduledForKill)
		{
			kill(this);
		}

		age++;
		
		if (age % consistency == 0)
		{
			path.add(new Dot(target.getCenterX(), target.getCenterY(), false));

			if (age > maxAge)
			{
				path.remove(path.get(0));
			}
		}

	}

	public void scheduleKill()
	{
		isScheduledForKill = true;
		scheduledKillAge = age;
	}

}
