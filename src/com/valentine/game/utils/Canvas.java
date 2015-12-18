package com.valentine.game.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import com.valentine.game.entity.Layer;

public class Canvas
{
	private static Graphics graphics;
	
	public static void init()
	
	{
		System.err.println("[Canvas]");
	}
	
	public static void setGraphics(Graphics _graphics)
	{
		graphics = _graphics;
	}
	
	public static void localize(Layer _layer)
	{
		translate(_layer.getX(), _layer.getY());
	}
	
	public static void delocalize(Layer _layer)
	{
		translate(-_layer.getX(), -_layer.getY());
	}

	public static void clearRect(double _x, double _y, double _width, double _height)
	{
		graphics.clearRect((int)_x, (int)_y, (int)_width, (int)_height);
	}

	public static void clipRect(double _x, double _y, double _width, double _height)
	{
		graphics.clipRect((int)_x, (int)_y, (int)_width, (int)_height);
	}

	public static void copyArea(double _x, double _y, double _width, double _height, double _dx, double _dy)
	{
		graphics.copyArea((int)_x, (int)_y, (int)_width, (int)_height, (int)_dx, (int)_dy);
	}

	public static void dispose()
	{	
		graphics.dispose();
	}

	public static void drawArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{	
		graphics.drawArc((int)_x, (int)_y, (int)_width, (int)_height, (int)_startAngle, (int)_arcAngle);
	}

	public static boolean drawImage(Image _img, double _x, double _y, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)_x, (int)_y, _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)_x, (int)_y, _bgcolor, _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, double _width, double _height, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)_x, (int)_y, (int)_width, (int)_height, _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, double _width, double _height, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)_x, (int)_y, (int)_width, (int)_height, _bgcolor, _observer);
	}

	public static boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)_dx1, (int)_dy1, (int)_dx2, (int)_dy2, (int)_sx1, (int)_sy1, (int)_sx2, (int)_sy2, _observer);
	}

	public static boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage( _img, (int)_dx1, (int)_dy1, (int)_dx2, (int)_dy2, (int)_sx1, (int)_sy1, (int)_sx2, (int)_sy2, _bgcolor, _observer);
	}

	public static void drawLine(double _x1, double _y1, double _x2, double _y2)
	{
		drawLine((int)_x1, (int)_y1, (int)_x2, (int)_y2);
	}

	public static void drawOval(double _x, double _y, double _width, double _height)
	{
		graphics.drawOval((int)_x, (int)_y, (int)_width, (int)_height);
	}

	public static void drawRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.drawRoundRect((int)_x, (int)_y, (int)_width, (int)_height, (int)_arcWidth, (int)_arcHeight);
	}

	public static void drawString(String _str, double _x, double _y)
	{
		graphics.drawString(_str, (int)_x, (int)_y);
	}

	public static void drawString(AttributedCharacterIterator _iterator, double _x, double _y)
	{
		graphics.drawString(_iterator, (int)_x, (int)_y);
	}

	public static void fillArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{
		graphics.fillArc((int)_x, (int)_y, (int)_width, (int)_height, (int)_startAngle, (int)_arcAngle);
	}

	public static void fillOval(double _x, double _y, double _width, double _height)
	{
		graphics.fillOval((int)_x, (int)_y, (int)_width, (int)_height);
	}

	public static void fillRect(double _x, double _y, double _width, double _height)
	{
		graphics.fillRect((int)_x, (int)_y, (int)_width, (int)_height);
	}

	public static void fillRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.fillRoundRect((int)_x, (int)_y, (int)_width, (int)_height, (int)_arcWidth, (int)_arcHeight);
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
		graphics.setClip((int)_x, (int)_y, (int)_width, (int)_height);
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
		graphics.translate((int)_x, (int)_y);
	}
	
	
}
