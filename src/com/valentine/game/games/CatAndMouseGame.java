package com.valentine.game.games;

import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.fuzzyset.*;
import com.valentine.game.entity.ui.*;

import java.awt.Color;
import java.awt.Dimension;

import com.valentine.game.entity.base.*;

public class CatAndMouseGame extends RootContainer
{
	int updateCounter = 0;

	CatchyCat cat;
	
	FuzzySet set;
	FuzzySetGraph graph;
	
	int ticknumber = 0;
	
	public CatAndMouseGame(Dimension _dimension)
	{
		super(_dimension);
		
		set = new FuzzySet();
		graph = new FuzzySetGraph(this, set, 10, 10, getWidth() - 20, getHeight() - 20);
		graph.setDotr(2);
		graph.setDrawColor(new Color(80,80,130));

		cat = new CatchyCat(this, getHeight() / 2);

		new FpsUpsCounter(this, 20, 20);

		new EntityCounter(this, FlyingMouse.class, 20, 80);
	}

	public void update()
	{
		super.update();

		// drop mice every 10th update

		if (updateCounter++ > 10)
		{
			new FlyingMouse(this, cat);
			updateCounter = 0;
			
			graph.getSet().add(ticknumber++, cat.getEfficiency() / 100);
		}

	}
}
