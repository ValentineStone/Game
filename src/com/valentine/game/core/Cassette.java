package com.valentine.game.core;

public class Cassette implements Updatable, Paintable
{
	Game game;
	
	public Cassette(Game _game)
	{
		game = _game;
	}
	
	public void update()
	{
		game.update();
	}

	public void paint(Screen _screen)
	{
		game.paint(_screen);
	}

}