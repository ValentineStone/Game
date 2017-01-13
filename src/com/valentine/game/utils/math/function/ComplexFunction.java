package com.valentine.game.utils.math.function;

import java.util.*;
import java.util.Map.*;
import java.util.function.*;

public class ComplexFunction implements DoublePredicate, DoubleFunction<Double>, Iterable<Entry<DoublePredicate, DoubleFunction<Double>>>
{
	private Map<DoublePredicate, DoubleFunction<Double>> functions = new HashMap<>();
	
	public void add(DoublePredicate _condition, DoubleFunction<Double> _function)
	{
		functions.put(_condition, _function);
	}

	public Double apply(double _value)
	{
		for (Entry<DoublePredicate, DoubleFunction<Double>> entry : functions.entrySet())
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

	public Iterator<Entry<DoublePredicate, DoubleFunction<Double>>> iterator()
	{
		return Collections.unmodifiableSet(functions.entrySet()).iterator();
	}
	
	public Iterator<DoublePredicate> conditions()
	{
		return Collections.unmodifiableSet(functions.keySet()).iterator();
	}
}