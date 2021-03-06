package com.valentine.game.core.screen;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.text.*;

import com.valentine.game.utils.math.geom.*;

public interface Screen
{
	void open();

	void flush();

	void localize(double _x, double _y);

	void delocalize(double _x, double _y);

	void clearRect(double _x, double _y, double _width, double _height);

	void clipRect(double _x, double _y, double _width, double _height);

	void drawArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle);

	void drawDot(double _x, double _y);

	boolean drawImage(Image _img, double _x, double _y, ImageObserver _observer);

	boolean drawImage(Image _img, double _x, double _y, Color _bgcolor, ImageObserver _observer);

	boolean drawImage(Image _img, double _x, double _y, double _width, double _height, ImageObserver _observer);

	boolean drawImage(Image _img, double _x, double _y, double _width, double _height, Color _bgcolor, ImageObserver _observer);

	boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, ImageObserver _observer);

	boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, Color _bgcolor, ImageObserver _observer);

	void drawLine(double _x1, double _y1, double _x2, double _y2);

	void drawOval(double _x, double _y, double _width, double _height);

	void drawRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight);

	void drawRect(double _x, double _y, double _width, double _height);

	void drawString(String _str, double _x, double _y);

	void drawString(AttributedCharacterIterator _iterator, double _x, double _y);

	void fillArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle);

	void fillOval(double _x, double _y, double _width, double _height);

	void fillRect(double _x, double _y, double _width, double _height);

	void fillRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight);

	Rectangle2D.Double getClip();

	Rectangle getClipBounds();

	Color getColor();

	Font getFont();

	FontMetrics getFontMetrics(Font _f);

	void setClip(Rectangle2D.Double _rect);

	void setClip(double _x, double _y, double _width, double _height);

	void setColor(Color _c);

	void setFont(Font _font);

	void resetFont();

	void setPaintMode();

	void setXORMode(Color _c1);

	void translate(double _x, double _y);

	Graphics2D getGraphics();

	void setStroke(Stroke _stroke);

	void resetStroke();

	Dimension getScreenSize();
	
	default void drawDot(Dot2d _d)
	{
		if (_d != null)
			drawDot(_d.getX(), _d.getY());
	}
	
	default void drawDot(Dot2d _d, double _r)
	{
		if (_d != null)
			drawOval(_d.getX() - _r, _d.getY() - _r, 2 * _r, 2 * _r);
	}
	
	default void drawLine(Dot2d _d1, Dot2d _d2)
	{
		if (_d1 != null && _d2 != null)
			drawLine(_d1.getX(), _d1.getY(), _d2.getX(), _d2.getY());
	}
	
	default void drawLineOrDot(Dot2d _d1, Dot2d _d2, double _r)
	{
		if (_d1 != null)
		{
			if (_d2 != null)
				drawLine(_d1.getX(), _d1.getY(), _d2.getX(), _d2.getY());
			else
				drawDot(_d1, _r);
		}
		else if (_d2 != null)
			drawDot(_d2, _r);
	}
	
	default void drawLines(Iterable<Dot2d> _itr)
	{
		Dot2d d1 = null;
		Dot2d d2 = null;
		boolean first = true;
		
		for (Dot2d d : _itr)
		{
			drawDot(d);
			
			if (first)
			{
				d1 = d;
				first = false;
				continue;
			}
			else
			{
				d2 = d;
				drawLine(d2, d1);
				d1 = d2;
			}
		}
	}
	
	default void drawLinesAndDots(Iterable<Dot2d> _itr, double _r)
	{
		Dot2d d1 = null;
		
		for (Dot2d d2 : _itr)
		{
			drawLineOrDot(d1, d2, _r);
			d1 = d2;
		}
		
		drawDot(d1, _r);
	}
}