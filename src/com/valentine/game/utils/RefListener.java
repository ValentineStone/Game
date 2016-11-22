package com.valentine.game.utils;

public interface RefListener<TYPE extends Object>
{
	public void change(RefNotifying<TYPE> _ref);
}
