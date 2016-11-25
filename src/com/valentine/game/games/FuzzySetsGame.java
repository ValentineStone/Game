package com.valentine.game.games;

import java.awt.Dimension;

import com.valentine.game.entity.base.RootContainer;
import com.valentine.game.entity.fuzzyset.FuzzySetsWindow;
import com.valentine.game.entity.ui.FpsUpsCounter;

public class FuzzySetsGame extends RootContainer
{
	private FpsUpsCounter fpser;
	
	private FuzzySetsWindow setX;
	private FuzzySetsWindow setY;
	private FuzzySetsWindow setZ;
	
	public FuzzySetsGame(Dimension _dimension)
	{
		super(_dimension);
		
		fpser = new FpsUpsCounter(this, 10, 10);
		
		setX = new FuzzySetsWindow(this, "X", 10, 10);
		setX.getSetRef().get().add("1 0.1 2 0.9 3 1 4 0.6 5 0.2 6 0.7 10 0.345 12 0.1 13 0");
		setX.setEditingEnabled(false);
		
		setY = new FuzzySetsWindow(this, "Y", 320, 10);
		setY.getSetRef().get().add("-10 0 -9 0.5 0 0.5 1 0.1 2 0.9 3 0.8 4 1");
		setY.setEditingEnabled(false);
		
		setZ = new FuzzySetsWindow(this, "Z", 630, 10);
		setZ.setEditingEnabled(false);
	}

}
