package com.valentine.game.games;

import com.valentine.game.core.Game;
import com.valentine.game.entity.ambient.*;
import com.valentine.game.utils.*;

public class Night extends Game
{
	public void assemble()
	{
		super.assemble();
		
		PhasedMoon phasedMoon = new PhasedMoon(this, 100);
		
		for (int i = 0; i < 100; i++)
		{
			new BlinkingStar(this);
		}
		/*
		new PlanetCentered(this, 10, 120, phasedMoon);
		new PlanetCentered(this, 60, 200, phasedMoon);
		new PlanetCentered(this, 30, 300, phasedMoon);
		new PlanetCentered(this, 40, 400, phasedMoon);
		*/

		double r;
		double d;
		double offset = 130;
		
		for (int i = 0; i < 20; i++)
		{
			r = MathExt.random(10, 50);
			d = MathExt.random(100);
			
			new PlanetCentered(this, r, offset + d, phasedMoon);
			
			offset += r + d;
		}
	}
}
