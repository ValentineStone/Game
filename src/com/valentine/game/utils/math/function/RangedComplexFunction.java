package com.valentine.game.utils.math.function;

import java.util.*;
import java.util.Map.*;
import java.util.function.*;

import com.valentine.game.utils.math.range.*;

public class RangedComplexFunction implements DoublePredicate, DoubleFunction<Double>, Iterable<Entry<SegmentRange, DoubleFunction<Double>>>
{
	private SortedMap<SegmentRange, DoubleFunction<Double>> functions = new TreeMap<>();
	
	public void add(SegmentRange _condition, DoubleFunction<Double> _function)
	{
		functions.put(_condition, _function);
	}

	public Double apply(double _value)
	{
		for (Entry<SegmentRange, DoubleFunction<Double>> entry : functions.entrySet())
		{
			if (entry.getKey().test(_value))
				return entry.getValue().apply(_value);
		}
		return null;
	}

	public boolean test(double _value)
	{
		return apply(_value) != null;
	}

	public Iterator<Entry<SegmentRange, DoubleFunction<Double>>> iterator()
	{
		return Collections.unmodifiableSet(functions.entrySet()).iterator();
	}
	
	public Iterator<SegmentRange> conditions()
	{
		return Collections.unmodifiableSet(functions.keySet()).iterator();
	}
}