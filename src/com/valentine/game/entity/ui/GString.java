package com.valentine.game.entity.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;

public class GString extends EntityBasicAI
{
	private int charHeight = 0;

	private Stroke hoverStroke = new BasicStroke(2);
	private Stroke pressStroke = new BasicStroke(3);

	private String text;
	
	private double textx;
	private double texty;
	
	private boolean backgroundVisible = false;
	private boolean borderVisible = false;
	
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

		_screen.setColor(getFillColor());
		_screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		_screen.setColor(getDrawColor());
		
		_screen.setClip(getX(), getY(), getWidth(), getHeight());
		_screen.drawString(getText(), textx, texty);
		_screen.setClip(null);

		switch (state)
		{
			case PRESSED:
			case PRESSED_HOVER:
				_screen.setStroke(pressStroke);
				break;
			case DEFAULT_HOVER:
				_screen.setStroke(hoverStroke);
		}

		_screen.drawRect(getX(), getY(), getWidth(), getHeight());

		_screen.resetStroke();		
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
	
	private void calcTextPos()
	{
		textx = getCenterX() - (charHeight * getText().length()) / 2.;
		texty = getCenterY() + charHeight / 2.;
	}
}
