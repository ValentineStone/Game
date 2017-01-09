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
			ab = new Seg2d(getA(), getB());
			bc = new Seg2d(getB(), getA());
			ca = new Seg2d(getC(), getA());
			circumcircle = getCircumcircle(this);
			
			if (circumcircle == null) throw new RuntimeException("What is dat tri!: " + toString());
		}
		
		public Tri2dExt(Tri2d _t)
		{
			this(_t.getA(), _t.getB(), _t.getC());
		}
		
		public boolean contains(Dot2d _dot)
		{
			return getA().equals(_dot) || getB().equals(_dot) || getC().equals(_dot);
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
				maxx = dot.getX();
				maxy = dot.getY();
				minx = dot.getX();
				miny = dot.getY();
				
			}
			
			if (dot.getX() > maxx)
				maxx = dot.getX();
			else 
			if (dot.getX() < minx)
				minx = dot.getX();
			
			if (dot.getY() > maxy)
				maxy = dot.getY();
			else
			if (dot.getY() < miny)
				miny = dot.getY();
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
			if (!affectedDots.contains(tri.getA()))
				affectedDots.add(tri.getA());
			if (!affectedDots.contains(tri.getB()))
				affectedDots.add(tri.getB());
			if (!affectedDots.contains(tri.getC()))
				affectedDots.add(tri.getC());
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static class Tri3dExt extends Tri3d
	{
		public Circle2d circumcircle;
		public Seg2d ab;
		public Seg2d bc;
		public Seg2d ca;
		
		public Tri3dExt(Dot3d _a, Dot3d _b, Dot3d _c)
		{
			super(_a, _b, _c);
			ab = new Seg2d(getA(), getB());
			bc = new Seg2d(getB(), getA());
			ca = new Seg2d(getC(), getA());
			circumcircle = getCircumcircle(this);
			
			if (circumcircle == null) throw new RuntimeException("What is dat tri!: " + toString());
		}
		
		public Tri3dExt(Tri3d _t)
		{
			this(_t.getA(), _t.getB(), _t.getC());
		}
		
		public boolean contains(Dot3d _dot)
		{
			return getA().equals(_dot) || getB().equals(_dot) || getC().equals(_dot);
		}
	}
	
	
	
	public static List<Tri3d> triangulate(Iterable<Dot3d> _dots, boolean _3d)
	{
		List<Tri3dExt> trisExt = new ArrayList<>();
		
		// First: craft and add to triangulation a super triangle
		
		double maxx = 0;
		double maxy = 0;
		double minx = 0;
		double miny = 0;
		
		boolean firstTime = true;
		
		for (Dot3d dot : _dots)
		{
			if (firstTime)
			{
				firstTime = false;
				maxx = dot.getX();
				maxy = dot.getY();
				minx = dot.getX();
				miny = dot.getY();
				
			}
			
			if (dot.getX() > maxx)
				maxx = dot.getX();
			else 
			if (dot.getX() < minx)
				minx = dot.getX();
			
			if (dot.getY() > maxy)
				maxy = dot.getY();
			else
			if (dot.getY() < miny)
				miny = dot.getY();
		}
		
		/*
		
		double w = maxx - minx;
		double h = maxy - miny;
		
		double centerx = w / 2 + minx;
		double centery = h / 2 + miny;
		
		double a = Math.max(w, h);
		double superTriExtLen = a / Math.sqrt(3);
		double superTriHeight = (a / 2 + superTriExtLen) * Math.sqrt(3);
		
		Dot3d superTriDot1 =
			new Dot3d(centerx - a / 2 - superTriExtLen, miny);
		
		Dot3d superTriDot2 =
			new Dot3d(centerx, superTriHeight);
		
		Dot3d superTriDot3 =
			new Dot3d(centerx + a / 2 + superTriExtLen, miny);
		
		Tri3d superTri = new Tri3d(superTriDot1, superTriDot2, superTriDot3);
		
		*/
		
		double ext = 1;
		
		
		maxx += ext;
		maxy += ext;
		minx -= ext;
		miny -= ext;
		
		
		Dot3d d1 = new Dot3d(minx, miny, 0);
		Dot3d d2 = new Dot3d(minx, maxy, 0);
		Dot3d d3 = new Dot3d(maxx, miny, 0);
		Dot3d d4 = new Dot3d(maxx + ext, maxy + ext, 0);
		
		trisExt.add(new Tri3dExt(d1, d2, d3));
		trisExt.add(new Tri3dExt(d2, d3, d4));
		
		for (Dot3d dot : _dots)
			add(trisExt, dot);
		
		
		List<Tri3d> tris = new ArrayList<>(trisExt.size());
		
		for (Tri3dExt triExt : trisExt)
			if
			(
				!triExt.contains(d1) &&
				!triExt.contains(d2) &&
				!triExt.contains(d3) &&
				!triExt.contains(d4)
			)
				tris.add(new Tri3d(triExt));
		
		return tris;
	}
	
	private static void add(List<Tri3dExt> _trisExt, Dot3d _dot)
	{
		List<Tri3dExt> affectedTris = new ArrayList<>();
		
		for (Tri3dExt tri : _trisExt)
			if (Circle2d.contains(tri.circumcircle, _dot))
				affectedTris.add(tri);
		
		List<Dot3d> affectedDots = new ArrayList<>(affectedTris.size() * 3);
		
		for (Tri3dExt tri : affectedTris)
		{
			if (!affectedDots.contains(tri.getA()))
				affectedDots.add(tri.getA());
			if (!affectedDots.contains(tri.getB()))
				affectedDots.add(tri.getB());
			if (!affectedDots.contains(tri.getC()))
				affectedDots.add(tri.getC());
		}
		
		_trisExt.removeAll(affectedTris);
		_trisExt.addAll(connectWithEach(affectedDots, _dot));
	}
	
	private static List<Tri3dExt> connectWithEach(List<Dot3d> _dots, Dot3d _dot)
	{
		List<Tri3dExt> trisExt = new ArrayList<>();
		
		Collections.sort
		(
			_dots,
			new Comparator<Dot3d>()
			{
				public int compare(Dot3d _o1, Dot3d _o2)
				{
					return Double.compare(MathExt.rotationMake(_dot, _o1), MathExt.rotationMake(_dot, _o2));
				}
			}
		);
		
		for (int i = 0; i < _dots.size() - 1; i++)
			trisExt.add(new Tri3dExt(_dots.get(i), _dots.get(i+1), _dot));
		trisExt.add(new Tri3dExt(_dots.get(0), _dots.get(_dots.size() - 1), _dot));
		
		return trisExt;
	}
}
