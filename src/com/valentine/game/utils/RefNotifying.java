package com.valentine.game.utils;

import java.util.*;

public class RefNotifying<TYPE extends Object> extends Ref<TYPE>
{
	public RefNotifying(TYPE _val)
	{
		super(_val);
	}

	private List<RefListener<TYPE>> listeners = new ArrayList<>();

	public void set(TYPE _val)
	{
		super.set(_val);
		
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
		return get().toString();
	}
}
