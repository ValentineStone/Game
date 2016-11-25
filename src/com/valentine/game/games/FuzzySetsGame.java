package com.valentine.game.games;

import java.awt.Dimension;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.fuzzyset.*;
import com.valentine.game.entity.ui.*;

public class FuzzySetsGame extends RootContainer
{
	private FpsUpsCounter fpser;

	private final FuzzySet setX;
	private final FuzzySet setY;
	private final FuzzySet setZ;
	
	private final FuzzySetWindow winX;
	private final FuzzySetWindow winY;
	private final FuzzySetWindow winZ;
	
	public FuzzySetsGame(Dimension _dimension)
	{
		super(_dimension);
		
		setX = new FuzzySet();
		winX = new FuzzySetWindow(this, "X", setX, 10, 10);
		winX.addToSet("1 0.1 2 0.9 3 1 4 0.6 5 0.2 6 0.7 10 0.345 12 0.1 13 0");
		//winX.setEditingEnabled(false);
		
		setY = new FuzzySet();
		winY = new FuzzySetWindow(this, "Y", setY, FuzzySetWindow.WIDTH + 20, 10);
		winY.addToSet("-10 0 -9 0.5 0 0.5 1 0.1 2 0.9 3 0.8 4 1");
		winY.setEditingEnabled(false);
		
		setZ = new FuzzySet();
		winZ = new FuzzySetWindow(this, "X+Y", setZ, 2*FuzzySetWindow.WIDTH + 30, 10);
		winZ.setEditingEnabled(false);
		winZ.toggleShown();
		
		fpser = new FpsUpsCounter(this, 10, winX.getHeight() + 20);
		new DragHandler(this, fpser);
	}

}
