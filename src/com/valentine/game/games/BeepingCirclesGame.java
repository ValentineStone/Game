package com.valentine.game.games;

import java.awt.Dimension;
import java.util.*;

import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.ui.*;
import com.valentine.game.entity.vfx.*;
import com.valentine.game.utils.*;

public class BeepingCirclesGame extends Container
{
	List<BeepingCircle> circles = new ArrayList<>();
	
	public BeepingCirclesGame(Dimension _dimension)
	{
		super(null, 0, 0, _dimension.getWidth(), _dimension.getHeight());
		
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
