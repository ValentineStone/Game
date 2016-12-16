package com.valentine.game.utils.math.fuzzy;

import java.util.*;
import java.util.Map.*;

import com.valentine.game.utils.math.*;

public class FuzzyNumber extends Function2d
{
	private TreeMap<Range,Function2d> bits = new TreeMap<>();
	
	public FuzzyNumber()
	{}

	public double evaluate(double _x)
	{
		for (Entry<Range, Function2d> entry : bits.entrySet())
			if (entry.getKey().contains(_x))
				return entry.getValue().evaluate(_x);
		return 0;
	}
}