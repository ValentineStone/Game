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
		else if (_geometry instanceof Dot2d)
			paint(_screen, (Dot2d)_geometry);
		else if (_geometry instanceof Line2d)
			paint(_screen, (Line2d)_geometry, _e);
		else if (_geometry instanceof Tri2d)
			paint(_screen, (Tri2d)_geometry);
		else
			System.err.println("Unknown geometry: " + _geometry.getClass());
	}
	
	public static void paint(Screen _screen, Circle2d _circle)
	{
		_screen.drawOval(_circle.x - _circle.r, _circle.y - _circle.r, 2*_circle.r, 2*_circle.r);
		paint(_screen, (Dot2d)_circle);
	}
	
	public static void paint(Screen _screen, Seg2d _seg)
	{
		_screen.drawLine(_seg.getD1X(), _seg.getD1Y(), _seg.getD2X(), _seg.getD2Y());
	}
	
	public static void paint(Screen _screen, Dot2d _dot)
	{
		double r = 5;
		_screen.drawRect(_dot.x - r, _dot.y - r, 2*r, 2*r);
		_screen.drawDot(_dot.x, _dot.y);
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
		
		Circle2d c = Tri2d.getCircumcircle(_tri);
		
		if (c != null)
			paint(_screen, Tri2d.getCircumcircle(_tri));
	}
	
}
