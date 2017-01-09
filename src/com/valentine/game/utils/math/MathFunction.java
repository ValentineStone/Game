package com.valentine.game.utils.math;

import java.util.function.*;

public abstract class MathFunction implements Function<Double[], Double>
{
	public abstract Double evaluate(Double... _params);

	public Double apply(Double[] _t)
	{
		return evaluate(_t);
	}
}