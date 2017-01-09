package com.valentine.game.utils.math.range;

import java.util.*;
import java.util.function.*;

public interface Range extends DoublePredicate
{
	boolean isInfinite();
	Iterator<Double> iterator(double _step);
	
	double getHigh();
	double getLow();

	boolean test(double _x);
	
	default Iterable<Double> makeSteps(double _step)
	{
		return () -> iterator(_step);
	}
}