package com.valentine.game.games;

import java.util.ArrayList;
import java.util.List;

import com.valentine.game.core.Game;
import com.valentine.game.entity.creatures.Collider;
import com.valentine.game.entity.ui.FpsUpsCounter;
import com.valentine.game.entity.ui.OnPointInfo;
import com.valentine.game.entity.vfx.BeepingCircle;
import com.valentine.game.entity.vfx.Trail;
import com.valentine.game.utils.MathExt;

public class BeepingCirclesGame extends Game
{
	List<BeepingCircle> circles = new ArrayList<BeepingCircle>();
	
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
