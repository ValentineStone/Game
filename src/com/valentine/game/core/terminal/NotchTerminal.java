package com.valentine.game.core.terminal;

import com.valentine.game.core.loop.*;

public class NotchTerminal extends Terminal
{
	public NotchTerminal()
	{
		super(new NotchLoop());
	}

}
