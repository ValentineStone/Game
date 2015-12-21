package com.valentine.game.utils;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.valentine.game.Somegame;
import com.valentine.game.entity.Container;

public abstract class Game extends Container implements ComponentListener
{	
	private static Game game;
	
	public Game()
	{
		super(null, 0, 0, 0, 0);
		
		Input.addComponentListener(this);
		
		Input.addComponentListener(new ComponentListener()
		{
			public void componentShown(ComponentEvent _componentEvent) {}
			public void componentHidden(ComponentEvent _componentEvent) {}
			public void componentMoved(ComponentEvent _componentEvent) {}
			public void componentResized(ComponentEvent _componentEvent)
			{
				Input.removeComponentListener(this);
				instance().componentResized(_componentEvent);
				instance().assemble();
				Looper.play();
			}
		});
	}

	public static void init()
	{
		game = new Somegame();
		
	}
	
	public static Game instance()
	{
		return game;
	}
	
	public abstract void assemble();

	public void paint()
	{
		super.paint();
	}
	
	public void update()
	{
		super.update();
	}

	public void componentResized(ComponentEvent _componentEvent)
	{
		setWidth(Window.getDimension().getWidth());
		setHeight(Window.getDimension().getHeight());
	}
	
	public void componentHidden(ComponentEvent _componentEvent)
	{}

	public void componentMoved(ComponentEvent _componentEvent)
	{}

	public void componentShown(ComponentEvent _componentEvent)
	{}
}
