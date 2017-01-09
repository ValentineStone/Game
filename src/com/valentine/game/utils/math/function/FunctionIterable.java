package com.valentine.game.utils.math.function;

import java.util.*;
import java.util.function.*;

public class FunctionIterable implements Iterable<Double>
{
	private final DoubleFunction<Double> function;
	public final double xmin;
	public final double xmax;
	public final double xrange;
	public final double xstep;
	public final int stepCount;
	public final boolean extraStep;
	

	private int stepNumber = 0;
	private double xvalue = 0;
	boolean extraStepUnused;
	
	public FunctionIterable(DoubleFunction<Double> _function, double _xmin, double _xmax, double _xstep)
	{
		function = _function;
		xmin = _xmin;
		xmax = _xmax;
		xrange = xmax > xmin ? xmax - xmin : 0;
		xstep = _xstep;
		
		stepCount = (int) Math.floor(xrange / xstep);
		
		extraStep = stepCount * xstep < xrange;
		extraStepUnused = extraStep;
		
		xvalue = xmin;
	}
	
	
	
	public int getStepNumber()
	{
		return stepNumber;
	}

	public double getXvalue()
	{
		return xvalue;
	}

	public boolean isExtraStepUnused()
	{
		return extraStepUnused;
	}

	
	
	public Iterator<Double> iterator()
	{
		if (xrange == 0 || xstep == 0)
			return Collections.emptyIterator();
		else
			return new Iterator<Double>()
			{
				public Double next()
				{
					if (stepNumber <= stepCount)
					{
						return function.apply(xvalue = xstep * stepNumber++ + xmin);
					}
					else if (extraStepUnused)
					{
						extraStepUnused = false;
						stepNumber++;
						return function.apply(xvalue = xmax);
					}
					else
						return null;
				}
				
				public boolean hasNext()
				{
					return stepNumber * xstep <= xrange || extraStepUnused;
				}
			};
	}
}
