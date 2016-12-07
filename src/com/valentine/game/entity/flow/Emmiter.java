package com.valentine.game.entity.flow;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.entity.ui.*;

public class Emmiter extends EntityBasicAI implements MouseWheelListener
{
	Handle handle;

	public Emmiter(Container _container)
	{
		super(_container);

		handle = new Handle(_container);
	}

	public void paint(Screen _screen)
	{}

	public void update()
	{}

	public void mouseWheelMoved(MouseWheelEvent _evt)
	{
		setWidth(getWidth() + _evt.getUnitsToScroll());
	}

	public void kill()
	{
		handle.kill();
	}

}

class Handle extends Circle implements MouseWheelListener
{
	private DragHandler dragger;

	public int dSize;

	public Handle(Container _container)
	{
		super(_container, 10);
		Input.addMouseWheelListener(this);
	}

	public void mouseWheelMoved(MouseWheelEvent _e)
	{
		dSize += _e.getUnitsToScroll();
	}

	public void kill()
	{
		Input.removeMouseWheelListener(this);
	}
}
