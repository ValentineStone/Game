package com.valentine.game.utils.math.range;

import java.util.*;

public abstract class SimpleRange implements Range
{
	public abstract Iterator<Double> iterator(double _step);
	public Iterable<Double> makeSteps(double _step)
	{
		return () -> iterator(_step);
	}
}
