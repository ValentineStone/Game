package com.valentine.game;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.valentine.game.entity.Container;
import com.valentine.game.utils.Display;
import com.valentine.game.utils.InputHandler;
import com.valentine.game.utils.Painter;
import com.valentine.game.utils.Updater;

public abstract class Game extends Container implements ComponentListener{
	
	// Current instance of Game
	
	private static Game game;
	
	// Dimensions of the on screen game
	
	protected static Dimension dimension;
	
	// selfexplanitory state switch
	
	private static boolean ready = false;
	
	public Game()
	{
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
	
	public static Dimension getDimension()
	{
		return dimension;
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
		
		dimension = Display.getDimension();
		
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
