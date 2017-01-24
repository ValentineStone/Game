package com.valentine.game.utils.math.fuzzy;

import java.util.function.*;

import com.valentine.game.utils.math.function.*;
import com.valentine.game.utils.math.range.*;

public class FuzzyNumber extends RangedComplexFunction
{
	public static class FuzzyFunctionMuzzle extends FunctionTrimmer
	{
		public FuzzyFunctionMuzzle(DoubleFunction<Double> _function)
		{
			super(_function, 0, 1);
		}
	}
	
	public void add(SegmentRange _condition, DoubleFunction<Double> _function)
	{
		super.add(_condition, new FuzzyFunctionMuzzle(_function));
	}
}