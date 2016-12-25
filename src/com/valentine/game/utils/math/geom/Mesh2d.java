package com.valentine.game.utils.math.geom;

import java.util.*;

public class Mesh2d implements Geometry
{
	protected final List<Dot2d> dots = new ArrayList<>();
	
	protected Mesh2d() {}
	
	public Mesh2d(Dot2d _a, Dot2d _b, Dot2d _c, Dot2d ... _dots)
	{
		add(_a);
		add(_b);
		add(_c);
		
		if (_dots != null)
			for (Dot2d dot : _dots)
				add(new Dot2d(dot));
	}
	
	public <D extends Dot2d> boolean add(D _dot)
	{
		if (dots.contains(_dot)) return false;
		return dots.add(new Dot2d(_dot));
	}
	
	public boolean remove(Dot2d _dot)
	{
		if (dots.size() > 3)
			return dots.remove(_dot);
		else
			return false;
	}
	
	public int size()
	{
		return dots.size();
	}

	public Iterable<Dot2d> iterable2d()
	{
		return new Iterable<Dot2d>()
		{
			public Iterator<Dot2d> iterator()
			{
				return new Iterator<Dot2d>()
				{
					private Iterator<Dot2d> iterator = dots.iterator();
					
					public boolean hasNext()
					{
						return iterator.hasNext();
					}

					public Dot2d next()
					{
						return new Dot2d(iterator.next());
					}
				};
			}
		};
	}
}
