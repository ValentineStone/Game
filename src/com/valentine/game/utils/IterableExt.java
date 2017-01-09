package com.valentine.game.utils;

import java.util.*;
import java.util.Map.*;
import java.util.function.*;

import com.valentine.game.entity.fuzzyset.*;
import com.valentine.game.utils.math.geom.*;

public class IterableExt
{
	public static <T> ArrayList<T> asArrayList(Iterable<T> _itr)
	{
		ArrayList<T> list = new ArrayList<>();
		for (T t : _itr)
			list.add(t);
		return list;
	}
	
	public static Iterable<Dot2d> asIterable(DoubleFunction<Double> _function, double _xmin, double _xmax, double _xstep)
	{
		if (_xmax <= _xmin || _xstep == 0)
			return () -> Collections.emptyIterator();
		else
			return () -> new Iterator<Dot2d>()
			{
				double xrange = _xmax > _xmin ? _xmax - _xmin : 0;
				
				int stepCount = (int) Math.floor(xrange / _xstep);
				
				boolean extraStep = stepCount * _xstep < xrange;
				boolean extraStepUnused = extraStep;
				
				int stepNumber = 0;
				double xvalue = _xmin;
				
				public Dot2d next()
				{
					if (stepNumber <= stepCount)
					{
						xvalue = _xstep * stepNumber++ + _xmin;
						return at(xvalue);
					}
					else if (extraStepUnused)
					{
						extraStepUnused = false;
						stepNumber++;
						xvalue = _xmax;
						return at(xvalue);
					}
					else
						return null;
				}
				
				public boolean hasNext()
				{
					return stepNumber * _xstep <= xrange || extraStepUnused;
				}
				
				private Dot2d at(double _x)
				{
					Double y = _function.apply(_x);
					if (y != null)
						return new Dot2d(_x, y.doubleValue());
					else
						return null;
				}
			};
	}
	
	public static Iterable<Dot2d> asIterable(FuzzySet _fuzzySet)
	{
		return () -> new Iterator<Dot2d>()
		{
			Iterator<Entry<Double, Double>> dditerator = _fuzzySet.iterator();
			
			public boolean hasNext()
			{
				return dditerator.hasNext();
			}

			public Dot2d next()
			{
				return new Dot2d(dditerator.next());
			}
		};
	}
}