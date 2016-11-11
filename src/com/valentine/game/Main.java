package com.valentine.game;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.core.terminal.*;
import com.valentine.game.games.*;

public abstract class Main
{
	public static void main(String[] _args)
	{
		Screen screen = new SwingScreen();

		Cassette cassete = new Cassette(new FuzzySets(screen.getScreenSize()));

		Terminal terminal = new NotchTerminal();

		terminal.plugIn(cassete);
		terminal.plugIn(Input.instance());
		terminal.plugIn(screen);
	}
}
