package com.valentine.game.games;

import com.valentine.game.core.Game;
import com.valentine.game.entity.creatures.CatchyCat;
import com.valentine.game.entity.creatures.FlyingMouse;

public class CatAndMouseGame extends Game
{
	int updateCounter = 0;
	
	CatchyCat cat;
	
	public void assemble()
	{
		super.assemble();
		
		cat = new CatchyCat(this, getHeight() / 2);
	}
	
	public void update()
	{
		super.update();
		
		// drop mice every 10th update
		
		if (updateCounter++ > 10)
		{
			new FlyingMouse(this, cat);
			updateCounter = 0;
		}
		
	}
}
