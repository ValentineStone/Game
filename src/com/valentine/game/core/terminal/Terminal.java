package com.valentine.game.core.terminal;

import com.valentine.game.core.*;
import com.valentine.game.core.interfaces.*;
import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;

public class Terminal implements PaintableSmart, Updatable
{
	protected Input input;
	protected Screen screen;
	protected Cassette cassette;
	protected Loop loop;

	public Terminal()
	{
		this(new BasicLoop(25));
	}

	protected Terminal(Loop _loop)
	{
		loop = _loop;
		loop.setPaintable(this);
		loop.setUpdatable(this);
		loop.enable();
	}

	public void plugIn(Input _input)
	{
		input = _input;
	}

	public void plugIn(Screen _screen)
	{
		screen = _screen;
	}

	public void plugIn(Cassette _cassette)
	{
		cassette = _cassette;
	}

	public void update()
	{
		if (input != null)
		{
			input.update();
		}
		if (cassette != null)
		{
			cassette.update();
		}
	}

	public void paint()
	{
		if (cassette != null && screen != null)
		{
			screen.open();
			cassette.paint(screen);
			screen.flush();
		}
	}
}
