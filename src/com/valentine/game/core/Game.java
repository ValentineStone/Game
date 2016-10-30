package com.valentine.game.core;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.games.*;

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
				System.err.println("[Game].assemble()");
				Looper.play();
			}
		});
	}

	public static void init()
	{
		game = new FlowGame();
	}
	
	public static Game instance()
	{
		return game;
	}
	
	public void assemble()
	{		
		for (Entity entity : this)
		{
			entity.kill(this);
		}
		
		getItems().clear();
		getAdditions().clear();
		getDeletions().clear();
	}
	
	
	
	public void update()
	{
		if (isUpdatable()) super.update();
	}
	
	public void paint()
	{
		if (isPaintable()) super.paint();
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
