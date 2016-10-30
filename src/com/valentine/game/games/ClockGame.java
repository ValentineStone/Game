package com.valentine.game.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.valentine.game.core.Yame;
import com.valentine.game.entity.creatures.Collider;
import com.valentine.game.entity.ui.Clock;
import com.valentine.game.entity.ui.FpsUpsCounter;
import com.valentine.game.entity.ui.OnPointInfo;
import com.valentine.game.entity.vfx.BeepingCircle;
import com.valentine.game.entity.vfx.Trail;
import com.valentine.game.utils.MathExt;

public class ClockGame extends Yame
{
	List<Clock> clocks = new ArrayList<Clock>();
	
	public void assemble()
	{
		super.assemble();
		
		for (int i = 0; i < 100; i++)
		{
			double r = MathExt.random(30, 100);
			clocks.add(new Clock(this, r));
			clocks.get(i).setPosition(MathExt.random(0, getWidth() - 2*r), MathExt.random(0, getHeight() - 2*r));
		}
		
		Clock clock = new Clock(this, 300);
		clock.setFillColor(new Color(0,0,70, 230));
	}
}
