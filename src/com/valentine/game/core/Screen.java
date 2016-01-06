package com.valentine.game.core;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.text.AttributedCharacterIterator;

public class Screen
{
	private static Graphics2D graphics;
	
	private static Font font;
	
	
	public static void init()
	
	{
		System.err.println("[Canvas]");
		
		try
		{
		     GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/PressStart2P.ttf")));
		}
		catch (IOException|FontFormatException e)
		{
		     System.err.println("IOException|FontFormatException!!!");
		}
		
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	    for ( int i = 0; i < fonts.length; i++ )
	    {
	      System.out.println(fonts[i]);
	    }
		
		font = new Font("Press Start 2P", Font.PLAIN, 12);
		
	}
	
	public static void setGraphics(Graphics2D _graphics)
	{
		graphics = _graphics;
		
		graphics.setFont(font);
		
		graphics.setRenderingHint(
							        RenderingHints.KEY_TEXT_ANTIALIASING,
							        RenderingHints.VALUE_TEXT_ANTIALIAS_ON
							        );
		
	}
	
	
	
	
	
	public static void localize(double _x, double _y)
	{
		translate(_x, _y);
	}
	
	public static void delocalize(double _x, double _y)
	{
		translate(-_x, -_y);
	}
	

	public static void clearRect(double _x, double _y, double _width, double _height)
	{
		graphics.clearRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public static void clipRect(double _x, double _y, double _width, double _height)
	{
		graphics.clipRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public static void copyArea(double _x, double _y, double _width, double _height, double _dx, double _dy)
	{
		graphics.copyArea((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_dx), (int)Math.round(_dy));
	}

	public static void dispose()
	{	
		graphics.dispose();
	}

	public static void drawArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{	
		graphics.drawArc((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_startAngle), (int)Math.round(_arcAngle));
	}

	public static boolean drawImage(Image _img, double _x, double _y, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), _bgcolor, _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, double _width, double _height, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), _observer);
	}

	public static boolean drawImage(Image _img, double _x, double _y, double _width, double _height, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), _bgcolor, _observer);
	}

	public static boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_dx1), (int)Math.round(_dy1), (int)Math.round(_dx2), (int)Math.round(_dy2), (int)Math.round(_sx1), (int)Math.round(_sy1), (int)Math.round(_sx2), (int)Math.round(_sy2), _observer);
	}

	public static boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage( _img, (int)Math.round(_dx1), (int)Math.round(_dy1), (int)Math.round(_dx2), (int)Math.round(_dy2), (int)Math.round(_sx1), (int)Math.round(_sy1), (int)Math.round(_sx2), (int)Math.round(_sy2), _bgcolor, _observer);
	}

	public static void drawLine(double _x1, double _y1, double _x2, double _y2)
	{
		graphics.drawLine((int)Math.round(_x1), (int)Math.round(_y1), (int)Math.round(_x2), (int)Math.round(_y2));
	}

	public static void drawOval(double _x, double _y, double _width, double _height)
	{
		graphics.drawOval((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public static void drawRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.drawRoundRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_arcWidth), (int)Math.round(_arcHeight));
	}
	
	public static void drawRect(double _x, double _y, double _width, double _height)
	{
		graphics.drawRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public static void drawString(String _str, double _x, double _y)
	{
		graphics.drawString(_str, (int)Math.round(_x), (int)Math.round(_y));
	}

	public static void drawString(AttributedCharacterIterator _iterator, double _x, double _y)
	{
		graphics.drawString(_iterator, (int)Math.round(_x), (int)Math.round(_y));
	}

	public static void fillArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{
		graphics.fillArc((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_startAngle), (int)Math.round(_arcAngle));
	}

	public static void fillOval(double _x, double _y, double _width, double _height)
	{
		graphics.fillOval((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public static void fillRect(double _x, double _y, double _width, double _height)
	{
		graphics.fillRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public static void fillRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.fillRoundRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_arcWidth), (int)Math.round(_arcHeight));
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
		graphics.setClip((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public static void setColor(Color _c)
	{
		graphics.setColor(_c);
	}

	public static void setFont(Font _font)
	{
		graphics.setFont(_font);
	}
	
	public static void resetFont()
	{
		graphics.setFont(font);
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
		graphics.translate(Math.round(_x), Math.round(_y));
	}

	public static Graphics2D getGraphics()
	{
		return graphics;
	}
	
	
	
}
