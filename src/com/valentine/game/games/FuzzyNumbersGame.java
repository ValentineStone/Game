package com.valentine.game.games;

import java.awt.*;
import java.util.Map.*;
import java.util.List;
import java.util.function.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.fuzzy.*;
import com.valentine.game.utils.math.geom.*;
import com.valentine.game.utils.math.range.*;

public class FuzzyNumbersGame extends RootContainer
{
	List<Dot2d> dots;
	public FuzzyNumbersGame(Dimension _dimension)
	{
		super(_dimension);
		
		
		FuzzyNumber fn = new FuzzyNumber();
		
		DoubleFunction<Double> f1 = (x) -> x*x / getWidth();
		DoubleFunction<Double> f2 = (x) -> x;
		
		for (int i = 0; i < 10; i++)
		{
			
		}
		

		fn.add(new SegmentRange(100, 300), (x) -> x);
		fn.add(new SegmentRange(0, 100), (x) -> x*x / getWidth());
		fn.add(new SegmentRange(300, getWidth()), Math::sqrt);
		
		for (Entry<SegmentRange, DoubleFunction<Double>> e : fn)
			System.err.println(e.getKey());
	
		dots = IterableExt.asArrayList(IterableExt.asIterable(fn, 1));
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		
		_screen.drawLines(dots);
	}
}
