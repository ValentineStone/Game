package com.valentine.game.utils;

public class Ref<TYPE extends Object>
{
	private TYPE val = null;
	
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
	}
	
	public String toString()
	{
		return val.toString();
	}
}
