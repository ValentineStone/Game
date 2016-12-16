package com.valentine.game.utils.math;

public class Range implements Comparable<Range>
{
	public double low  = 0;
	public double high = 0;
	public boolean includeLow  = true;
	public boolean includeHigh = true;
	
	public Range(double _low, double _high)
	{
		this(_low, _high, true, false);
	}
	
	public Range(double _low, double _high, boolean _includeLow, boolean _includeHigh)
	{
		low = _low;
		high = _high;
		includeLow = _includeLow;
		includeHigh = _includeHigh;
	}


	public boolean contains(double _x)
	{
		boolean result = false;
		
		if (includeLow)
			result = _x >= low;
		else
			result = _x >  low;
			
		if (!result)
			return result;
		
		if (includeHigh)
			result = result && _x <= high;
		else
			result = result && _x <  high;
		
		return result;
	}
	

	public boolean equals(Range _r)
	{
		if
		(
			   low         == _r.low
			&& high        == _r.high
			&& includeLow  == _r.includeLow
			&& includeHigh == _r.includeLow
		)
			return true;
		else
			return false;
	}

	public int compareTo(Range _r)
	{
		if (equals(_r))
			return 0;
			
		boolean isGreater;
		
		if (includeLow && !_r.includeHigh || !includeLow && _r.includeHigh)
			isGreater = low >= _r.high;
		else
			isGreater = low > _r.high;
			
		if (isGreater)
			return 1;
		else
			return -1;
	}
}
