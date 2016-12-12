package com.valentine.game;

import java.awt.Dimension;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.core.terminal.*;
import com.valentine.game.games.*;

public abstract class Main
{
	public static void main(String[] _args)
	{
		Screen screen = new SwingScreen(new Dimension(400, 400), false);

		Cassette cassete = new Cassette(new Lines0(screen.getScreenSize()));

		Terminal terminal = new NotchTerminal();

		terminal.plugIn(cassete);
		terminal.plugIn(Input.instance());
		terminal.plugIn(screen);
	}
}