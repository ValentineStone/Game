package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.math.fuzzy.*;
import com.valentine.game.utils.math.range.*;

public class FuzzyNumbersGame extends RootContainer
{
	public FuzzyNumbersGame(Dimension _dimension)
	{
		super(_dimension);
		
		
		FuzzyNumber fn = new FuzzyNumber();
		
		for (int i = 0; i < 10; i++)
		{
			
		}
		

		fn.add(new SegmentRange(0, 1), (x) -> x);
		fn.add(new SegmentRange(1, 10), (x) -> 1 / x);
		fn.add(new SegmentRange(-10, 0), (x) -> 0.5);
	
		new FuzzyNumberGraph(this, 10, 10, 400, 200, fn);
	}
}
