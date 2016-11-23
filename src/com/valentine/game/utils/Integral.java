package com.valentine.game.utils;

public abstract class Integral
{
	
	public static Double evaluateTrap1(MathFunction _function, Range _range)
	{
		Double _summ = 0.;
		
		for (double q = _range.min, i = 0; i < _range.n; q += _range.step, i++)
		{
			_summ += _range.step * _function.evaluate(q + _range.step / 2.);
		}
		
		return _summ;
	}
	
	public static Double evaluateTrap2(MathFunction _function, Range _rangex, Range _rangey)
	{
		Double _summ = 0.;
		
		for (double x = _rangex.min, i = 0; i < _rangex.n; x += _rangex.step, i++)
		for (double y = _rangex.min, j = 0; j < _rangey.n; y += _rangey.step, j++)
		{
			_summ +=
				_rangex.step
				* _rangey.step
				* _function.evaluate(x + _rangex.step / 2., y + _rangey.step / 2.);
		}
		
		return _summ;
	}
	
	public static void main(String... _args)
	{
		double parab = 
		Integral.evaluateTrap2
		(
			new MathFunction()
			{
				public Double evaluate(Double... _params)
				{
					return 1.;
				}
			},
			new Range(0, 1, 3),
			new Range(0, 1, 3)
		);
		
		System.err.println(parab);
	}
}



class Range
{
	public double min;
	public double max;
	public double n;
	public double step;
	
	public Range(double _min, double _max, double _n)
	{
		min = _min;
		max = _max;
		n = _n;
		step = (max - min) / n;
	}
}
