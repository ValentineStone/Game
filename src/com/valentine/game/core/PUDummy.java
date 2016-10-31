package com.valentine.game.core;

public class PUDummy extends Entity
{
	NotchLoop nl;
	
	public void update()
	{
		System.err.println("UPDATE " + (nl != null ? "F:" + nl.getFps() + ", U:" + nl.getUps() : "NULL") );
	}

	public void paint()
	{
		System.err.println("PAINT " + (nl != null ? nl.getInterpolation() : "NULL"));
	}
	
	public void setNotchLoop(NotchLoop _nl)
	{
		nl = _nl;
	}

}
