package com.valentine.game.entity.fuzzyset;

import java.util.*;
import java.util.Map.*;

public class FuzzySet implements Iterable<Entry<Double, Double>>
{
	private final SortedMap<Double, Double> set = new TreeMap<>();
	
	public void add(double _element, double _proximity)
	{
		set.put(_element, _proximity);
	}
	
	public void add(String _list)
	{
		if (_list == null)    return;
		if (_list.equals("")) return;
		
		
		String[] listBits = _list.split(" ");
		
		double element;
		double proximity;
		
		for (int i = 0; i < listBits.length; i += 2)
		{
			try
			{
				element = Double.valueOf(listBits[i]);
				proximity = Double.valueOf(listBits[i+1]);
				
				if (proximity <= 1 && proximity >= 0)
					add(element, proximity);
			}
			catch (Exception _exc)
			{
				return;
			}
		}
	}
	
	public void remove(double _element)
	{
		set.remove(_element);
	}
	
	public void remove(String _list)
	{
		if (_list == null)    return;
		if (_list.equals("")) return;
		
		
		String[] listBits = _list.split(" ");
		
		double element;
		
		for (int i = 0; i < listBits.length; i++)
		{
			try
			{
				element = Double.valueOf(listBits[i]);
				
				remove(element);
			}
			catch (Exception _exc)
			{
				return;
			}
		}
	}

	public Iterator<Entry<Double, Double>> iterator()
	{
		return set.entrySet().iterator();
	}
	
	public double getMin()
	{
		return set.firstKey();
	}
	
	public double getMax()
	{
		return set.lastKey();
	}

	public SortedMap<Double, Double> getSet()
	{
		return set;
	}
	
	public boolean isEmpty()
	{
		return set.isEmpty();
	}
	
	public int size()
	{
		return set.size();
	}
	
	public boolean normalize()
	{
		Entry<Double, Integer> modality = generateModality();
		
		if (modality.getKey() == 1)
			return false;
		
		for (Entry<Double, Double> entry : this)
		{
			entry.setValue(entry.getValue() / modality.getKey());
		}
		
		return true;
	}
	
	public Set<Double> getUniversum()
	{
		return set.keySet();
	}
	
	public Entry<Double, Integer> generateModality()
	{
		double max = 0;
		int maxcount = 0;
		
		for (Entry<Double, Double> entry : this)
		{
			if (entry.getValue() > max)
			{
				max = entry.getValue();
				maxcount = 1;
			}
			else if (entry.getValue() == max)
			{
				maxcount++;
			}
		}
		
		final double maxRet = max;
		final int maxcountRet = maxcount;
		
		return new Entry<Double, Integer>()
		{
			public Integer setValue(Integer _value)
			{
				return null;
			}
			
			public Integer getValue()
			{
				return maxcountRet;
			}
			
			public Double getKey()
			{
				return maxRet;
			}
		};
	}
	
	public Map<Double, Double> generateCarrier()
	{
		return generateSubset(0, false, 1, true);
	}
	
	public Set<Double> generateBreakpoints()
	{
		return generateSubset(0.5, true, 0.5, true).keySet();
	}
	
	public Set<Double> generateCore()
	{
		return generateSubset(1, true, 1, true).keySet();
	}
	
	public Set<Double> generateBorders()
	{
		return generateSubset(0, false, 1, false).keySet();
	}
	
	public Map<Double, Double> generateSubset(double _low, boolean _lowIncluded, double _high, boolean _highIncluded)
	{
		SortedMap<Double, Double> carrier = new TreeMap<>();
		
		for (Entry<Double, Double> entry : this)
		{
			if
			(
				(
					_lowIncluded
					? entry.getValue() >= _low
					: entry.getValue() >  _low
				)
				&&
				(
					_highIncluded
					? entry.getValue() <= _high
					: entry.getValue() < _high
				)
			)
				carrier.put(entry.getKey(), entry.getValue());
		}
		
		return carrier;
	}
}
