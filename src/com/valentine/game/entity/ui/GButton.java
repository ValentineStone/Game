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

public class GButton extends GString implements MouseInputListener
{
	private enum State
	{
		DEFAULT, DEFAULT_HOVER, PRESSED, PRESSED_HOVER
	};

	private List<Runnable> listeners = new ArrayList<>();

	private int bufferedClicks = 0;

	private Stroke hoverStroke = new BasicStroke(2);
	private Stroke pressStroke = new BasicStroke(3);

	private State state = State.DEFAULT;
	
	private boolean enabled = true;
	
	public GButton(Container _container, String _text, double _x, double _y, double _width, double _height)
	{
		super(_container, _text, _height, _height, _height, _height);
		setPosition(_x, _y);
		setSize(_width, _height);
		setText(_text);

		Input.addMouseListener(this);
		Input.addMouseMotionListener(this);
	}

	
	public void paint(Screen _screen)
	{
		super.paint(_screen);

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
		while (bufferedClicks > 0)
		{
			bufferedClicks--;
			for (Runnable listener : listeners)
			{
				listener.run();
			}
		}
	}
	
	public void press()
	{
		if (enabled) bufferedClicks++;
	}

	public boolean addListener(Runnable _listener)
	{
		return listeners.add(_listener);
	}

	public boolean removeListener(Runnable _listener)
	{
		return listeners.remove(_listener);
	}
	
	
	
	public void setEnabled(boolean _enabled)
	{
		enabled = _enabled;
		
		if (enabled)
		{
			setDrawColor(Color.WHITE);
			setUpdatable(true);
		}
		else
		{
			setDrawColor(Color.GRAY);
			setUpdatable(false);
		}
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
		if (isGettingHit(_mouseEvent.getX() - getContainer().getTrueX(), _mouseEvent.getY() - getContainer().getTrueY()))
		{
			state = State.PRESSED_HOVER;
			press();
		}
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		if (isGettingHit(_mouseEvent.getX() - getContainer().getTrueX(), _mouseEvent.getY() - getContainer().getTrueY()))
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
		if (isGettingHit(_mouseEvent.getX() - getContainer().getTrueX(), _mouseEvent.getY() - getContainer().getTrueY()))
		{
			state = state == State.PRESSED ? State.PRESSED_HOVER : State.DEFAULT_HOVER;
		}
		else
		{
			state = state == State.PRESSED_HOVER ? State.PRESSED : State.DEFAULT;
		}
	}
}
