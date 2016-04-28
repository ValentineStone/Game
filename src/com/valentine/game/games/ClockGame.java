package com.valentine.game.games;

import java.util.ArrayList;
import java.util.List;

import com.valentine.game.core.Game;
import com.valentine.game.entity.creatures.Collider;
import com.valentine.game.entity.ui.FpsUpsCounter;
import com.valentine.game.entity.ui.OnPointInfo;
import com.valentine.game.entity.vfx.BeepingCircle;
import com.valentine.game.entity.vfx.Clock;
import com.valentine.game.entity.vfx.Trail;
import com.valentine.game.utils.MathExt;

public class ClockGame extends Game
{
	List<BeepingCircle> circles = new ArrayList<BeepingCircle>();
	
	public void assemble()
	{
		super.assemble();
		
		new Clock(this, 100);
	}
}
