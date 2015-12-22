package com.valentine.game.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class Screen
{
	private static Graphics2D graphics;
	
	public static class COLORS
	{
		public final static Color TRANSPARENT = new Color(0,0,0,0);
	}
	
	public static void init()
	
	{
		System.err.println("[Canvas]");
	}
	
	public static void setGraphics(Graphics2D _graphics)
	{
		graphics = _graphics;
	}
	
	
	
	
	
	public static void localize(double _x, double _y)
	{
		translate(_x, _y);
	}
	
	public static void delocalize(double _x, double _y)
	{
		translate(-_x, -_y);
	}
	
	
	
	
	
	private static int round(double _value)
	{
		return (int)Math.round(_value);
	}
	
	
	
	
	public static Color randomColor(int _min, int _max)
	{
		return new Color(
					((int)(Math.random() * (_max - _min)) + _min),
					((int)(Math.random() * (_max - _min)) + _min),
					((int)(Math.random() * (_max - _min)) + _min)
					);
	}
	
	
	

	public static void clearRect(double _x, double _y, double _width, double _height)
	{
		graphics.clearRect((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height));
	}

	public static void clipRect(double _x, double _y, double _width, double _height)
	{
		graphics.clipRect((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height));
	}

	public static void copyArea(double _x, double _y, double _width, double _height, double _dx, double _dy)
	{
		graphics.copyArea((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height), (int)round(_dx), (int)round(_dy));
	}

	public static void dispose()
	{	
		graphics.dispose();
	}

	public static void drawArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{	
		graphics.drawArc((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height), (int)round(_startAngle), (int)round(_arcAngle));
	}

	public static boolean drawImage(Image _img, double _x, double _y, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)round(_x), (int)round(_y), _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)round(_x), (int)round(_y), _bgcolor, _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, double _width, double _height, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height), _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, double _width, double _height, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height), _bgcolor, _observer);
	}

	public static boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)round(_dx1), (int)round(_dy1), (int)round(_dx2), (int)round(_dy2), (int)round(_sx1), (int)round(_sy1), (int)round(_sx2), (int)round(_sy2), _observer);
	}

	public static boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage( _img, (int)round(_dx1), (int)round(_dy1), (int)round(_dx2), (int)round(_dy2), (int)round(_sx1), (int)round(_sy1), (int)round(_sx2), (int)round(_sy2), _bgcolor, _observer);
	}

	public static void drawLine(double _x1, double _y1, double _x2, double _y2)
	{
		graphics.drawLine((int)round(_x1), (int)round(_y1), (int)round(_x2), (int)round(_y2));
	}

	public static void drawOval(double _x, double _y, double _width, double _height)
	{
		graphics.drawOval((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height));
	}

	public static void drawRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.drawRoundRect((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height), (int)round(_arcWidth), (int)round(_arcHeight));
	}
	
	public static void drawRect(double _x, double _y, double _width, double _height)
	{
		graphics.drawRect((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height));
	}

	public static void drawString(String _str, double _x, double _y)
	{
		graphics.drawString(_str, (int)round(_x), (int)round(_y));
	}

	public static void drawString(AttributedCharacterIterator _iterator, double _x, double _y)
	{
		graphics.drawString(_iterator, (int)round(_x), (int)round(_y));
	}

	public static void fillArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{
		graphics.fillArc((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height), (int)round(_startAngle), (int)round(_arcAngle));
	}

	public static void fillOval(double _x, double _y, double _width, double _height)
	{
		graphics.fillOval((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height));
	}

	public static void fillRect(double _x, double _y, double _width, double _height)
	{
		graphics.fillRect((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height));
	}

	public static void fillRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.fillRoundRect((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height), (int)round(_arcWidth), (int)round(_arcHeight));
	}

	public static Shape getClip()
	{
		return graphics.getClip();
	}

	public static Rectangle getClipBounds()
	{
		return graphics.getClipBounds();
	}

	public static Color getColor()
	{
		return graphics.getColor();
	}

	public static Font getFont()
	{
		return graphics.getFont();
	}

	public static FontMetrics getFontMetrics(Font _f)
	{
		return graphics.getFontMetrics(_f);
	}

	public static void setClip(Shape _clip)
	{
		graphics.setClip(_clip);
	}

	public static void setClip(double _x, double _y, double _width, double _height)
	{
		graphics.setClip((int)round(_x), (int)round(_y), (int)round(_width), (int)round(_height));
	}

	public static void setColor(Color _c)
	{
		graphics.setColor(_c);
	}

	public static void setFont(Font _font)
	{
		graphics.setFont(_font);
	}

	public static void setPaintMode()
	{
		graphics.setPaintMode();
	}

	public static void setXORMode(Color _c1)
	{
		graphics.setXORMode(_c1);
	}

	public static void translate(double _x, double _y)
	{
		graphics.translate((int)round(_x), (int)round(_y));
	}
	
	
}
