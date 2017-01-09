package com.valentine.game.utils.painters;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.math.geom.*;

public final class GeometryPainter
{
	private GeometryPainter()
	{}


	
	
	
	public static void paint(Screen _screen, Geometry _geometry, Entity _e)
	{
		if      (_geometry instanceof Circle2d)
			paint(_screen, (Circle2d)_geometry);
		else if (_geometry instanceof Seg2d)
			paint(_screen, (Seg2d)_geometry);
		else if (_geometry instanceof Dot3d)
			paint(_screen, (Dot3d)_geometry);
		else if (_geometry instanceof Dot2d)
			paint(_screen, (Dot2d)_geometry);
		else if (_geometry instanceof Line2d)
			paint(_screen, (Line2d)_geometry, _e);
		else if (_geometry instanceof Tri2d)
			paint(_screen, (Tri2d)_geometry);
		else if (_geometry instanceof Mesh3d)
			paint(_screen, (Mesh3d)_geometry);
		else if (_geometry instanceof Mesh2d)
			paint(_screen, (Mesh2d)_geometry);
		else
			System.err.println("Unknown geometry: " + _geometry.getClass());
	}
	
	public static void paint(Screen _screen, Circle2d _circle)
	{
		_screen.drawOval(_circle.getX() - _circle.getR(), _circle.getY() - _circle.getR(), 2*_circle.getR(), 2*_circle.getR());
		paint(_screen, (Dot2d)_circle);
	}
	
	public static void paint(Screen _screen, Seg2d _seg)
	{
		_screen.drawLine(_seg.getD1X(), _seg.getD1Y(), _seg.getD2X(), _seg.getD2Y());
	}
	
	public static void paint(Screen _screen, Dot2d _dot)
	{
		double r = 5;
		paint(_screen, _dot, r);
	}
	
	public static void paint(Screen _screen, Dot2d _dot, double _r)
	{
		_screen.drawRect(_dot.getX() - _r, _dot.getY() - _r, 2*_r, 2*_r);
		_screen.drawDot(_dot.getX(), _dot.getY());
	}
	
	public static void paint(Screen _screen, Dot3d _dot)
	{
		double r = 5;
		paint(_screen, _dot, r);
		StringPainter.paint(_screen, _dot.getZ(), 2, _dot.getX() + r, _dot.getY() - r);
	}
	
	public static void paint(Screen _screen, Line2d _line, Entity _e)
	{
		double w = _e.getWidth() / 2;
		double h = _e.getHeight() / 2;
		
		double xUp = _line.xFromY(h);
		double yUp = _line.yFromX(w);
		
		double xLo = _line.xFromY(-h);
		double yLo = _line.yFromX(-w);
		
		if (Math.abs(xUp) < Math.abs(yUp))
			yUp = h;
		else
			xUp = w;
		
		if (Math.abs(xLo) < Math.abs(yLo))
			yLo = -h;
		else
			xLo = -w;
		
		_screen.drawLine(xUp, yUp, xLo, yLo);
	}
	
	public static void paint(Screen _screen, Tri2d _tri)
	{
		_screen.drawLine(_tri.getAx(), _tri.getAy(), _tri.getBx(), _tri.getBy());
		_screen.drawLine(_tri.getAx(), _tri.getAy(), _tri.getCx(), _tri.getCy());
		_screen.drawLine(_tri.getBx(), _tri.getBy(), _tri.getCx(), _tri.getCy());
	}
	
	public static void paint(Screen _screen, Mesh2d _mesh)
	{
		for (Dot2d dot : _mesh.iterable2d())
			paint(_screen, dot);
	}
	public static void paint(Screen _screen, Mesh3d _mesh)
	{
		for (Dot3d dot : _mesh.iterable3d())
			paint(_screen, dot);
	}
	
}
