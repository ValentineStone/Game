package com.valentine.game.utils.math.geom;

import java.util.*;

import com.valentine.game.utils.math.*;

public class Triangulation
{
	private static class Tri2dExt extends Tri2d
	{
		public Circle2d circumcircle;
		public Seg2d ab;
		public Seg2d bc;
		public Seg2d ca;
		
		public Tri2dExt(Dot2d _a, Dot2d _b, Dot2d _c)
		{
			super(_a, _b, _c);
			ab = new Seg2d(a, b);
			bc = new Seg2d(b, a);
			ca = new Seg2d(c, a);
			circumcircle = getCircumcircle(this);
			
			if (circumcircle == null) throw new RuntimeException("What is dat tri!: " + toString());
		}
		
		public Tri2dExt(Tri2d _t)
		{
			this(_t.a, _t.b, _t.c);
		}
		
		public boolean contains(Dot2d _dot)
		{
			return a.equals(_dot) || b.equals(_dot) || c.equals(_dot);
		}
	}
	
	
	
	public static List<Tri2d> triangulate(Iterable<Dot2d> _dots)
	{
		List<Tri2dExt> trisExt = new ArrayList<>();
		
		// First: craft and add to triangulation a super triangle
		
		double maxx = 0;
		double maxy = 0;
		double minx = 0;
		double miny = 0;
		
		boolean firstTime = true;
		
		for (Dot2d dot : _dots)
		{
			if (firstTime)
			{
				firstTime = false;
				maxx = dot.x;
				maxy = dot.y;
				minx = dot.x;
				miny = dot.y;
				
			}
			
			if (dot.x > maxx)
				maxx = dot.x;
			else 
			if (dot.x < minx)
				minx = dot.x;
			
			if (dot.y > maxy)
				maxy = dot.y;
			else
			if (dot.y < miny)
				miny = dot.y;
		}
		
		/*
		
		double w = maxx - minx;
		double h = maxy - miny;
		
		double centerx = w / 2 + minx;
		double centery = h / 2 + miny;
		
		double a = Math.max(w, h);
		double superTriExtLen = a / Math.sqrt(3);
		double superTriHeight = (a / 2 + superTriExtLen) * Math.sqrt(3);
		
		Dot2d superTriDot1 =
			new Dot2d(centerx - a / 2 - superTriExtLen, miny);
		
		Dot2d superTriDot2 =
			new Dot2d(centerx, superTriHeight);
		
		Dot2d superTriDot3 =
			new Dot2d(centerx + a / 2 + superTriExtLen, miny);
		
		Tri2d superTri = new Tri2d(superTriDot1, superTriDot2, superTriDot3);
		
		*/
		
		double ext = 1;
		
		
		maxx += ext;
		maxy += ext;
		minx -= ext;
		miny -= ext;
		
		
		Dot2d d1 = new Dot2d(minx, miny);
		Dot2d d2 = new Dot2d(minx, maxy);
		Dot2d d3 = new Dot2d(maxx, miny);
		Dot2d d4 = new Dot2d(maxx + ext, maxy + ext);
		
		trisExt.add(new Tri2dExt(d1, d2, d3));
		trisExt.add(new Tri2dExt(d2, d3, d4));
		
		for (Dot2d dot : _dots)
			add(trisExt, dot);
		
		
		List<Tri2d> tris = new ArrayList<>(trisExt.size());
		
		for (Tri2dExt triExt : trisExt)
			if
			(
				!triExt.contains(d1) &&
				!triExt.contains(d2) &&
				!triExt.contains(d3) &&
				!triExt.contains(d4)
			)
				tris.add(new Tri2d(triExt));
		
		return tris;
	}
	
	private static void add(List<Tri2dExt> _trisExt, Dot2d _dot)
	{
		List<Tri2dExt> affectedTris = new ArrayList<>();
		
		for (Tri2dExt tri : _trisExt)
			if (Circle2d.contains(tri.circumcircle, _dot))
				affectedTris.add(tri);
		
		List<Dot2d> affectedDots = new ArrayList<>(affectedTris.size() * 3);
		
		for (Tri2dExt tri : affectedTris)
		{
			if (!affectedDots.contains(tri.a))
				affectedDots.add(tri.a);
			if (!affectedDots.contains(tri.b))
				affectedDots.add(tri.b);
			if (!affectedDots.contains(tri.c))
				affectedDots.add(tri.c);
		}
		
		_trisExt.removeAll(affectedTris);
		_trisExt.addAll(connectWithEach(affectedDots, _dot));
	}
	
	private static List<Tri2dExt> connectWithEach(List<Dot2d> _dots, Dot2d _dot)
	{
		List<Tri2dExt> trisExt = new ArrayList<>();
		
		Collections.sort
		(
			_dots,
			new Comparator<Dot2d>()
			{
				public int compare(Dot2d _o1, Dot2d _o2)
				{
					return Double.compare(MathExt.rotationMake(_dot, _o1), MathExt.rotationMake(_dot, _o2));
				}
			}
		);
		
		for (int i = 0; i < _dots.size() - 1; i++)
			trisExt.add(new Tri2dExt(_dots.get(i), _dots.get(i+1), _dot));
		trisExt.add(new Tri2dExt(_dots.get(0), _dots.get(_dots.size() - 1), _dot));
		
		return trisExt;
	}
}
