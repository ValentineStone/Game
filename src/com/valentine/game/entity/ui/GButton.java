package com.valentine.game.entity.ui;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.event.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class GButton extends EntityBasicAI implements MouseListener
{
	private enum State
	{
		DEFAULT,
		DEFAULT_HOVER,
		PRESSED,
		PRESSED_HOVER
	};
	
	private Stroke hovoerStroke = new BasicStroke(2);
	
	private State state = State.DEFAULT; 
	
	public GButton(Container _container)
	{
		super(_container);
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
		_screen.setColor(getDrawColor());
		_screen.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
		
		switch (state)
		{
			case PRESSED:
			case PRESSED_HOVER:
			case DEFAULT_HOVER:
				_screen.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
			default:
		}
	}

	public void update()
	{

	}

	public void mouseClicked(MouseEvent _mouseEvent)
	{
		
	}

	public void mousePressed(MouseEvent _mouseEvent)
	{
		if (isGettingHit(_mouseEvent.getX() - getContainer().getTrueX(),
				_mouseEvent.getY() - getContainer().getTrueY()))
		{
			
		}
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		
	}

	public void mouseEntered(MouseEvent _mouseEvent)
	{
		
	}

	public void mouseExited(MouseEvent _mouseEvent)
	{
		
	}
}
