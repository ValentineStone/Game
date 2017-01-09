package com.valentine.game.utils.math.range;

import java.util.*;

public class ComplexRange implements Range
{
	private List<BreakPoint> breakPoints = new ArrayList<>();
	
	public ComplexRange makeInfinite()
	{
		if (isInfinite())
			return this;
		
		breakPoints.add(0, new BreakPoint(Double.NEGATIVE_INFINITY, false, true));
		breakPoints.add(new BreakPoint(Double.POSITIVE_INFINITY, true, false));
		
		fixAround(0);
		fixAround(breakPoints.size()-1);
		
		return this;
	}
	
	public boolean add(double _point, boolean _includeLeft, boolean _includeRight)
	{
		if (!Double.isFinite(_point))
			return false;
		
		int index = 0;
		boolean found = false;
		boolean replace = false;
		
		for (; index < breakPoints.size(); index++)
		{
			if (breakPoints.get(index).point >= _point)
			{
				if (breakPoints.get(index).point == _point)
					replace = true;
				found = true;
				break;
			}
		}
		
		if (found)
		{
			if (replace)
			{
				breakPoints.get(index).includeLeft = _includeLeft;
				breakPoints.get(index).includeRight = _includeRight;
				breakPoints.get(index).point = _point;
			}
			else
				breakPoints.add(index, new BreakPoint(_point, _includeLeft, _includeRight));
		}
		else
			breakPoints.add(new BreakPoint(_point, _includeLeft, _includeRight));
		
		fixAround(index);
		
		return true;
	}

	public boolean isInfinite()
	{
		if (breakPoints.isEmpty())
			return false;
		return Double.isInfinite(getLow()) || Double.isInfinite(getHigh());
	}

	public double getHigh()
	{
		if (breakPoints.isEmpty())
			throw new RuntimeException("High value can not exist in an empty range");
		return breakPoints.get(0).point;
	}

	public double getLow()
	{
		if (breakPoints.isEmpty())
			throw new RuntimeException("Low value can not exist in an empty range");
		return breakPoints.get(breakPoints.size()-1).point;
	}
	
	public boolean test(double _x)
	{
		return wrapsAround(_x);
	}
	
	public boolean wrapsAround(double _x)
	{
		return getLow() <= _x && _x <= getHigh();
	}
	
	
	
	
	

	
	
	private void fixAround(int _index)
	{
		if ( _index - 1 >= 0)
			breakPoints.get(_index - 1).includeRight =
				breakPoints.get(_index).includeLeft;
		
		if (_index + 1 < breakPoints.size())
			breakPoints.get(_index + 1).includeLeft  =
				breakPoints.get(_index).includeRight;
	}
	
	
	public List<Range> generateRanges()
	{
		List<Range> ranges = new ArrayList<>();
		
		if (breakPoints.size() == 0)
			return ranges;
		
		if (breakPoints.size() == 1)
		{
			ranges.add(new PointRange(breakPoints.get(0).point));
			return ranges;
		}
		
		for (int i = 0; i < breakPoints.size(); i++)
		{
			if (breakPoints.get(i).isIsolated())
			{
				ranges.add(new PointRange(breakPoints.get(i).point));
			}
			else if (breakPoints.get(i).includeLeft)
			{
				ranges.add
				(
					new SegmentRange
					(
						breakPoints.get(i-1).point,
						breakPoints.get(i).point
					)
				);
			}
		}
		
		return ranges;
	}
	
	
	
	
	public static void main(String[] _args)
	{
		ComplexRange r = new ComplexRange().makeInfinite();
		
		r.add(0, false, true);
		r.add(1.001, true, true);
		r.add(2.987654321, true, true);
		r.add(3, true, false);
		r.add(10, false, true);
		
		System.err.println(r);
		
		for (Range range : r.generateRanges())
			System.err.println(range);
		
		for (double d : r.makeSteps(0.1))
			System.err.println(d);
		
		for (Range ra : r.generateRanges())
		{
			System.err.println("{");
			for (double d : ra.makeSteps(0.1))
			{
				System.err.println("\t"+d);
			}
			System.err.println("}");
		}
	}
	
	


	public Iterator<Double> iterator(double _step)
	{
		return new Iterator<Double>()
		{
			Iterator<Range> ranges = generateRanges().iterator();
			Iterator<Double> current = null;
			
			public Double next()
			{
				return current.next();
			}
			
			public boolean hasNext()
			{
				if (current != null && current.hasNext())
					return true;
				else if (ranges.hasNext())
				{
					current = ranges.next().iterator(_step);
					return hasNext();
				}
				return false;
			}
		};
	}
	
	
	

	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder
			.append("ComplexRange($")
			.append(breakPoints.size())
			.append(")[");
		
		for (BreakPoint bp : breakPoints)
			stringBuilder
				.append(bp.toString());
		
		stringBuilder
			.append(']');
		
		return stringBuilder.toString();
	}





	private class BreakPoint
	{
		private double point;
		private boolean includeLeft;
		private boolean includeRight;
		
		public BreakPoint(double _point, boolean _includeLeft, boolean _includeRight)
		{
			point        = _point;
			includeLeft  = _includeLeft;
			includeRight = _includeRight;
		}
		
		public boolean isIsolated()
		{
			return !(includeLeft || includeRight);
		}
		
		public String toString()
		{
			return (includeLeft ? "_" : " ") + point + (includeRight ? "_" : " ");
		}
	}
}