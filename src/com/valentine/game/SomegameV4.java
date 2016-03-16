package com.valentine.game;

import com.valentine.game.core.Game;
import com.valentine.game.entity.base.FlyingMouse;
import com.valentine.game.entity.creatures.CatchyCat;

public class SomegameV4 extends Game
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
		
		if (updateCounter++ > 10)
		{
			new FlyingMouse(this, cat);
			updateCounter = 0;
		}
		
	}
}
