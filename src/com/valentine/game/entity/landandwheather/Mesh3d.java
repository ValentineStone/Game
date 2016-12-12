package com.valentine.game.entity.landandwheather;

import java.awt.*;
import java.util.List;
import java.util.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class Mesh3d implements SurfaceScan
{
	private final List<Dot3d> dots = new ArrayList<>();
	private Tri3d _tri;
	private Dot3d _triDot;
	
	public Mesh3d(Dot3d _a, Dot3d _b, Dot3d _c, Dot3d ... _dots)
	{
		dots.add(_a);
		dots.add(_b);
		dots.add(_c);
		
		for (Dot3d dot : _dots)
			add(dot);
	}
	
	public boolean add(Dot3d _dot)
	{
		return dots.add(_dot);
	}
	
	public boolean remove(Dot3d _dot)
	{
		if (dots.size() > 3)
			return dots.remove(_dot);
		else
			return false;
	}

	public Double valueAt(double _x, double _y)
	{
		evalTri(new Dot2d(_x, _y));
		return _triDot.z;
	}
	
	private void evalTri(Dot2d _dot)
	{
		Dot3d a = dots.get(0);
		Dot3d b = dots.get(1);
		Dot3d c = dots.get(2);
		
		double dista = MathExt.distanceMake(a, _dot);
		double distb = MathExt.distanceMake(b, _dot);
		double distc = MathExt.distanceMake(c, _dot);
		
		for (int i = 3; i < dots.size(); i++)
		{
			double dist = MathExt.distanceMake(dots.get(i), _dot);
			
			if (dist < dista)
			{
				a = dots.get(i);
				dista = dist;
			}
			else if (dist < distb)
			{
				b = dots.get(i);
				distb = dist;
			}
			else if (dist < distc)
			{
				c = dots.get(i);
				distc = dist;
			}
			
			_tri = new Tri3d(a, b, c);
			_triDot = new Dot3d(_dot.x, _dot.y, _tri.valueAt(_dot.x, _dot.y));
		}
	}
	
	
	public void paint(Screen _screen)
	{
		_screen.setColor(Color.WHITE);
		for (Dot3d dot : dots)
		{
			_screen.drawOval(dot.x - 2, dot.y - 2, 4, 4);
			_screen.drawString(String.valueOf(dot.z), dot.x+2, dot.y+2);
		}
		
		if (_tri != null)
		{
			_screen.drawLine(_tri.a.x, _tri.a.y, _tri.b.x, _tri.b.y);
			_screen.drawLine(_tri.b.x, _tri.b.y, _tri.c.x, _tri.c.y);
			_screen.drawLine(_tri.c.x, _tri.c.y, _tri.a.x, _tri.a.y);
		}
		
		if (_triDot != null)
		{
			_screen.drawString(String.valueOf(_triDot.z), _triDot.x, _triDot.y);
		}
	}
	
	

	
	public static void main(String[] _args)
	{
		new Mesh3d(new Dot3d(0,0,1), new Dot3d(0,1,1), new Dot3d(1,0,1), new Dot3d(1,1,1));
	}
	
}
