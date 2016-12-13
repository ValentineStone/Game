package com.valentine.game.utils.math.geom;

import java.util.*;

public class Triangulation
{
	public static List<Tri2d> triangulate(Iterable<Dot2d> _dots)
	{
		List<Tri2d> tris = new ArrayList<>();
		
		// First: craft and add to triangulation a super triangle
		
		double maxx = 0;
		double maxy = 0;
		double minx = 0;
		double miny = 0;
		
		for (Dot2d dot : _dots)
		{
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
		
		tris.add(superTri);
		
		return null;
	}
}
