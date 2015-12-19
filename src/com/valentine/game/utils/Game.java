package com.valentine.game.utils;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.valentine.game.Somegame;
import com.valentine.game.entity.Box;
import com.valentine.game.entity.Container;

public abstract class Game extends Box implements ComponentListener
{
	
	// Current instance of Game
	
	private static Game game;
	
	// selfexplanitory state switch
	
	private static boolean ready = false;
	
	public Game()
	{
		super(0,0,0,0);
		
		InputHandler.addComponentListener(this);
	}

	public static void init()
	{
		game = new Somegame();
		
	}
	
	public static Game instance()
	{
		return game;
	}
	
	public void assemble()
	{
		System.err.println("[Game].assemble()");
		Painter.start();
		Updater.start();
	}
	
	public static boolean isReady() {
		return ready;
	}

	protected static void setReady(boolean _ready) {
		ready = _ready;
	}

	public void paint()
	{
		if (isReady() == false) return;
		
		super.paint();
	}
	
	public void update()
	{
		if (isReady() == false) return;
		
		super.update();
	}

	public void componentResized(ComponentEvent _componentEvent)
	{
		
		setWidth(Display.getDimension().getWidth());
		setHeight(Display.getDimension().getHeight());
		
		if (isReady()) return;
		
		instance().assemble();
	}
	
	public void componentHidden(ComponentEvent _componentEvent)
	{}

	public void componentMoved(ComponentEvent _componentEvent)
	{}

	public void componentShown(ComponentEvent _componentEvent)
	{}
}
