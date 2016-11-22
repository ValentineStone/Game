package com.valentine.game.games;

import java.awt.Dimension;

import com.valentine.game.entity.base.RootContainer;
import com.valentine.game.entity.fuzzyset.FuzzySetsWindow;

public class FuzzySetsGame extends RootContainer
{
	public FuzzySetsGame(Dimension _dimension)
	{
		super(_dimension);
		
		new FuzzySetsWindow(this, 10, 10, 300, 300);
		
		new FuzzySetsWindow(this, 320, 10, 300, 300);
		
		new FuzzySetsWindow(this, 630, 10, 300, 300);
	}

}
