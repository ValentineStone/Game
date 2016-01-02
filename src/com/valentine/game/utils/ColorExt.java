package com.valentine.game.utils;

import java.awt.Color;

public class ColorExt {
	public final static Color TRANSPARENT = new Color(0,0,0,0);
	public static Color randomColor(int _min, int _max)
	{
		return new Color(
					((int)(Math.random() * (_max - _min)) + _min),
					((int)(Math.random() * (_max - _min)) + _min),
					((int)(Math.random() * (_max - _min)) + _min)
					);
	}
}
