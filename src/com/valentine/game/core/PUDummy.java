package com.valentine.game.core;

import com.valentine.game.core.basic.*;

public class PUDummy extends EntityNew
{
	NotchLoop nl;
	
	@Override
	public void update()
	{
		System.err.println("UPDATE " + (nl != null ? "F:" + nl.getFps() + ", U:" + nl.getUps() : "NULL") );
	}

	@Override
	public void paint()
	{
		System.err.println("PAINT " + (nl != null ? nl.getInterpolation() : "NULL"));
	}
	
	public void setNotchLoop(NotchLoop _nl)
	{
		nl = _nl;
	}

}
