package com.valentine.game.utils;

public interface RefListener<TYPE extends Object>
{
	public void change(Ref<TYPE> _ref);
}
