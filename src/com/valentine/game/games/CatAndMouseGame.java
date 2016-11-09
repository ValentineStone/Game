package com.valentine.game.games;

import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;

public class CatAndMouseGame extends GameContainer
{
	int updateCounter = 0;

	CatchyCat cat;

	public void assemble()
	{
		super.assemble();

		cat = new CatchyCat(this, getHeight() / 2);

		new OnPointInfo(this, 10, 10);

		new FpsUpsCounter(this, 10, 130);

		new EntityCounter(this, FlyingMouse.class, 10, 200);
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
