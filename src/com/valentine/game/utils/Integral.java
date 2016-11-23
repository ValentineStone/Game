package com.valentine.game.utils;

public abstract class Integral
{
	public static class Range
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
		for (double y = _rangey.min, j = 0; j < _rangey.n; y += _rangey.step, j++)
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
		MathFunction parabFunc =
		new MathFunction()
		{
			public Double evaluate(Double... _params)
			{
				return _params[0]*_params[0] - _params[1]*_params[1];
			}
		};
		
		double parabIntegral = 
		Integral.evaluateTrap2
		(
			parabFunc,
			new Range(0, 1, 1),
			new Range(-1, 1, 2)
		);
		
		System.err.println(parabFunc.evaluate(0.,0.));
		
		System.err.println(parabIntegral);
	}
}