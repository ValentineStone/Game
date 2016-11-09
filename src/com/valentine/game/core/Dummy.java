package com.valentine.game.core;

import java.awt.Color;

public class Dummy implements BasicEntity
{
	private boolean fill = false;
	
	public void paint(Screen _screen)
	{
		_screen.setColor(Color.YELLOW);
		_screen.fillRect(0, 0, 120, 120);
		
		_screen.setColor(Color.BLUE);
		if (fill)
			_screen.fillRect(10, 10, 100, 100);
		else
			_screen.drawRect(10, 10, 100, 100);
	}
	
	public void update()
	{
		fill = fill ? false : true;
	}

}
