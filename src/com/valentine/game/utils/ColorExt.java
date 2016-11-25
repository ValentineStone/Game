package com.valentine.game.utils;

import java.awt.*;

public class ColorExt
{
	public final static Color TRANSPARENT = new Color(0, 0, 0, 0);
	public final static Color ORANGE = new Color(255, 90, 0);
	public final static Color GREEN = new Color(0, 140, 0);
	public final static Color LIGHT_BLUE = new Color(150, 150, 255);

	public static Color randomColor(int _min, int _max)
	{
		return new Color(((int) (Math.random() * (_max - _min)) + _min), ((int) (Math.random() * (_max - _min)) + _min), ((int) (Math.random() * (_max - _min)) + _min));
	}

	public static Color makeTransparent(Color _color, int _transparency)
	{
		return new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), _transparency);
	}

	public static Color opposite(Color _color)
	{
		return new Color(255 - _color.getRed(), 255 - _color.getGreen(), 255 - _color.getBlue());
	}

	public static Color fadeto(Color _src, Color _dest)
	{
		return new Color(_src.getRed() > _dest.getRed() ? _src.getRed() - 1 : (_src.getRed() < _dest.getRed() ? _src.getRed() + 1 : _src.getRed()), _src.getGreen() > _dest.getGreen() ? _src.getGreen() - 1 : (_src.getGreen() < _dest.getGreen() ? _src.getGreen() + 1 : _src.getGreen()), _src.getBlue() > _dest.getBlue() ? _src.getBlue() - 1 : (_src.getBlue() < _dest.getBlue() ? _src.getBlue() + 1 : _src.getBlue()));
	}
}
