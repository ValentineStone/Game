package com.valentine.game.core;

public class Terminal
{
	Input input;
	Screen screen;
	Cassette cassette;
	Loop loop;
	
	public Terminal(Loop _loop)
	{
		loop = _loop;
		loop.
	}
	
	public void plugIn(Input _input)
	{
		input = _input;
	}
	
	public void plugIn(Screen _screen)
	{
		screen = _screen;
	}
	
	public void plugIn(Cassette _cassette)
	{
		cassette = _cassette;
	}
}
