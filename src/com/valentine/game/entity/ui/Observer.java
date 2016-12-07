package com.valentine.game.entity.ui;

import java.util.*;

public class Observer
{
	private List<Runnable> listeners = new ArrayList<>();
	
	public void update()
	{
		for (Runnable listener : listeners)
		{
			listener.run();
		}
	}
	
	public boolean addListener(Runnable _listener)
	{
		return listeners.add(_listener);
	}

	public boolean removeListener(Runnable _listener)
	{
		return listeners.remove(_listener);
	}
}