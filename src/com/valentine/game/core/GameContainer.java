package com.valentine.game.core;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.games.*;

public class GameContainer extends Container implements ComponentListener
{	
	private static GameContainer game;
	
	public GameContainer()
	{
		super(null, 0, 0, 0, 0);
		
		Input.addComponentListener(this);
		
		Input.addComponentListener(new ComponentListener()
		{
			@Override
			public void componentShown(ComponentEvent _componentEvent) {}
			@Override
			public void componentHidden(ComponentEvent _componentEvent) {}
			@Override
			public void componentMoved(ComponentEvent _componentEvent) {}
			@Override
			public void componentResized(ComponentEvent _componentEvent)
			{
				Input.removeComponentListener(this);
				instance().componentResized(_componentEvent);
				instance().assemble();
				System.err.println("[Game].assemble()");
				//NotchLoop.play();
			}
		});
	}

	public static void init()
	{
		game = new FlowGame();
	}
	
	public static GameContainer instance()
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
	
	
	
	@Override
	public void update()
	{
		if (isUpdatable()) super.update();
	}
	
	@Override
	public void paint(Screen _screen)
	{
		if (isPaintable()) super.paint(_screen);
	}
	

	@Override
	public void componentResized(ComponentEvent _componentEvent)
	{
		setWidth(WindowOld.getDimension().getWidth());
		setHeight(WindowOld.getDimension().getHeight());
	}
	
	@Override
	public void componentHidden(ComponentEvent _componentEvent)
	{}

	@Override
	public void componentMoved(ComponentEvent _componentEvent)
	{}

	@Override
	public void componentShown(ComponentEvent _componentEvent)
	{}
}
