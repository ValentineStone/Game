package com.valentine.game.entity.ui;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class GString extends EntityBasicAI
{
	protected int charHeight = 0;

	protected String text;
	
	protected double textx;
	protected double texty;
	
	private boolean backgroundVisible = true;
	private boolean borderVisible = true;
	
	public GString(Container _container, String _text, double _x, double _y, double _width, double _height)
	{
		super(_container);
		setPosition(_x, _y);
		setSize(_width, _height);
		setText(_text);
	}

	public void paint(Screen _screen)
	{
		if (charHeight == 0)
		{
			charHeight = _screen.getGraphics().getFontMetrics().getHeight();
			calcTextPos();
		}

		if (backgroundVisible)
		{
			_screen.setColor(getFillColor());
			_screen.fillRect(getX(), getY(), getWidth(), getHeight());
		}
		
		_screen.setColor(getDrawColor());
		
		
		_screen.setClip(getX(), getY(), getWidth(), getHeight());
		_screen.drawString(getText(), textx, texty);
		_screen.setClip(null);
		
		if (borderVisible)
		{
			_screen.drawRect(getX(), getY(), getWidth(), getHeight());
		}
	}

	public void update()
	{
		
	}

	public String getText()
	{
		return text;
	}

	public void setText(String _text)
	{
		text = _text;
		calcTextPos();
	}
	
	protected void calcTextPos()
	{
		textx = getCenterX() - (charHeight * getText().length()) / 2.;
		texty = getCenterY() + charHeight / 2.;
	}

	public boolean isBackgroundVisible()
	{
		return backgroundVisible;
	}

	public void setBackgroundVisible(boolean _backgroundVisible)
	{
		backgroundVisible = _backgroundVisible;
	}

	public boolean isBorderVisible()
	{
		return borderVisible;
	}

	public void setBorderVisible(boolean _borderVisible)
	{
		borderVisible = _borderVisible;
	}
	
	
}
