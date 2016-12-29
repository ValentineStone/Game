package com.valentine.game.utils.math.geom;

import java.util.*;

public class Mesh3d extends Mesh2d
{
	public Mesh3d()
	{}
	
	public Mesh3d(Dot3d _a, Dot3d _b, Dot3d _c, Dot3d ... _dots)
	{
		super();
		
		add(_a);
		add(_b);
		add(_c);
		
		if (_dots != null)
			for (Dot3d dot : _dots)
				add(new Dot3d(dot));
	}
	
	public boolean add(Dot3d _dot)
	{
		if (dots.contains(_dot)) return false;
		return dots.add(new Dot3d(_dot));
	}
	
	public boolean add(Dot2d _dot)
	{
		return false;
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

	public Iterable<Dot3d> iterable3d()
	{
		return new Iterable<Dot3d>()
		{
			public Iterator<Dot3d> iterator()
			{
				return new Iterator<Dot3d>()
				{
					private Iterator<Dot2d> iterator = dots.iterator();
					
					public boolean hasNext()
					{
						return iterator.hasNext();
					}

					public Dot3d next()
					{
						return new Dot3d((Dot3d) iterator.next());
					}
				};
			}
		};
	}
	
}
