package com.valentine.game.entity.ui;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.event.*;
import java.util.*;

import javax.swing.event.MouseInputListener;

import com.valentine.game.core.Input;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class GButton extends EntityBasicAI implements MouseInputListener
{
	private enum State
	{
		DEFAULT,
		DEFAULT_HOVER,
		PRESSED,
		PRESSED_HOVER
	};
	
	private List<Runnable> listeners = new ArrayList<>();
	
	private int bufferedClicks = 0;
	
	private int charHeight;
	
	private Stroke hoverStroke;
	private Stroke pressStroke;
	
	private State state = State.DEFAULT;
	
	private String text;
	
	public GButton(Container _container, String _text)
	{
		super(_container);
		setText(_text);
		
		Input.addMouseListener(this);
		Input.addMouseMotionListener(this);
	}

	public void paint(Screen _screen)
	{
		if (charHeight == 0)
		{
			charHeight = _screen.getGraphics().getFontMetrics().getHeight();
			setText(getText());
			// ^ reset size according to font
			
			hoverStroke = new BasicStroke(charHeight / 3);
			pressStroke = new BasicStroke(charHeight / 2);
		}
		
		_screen.setColor(getFillColor());
		_screen.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
		_screen.setColor(getDrawColor());
		
		switch (state)
		{
			case PRESSED:
			case PRESSED_HOVER:
				_screen.setStroke(pressStroke);
				break;
			case DEFAULT_HOVER:
				_screen.setStroke(hoverStroke);
		}
		
		_screen.drawRoundRect(getX(), getY(), getWidth(), getHeight(), charHeight*2, charHeight*2);
		
		_screen.resetStroke();
		
		_screen.drawString(getText(), charHeight, charHeight*2);
	}

	public void update()
	{	
		for (;bufferedClicks-- > 0;)
		{
			for (Runnable listener : listeners)
			{
				listener.run();
			}
		}
	}
	
	
	
	
	

	public String getText()
	{
		return text;
	}

	public void setText(String _text)
	{
		text = _text;
		setWidth(charHeight * (getText().length() + 2));
		setHeight(charHeight * 3);
	}
	
	
	
	
	public boolean addListener(Runnable _listener)
	{
		return listeners.add(_listener);
	}
	
	public boolean removeListener(Runnable _listener)
	{
		return listeners.remove(_listener);
	}

	
	
	
	public boolean kill(Entity _killer)
	{
		Input.removeMouseListener(this);
		Input.removeMouseMotionListener(this);
		
		return super.kill(_killer);
	}
	
	
	
	
	
	
	public void mouseClicked(MouseEvent _mouseEvent)
	{
		
	}

	public void mousePressed(MouseEvent _mouseEvent)
	{
		if (isGettingHit(_mouseEvent.getX() - getContainer().getTrueX(),
				_mouseEvent.getY() - getContainer().getTrueY()))
		{
			state = State.PRESSED_HOVER;
			bufferedClicks++;
		}
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		if (isGettingHit(_mouseEvent.getX() - getContainer().getTrueX(),
				_mouseEvent.getY() - getContainer().getTrueY()))
		{
			state = State.DEFAULT_HOVER;
		}
	}

	public void mouseEntered(MouseEvent _mouseEvent)
	{}

	public void mouseExited(MouseEvent _mouseEvent)
	{}

	public void mouseDragged(MouseEvent _mouseEvent)
	{
		mouseMoved(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent)
	{
		if (isGettingHit(_mouseEvent.getX() - getContainer().getTrueX(),
				_mouseEvent.getY() - getContainer().getTrueY()))
		{
			state = state == State.PRESSED ? State.PRESSED_HOVER : State.DEFAULT_HOVER;
		}
		else
		{
			state = state == State.PRESSED_HOVER ? State.PRESSED : State.DEFAULT;
		}
	}
}
