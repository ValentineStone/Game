package com.valentine.game;

import java.util.ArrayList;
import java.util.List;

import com.valentine.game.core.Game;
import com.valentine.game.entity.vfx.BeepingCircle;
import com.valentine.game.entity.vfx.Trail;
import com.valentine.game.utils.MathExt;

public class BeepingCirclesGame extends Game
{
	List<BeepingCircle> circles = new ArrayList<BeepingCircle>();
	
	public void assemble()
	{
		super.assemble();
		
		for (int i = 0; i < 5; i++)
		{
			circles.add(new BeepingCircle(this, MathExt.random(15, 75)));
		}
		
		for (BeepingCircle beeper : circles)
		{
			new Trail(this, beeper, 80);
		}
	}
}
