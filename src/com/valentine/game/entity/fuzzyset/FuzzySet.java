package com.valentine.game.entity.fuzzyset;

import java.util.*;
import java.util.Map.*;

public class FuzzySet implements Iterable<Entry<Double, Double>>
{
	private final SortedMap<Double, Double> set = new TreeMap<>();
	
	public FuzzySet()
	{}
	
	public FuzzySet(FuzzySet _bro)
	{
		if (_bro != null)
			set.putAll(_bro.getMap());
	}
	
	public FuzzySet(String _list)
	{
		add(_list);
	}
	
	
	
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
	
	/**
     * Potentially remove an element from the set if it
     * is found in it.
     *
     * @param  _element - the double to be removed from the set
     */
	public void remove(double _element)
	{
		set.remove(_element);
	}
	
	/**
     * Potentially remove elements from the set if
     * <code>Double.valueOf(String)</code> finds them
     * parseable to a double and they happen to bew present
     * in a givern set.
     *
     * @param  _list - the string of doubles, separated by
     * whitespace, to be removed.
     */
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

	public void clear()
	{
		set.clear();
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

	public SortedMap<Double, Double> getMap()
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
	
	
	
	public double evalLinearFuzzyness()
	{
		double fuzzyness = 0;
		
		for (Entry<Double, Double> entry : this)
		{
			fuzzyness += Math.abs(entry.getValue() - (entry.getValue() > 0.5 ? 1 : 0));
		}
		
		return 2 * fuzzyness / size();
	}
	
	public double evalQuadraticFuzzyness()
	{
		double fuzzyness = 0;
		
		for (Entry<Double, Double> entry : this)
		{
			fuzzyness += Math.pow(entry.getValue() - (entry.getValue() > 0.5 ? 1 : 0), 2);
		}
		
		return 2 * Math.pow(fuzzyness / size(), .5);
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
	
	public void pow(double _p)
	{
		for (Entry<Double, Double> entry : this)
		{
			entry.setValue(Math.pow(entry.getValue(), _p));
		}
	}
	
	public void con()
	{
		pow(2);
	}
	
	public void dil()
	{
		pow(.5);
	}
	
	public FuzzySet clone()
	{
		return new FuzzySet(this);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	public static interface Operation
	{
		public Double evaluate(Double _a, Double _b);
	}
	
	public static class Union implements Operation
	{
		public static enum Type
		{
			GENERAL,
			ALGEBRAIC,
			BOUNDARY,
			DRASTICAL
		}
		
		private Type type;
		
		public Union(Type _type)
		{
			type = _type;
		}

		public Double evaluate(Double _a, Double _b)
		{
			double a = _a == null ? 0 : _a;
			double b = _b == null ? 0 : _b;
			
			switch (type)
			{
				case ALGEBRAIC:
					return a + b - a * b;
				case BOUNDARY:
					return Math.min(a + b, 1);
				case DRASTICAL:
					return Math.min(a, b) > 0 ? 1 : (Math.max(a, b));
				case GENERAL:
				default:
					return Math.max(a, b);
			}
		}
	}
	
	public static class Intersection implements Operation
	{
		public static enum Type
		{
			GENERAL,
			ALGEBRAIC,
			BOUNDARY,
			DRASTICAL
		}
		
		private Type type;
		
		public Intersection(Type _type)
		{
			type = _type;
		}

		public Double evaluate(Double _a, Double _b)
		{
			double a = _a == null ? 0 : _a;
			double b = _b == null ? 0 : _b;
			
			switch (type)
			{
				case ALGEBRAIC:
					return a * b;
				case BOUNDARY:
					return Math.max(a + b - 1, 0);
				case DRASTICAL:
					return Math.max(a, b) < 1 ? 0 : (Math.min(a, b));
				case GENERAL:
				default:
					return Math.min(a, b);
			}
		}
	}
	
	public static class Summ implements Operation
	{
		public static enum Type
		{
			DISJUNCTIVE
		}
		
		private Type type;
		
		public Summ(Type _type)
		{
			type = _type;
		}

		public Double evaluate(Double _a, Double _b)
		{
			double a = _a == null ? 0 : _a;
			double b = _b == null ? 0 : _b;
			
			switch (type)
			{
				case DISJUNCTIVE:
				default:
					return Math.max(Math.min(a, 1-b), Math.min(b, 1-a));
			}
		}
	}
	
	public static class Diff implements Operation
	{
		public static enum Type
		{
			GENERAL
		}
		
		private Type type;
		
		public Diff(Type _type)
		{
			type = _type;
		}

		public Double evaluate(Double _a, Double _b)
		{
			double a = _a == null ? 0 : _a;
			double b = _b == null ? 0 : _b;
			
			switch (type)
			{
				case GENERAL:
				default:
					return Math.max(a - b, 0);
			}
		}
	}
	
	public static class Compliment implements Operation
	{
		public Double evaluate(Double _a, Double _b)
		{
			return _a != null ? 1-_a : (_b != null ? 1-_b : null);
		}
	}
	
	public static FuzzySet operate(Operation _operation, FuzzySet _setA, FuzzySet _setB)
	{
		FuzzySet union = new FuzzySet();
		Set<Double> keys = new HashSet<>();
		if (_setA != null)
			keys.addAll(_setA.getMap().keySet());
		if (_setB != null)
			keys.addAll(_setB.getMap().keySet());
		
		for (Double key : keys)
		{
			Double value =
				_operation.evaluate
				(
					_setA != null ? _setA.getMap().get(key) : null,
					_setB != null ? _setB.getMap().get(key) : null
				);
			if (value != null)
				union.add(key, value);
		}
		
		return union;
	}
}
