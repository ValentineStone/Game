package com.valentine.game.games;

import java.util.*;

import com.valentine.game.entity.ui.*;
import com.valentine.game.entity.vfx.*;
import com.valentine.game.utils.*;

public class BeepingCirclesGame extends GameContainer
{
	List<BeepingCircle> circles = new ArrayList<>();

	public void assemble()
	{
		super.assemble();

		new OnPointInfo(this, 10, 10);

		new FpsUpsCounter(this, 10, 130);

		for (int i = 0; i < 5; i++)
		{
			circles.add(new BeepingCircle(this, MathExt.random(15, 75)));
		}

		for (BeepingCircle beeper : circles)
		{
			new Trail(this, beeper, 20);
		}
	}
}
