package com.valentine.game.utils;

import java.awt.Color;

public class ColorExt {
	public final static Color TRANSPARENT = new Color(0,0,0,0);
	public final static Color ORANGE = new Color(255,90,0);
	
	public static Color randomColor(int _min, int _max)
	{
		return new Color(
					((int)(Math.random() * (_max - _min)) + _min),
					((int)(Math.random() * (_max - _min)) + _min),
					((int)(Math.random() * (_max - _min)) + _min)
					);
	}
	
	public static Color makeTransparent(Color _color, int _transparency)
	{
		return new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), _transparency);
	}
	
	public static Color opposite(Color _color)
	{
		return new Color(255 - _color.getRed(), 255 - _color.getGreen(), 255 - _color.getBlue());
	}
}
