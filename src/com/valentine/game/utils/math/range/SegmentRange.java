package com.valentine.game.utils.math.range;

import java.util.*;

public class SegmentRange implements Range, Comparable<SegmentRange>
{
	private double low  = 0;
	private double high = 0;
	
	public SegmentRange(double _low, double _high)
	{
		if (Double.isNaN(_low) || Double.isNaN(_high) || _low >=_high)
			throw new RuntimeException("Invalid range: from " + _low + " to " + _high);
		
		low = _low;
		high = _high;
	}

	public double getLow()
	{
		return low;
	}

	public void setLow(double _low)
	{
		low = _low;
	}

	public double getHigh()
	{
		return high;
	}

	public void setHigh(double _high)
	{
		high = _high;
	}

	public boolean test(double _x)
	{
		if (low <= _x && _x <= high)
			return true;
		else
			return false;
	}

	public boolean equals(SegmentRange _r)
	{
		if (low == _r.low && high == _r.high)
			return true;
		else
			return false;
	}

	public boolean isInfinite()
	{
		return Double.isInfinite(high) || Double.isInfinite(low);
	}
	
	public Iterator<Double> iterator(double _step)
	{	
		if (isInfinite())
			return new Iterator<Double>()
			{
				double item = Double.isInfinite(low) ? high : low;
				boolean exhausted = false;
				
				public boolean hasNext()
				{
					return !exhausted && Double.isFinite(item);
				}

				public Double next()
				{
					exhausted = true;
					return item;
				}
			};
		else
			return new Iterator<Double>()
			{
				double current = low - _step;
				boolean last = false;
				
				public Double next()
				{
					return
						last
						? (current =  high)
						: (current += _step);
				}
				
				public boolean hasNext()
				{
					if (current + _step <= high)
						return true;
					else if (current < high)
					{
						last = true;
						return true;
					}
					else
						return false;
				}
			};
	}

	public String toString()
	{
		return "SegmentRange[" + low + ", " + high + "]";
	}

	public int compareTo(SegmentRange _o)
	{
		return Double.compare(getLow(), _o.getLow());
	}
}
