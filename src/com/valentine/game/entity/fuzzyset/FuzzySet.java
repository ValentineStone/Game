package com.valentine.game.entity.fuzzyset;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class FuzzySet implements Iterable<Entry<Double, Double>>
{
	private SortedMap<Double, Double> set = new TreeMap<>();
	
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
				
				add(element, proximity);
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
}
