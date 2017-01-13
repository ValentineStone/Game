package com.valentine.game.utils.math.function;

import java.util.*;
import java.util.Map.*;
import java.util.function.*;

import com.valentine.game.utils.math.range.*;

public class ComplexRangedFunction extends ComplexFunction
{
	public void add(SimpleRange _condition, DoubleFunction<Double> _function)
	{
		super.add(_condition, _function);
	}

	public Iterator<Entry<SimpleRange, DoubleFunction<Double>>> iteratorRanged()
	{
		Iterator<Entry<DoublePredicate, DoubleFunction<Double>>> superiterator
		= iterator();
		
		return new Iterator<Entry<SimpleRange, DoubleFunction<Double>>>()
		{
			public boolean hasNext()
			{
				return superiterator.hasNext();
			}

			public Entry<SimpleRange, DoubleFunction<Double>> next()
			{
				Entry<DoublePredicate, DoubleFunction<Double>> entry =
					superiterator.next();
				return
					new AbstractMap
					.SimpleImmutableEntry<SimpleRange, DoubleFunction<Double>>
					(
						(SimpleRange)entry.getKey(),
						entry.getValue()
					);
			}
			
		};
	}
	
	public Iterator<Range> ranges()
	{
		return new Iterator<Range>()
		{
			Iterator<DoublePredicate> superiterator = conditions();
			
			public boolean hasNext()
			{
				return superiterator.hasNext();
			}

			public Range next()
			{
				return (Range)superiterator.next();
			}
		};
	}
}
