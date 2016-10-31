package com.valentine.game.core;

import java.awt.*;
import java.awt.image.*;
import java.text.*;

public interface ScreenInterface
{
	public void localize(double _x, double _y);
	
	public void delocalize(double _x, double _y);

	public void clearRect(double _x, double _y, double _width, double _height);

	public void clipRect(double _x, double _y, double _width, double _height);

	public void copyArea(double _x, double _y, double _width, double _height, double _dx, double _dy);

	public void drawArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle);
	
	public void drawDot(double _x, double _y);

	public boolean drawImage(Image _img, double _x, double _y, ImageObserver _observer);

	public boolean drawImage(Image _img, double _x, double _y, Color _bgcolor, ImageObserver _observer);

	public boolean drawImage(Image _img, double _x, double _y, double _width, double _height, ImageObserver _observer);

	public boolean drawImage(Image _img, double _x, double _y, double _width, double _height, Color _bgcolor, ImageObserver _observer);

	public boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, ImageObserver _observer);

	public boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, Color _bgcolor, ImageObserver _observer);

	public void drawLine(double _x1, double _y1, double _x2, double _y2);

	public void drawOval(double _x, double _y, double _width, double _height);

	public void drawRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight);
	
	public void drawRect(double _x, double _y, double _width, double _height);

	public void drawString(String _str, double _x, double _y);

	public void drawString(AttributedCharacterIterator _iterator, double _x, double _y);

	public void fillArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle);

	public void fillOval(double _x, double _y, double _width, double _height);

	public void fillRect(double _x, double _y, double _width, double _height);

	public void fillRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight);

	public Shape getClip();

	public Rectangle getClipBounds();

	public Color getColor();

	public Font getFont();

	public FontMetrics getFontMetrics(Font _f);

	public void setClip(Shape _clip);

	public void setClip(double _x, double _y, double _width, double _height);

	public void setColor(Color _c);

	public void setFont(Font _font);
	
	public void resetFont();

	public void setPaintMode();

	public void setXORMode(Color _c1);

	public void translate(double _x, double _y);

	public Graphics2D getGraphics();
	
	public void setStroke(Stroke _stroke);
	
	Dimension getScreenSize();
}