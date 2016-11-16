package com.valentine.game.utils;

import java.util.*;

public class Ref<TYPE extends Object>
{
	private TYPE val = null;
	
	private List<RefListener<TYPE>> listeners = new ArrayList<>();
	
	public Ref(TYPE _val)
	{
		val = _val;
	}

	public TYPE get()
	{
		return val;
	}

	public void set(TYPE _val)
	{
		val = _val;
		
		for (RefListener<TYPE> listener : listeners)
		{
			listener.change(this);
		}
	}

	public void addListener(RefListener<TYPE> _element)
	{
		listeners.add(_element);
	}

	public boolean removeListener(RefListener<TYPE> _element)
	{
		return listeners.remove(_element);
	}
	
	public String toString()
	{
		return val.toString();
	}
}
