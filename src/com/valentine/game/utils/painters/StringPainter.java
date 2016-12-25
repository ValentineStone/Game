package com.valentine.game.utils.painters;

import com.valentine.game.core.screen.*;

public final class StringPainter
{
	private StringPainter()
	{}

	public static void paint(Screen _screen, double _double, int _presicion, double _x, double _y)
	{
		_screen.drawString(String.format("%."+_presicion+"f", _double), _x, _y);
	}
}
