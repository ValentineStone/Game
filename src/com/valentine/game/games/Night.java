package com.valentine.game.games;

import com.valentine.game.entity.ambient.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.vfx.line.*;
import com.valentine.game.utils.*;

public class Night extends GameContainer
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
		 * new PlanetCentered(this, 10, 120, phasedMoon); new
		 * PlanetCentered(this, 60, 200, phasedMoon); new PlanetCentered(this,
		 * 30, 300, phasedMoon); new PlanetCentered(this, 40, 400, phasedMoon);
		 */

		double r;
		double d;
		double offset = 130;

		for (int i = 0; i < 12; i++)
		{
			r = MathExt.random(10, 30);
			d = MathExt.random(10);

			new PlanetCentered(this, r, offset + d, phasedMoon);

			offset += 2 * r + d;
		}

		Player player = new Player(this);
		player.setInvulnerable(true);

		offset = 30;

		for (int i = 0; i < 7; i++)
		{
			r = MathExt.random(5, 10);
			d = MathExt.random(10);

			new PlanetCentered(this, r, offset + d, player);

			offset += 2 * r + d;
		}

		for (int i = 0; i < 30; i++)
		{
			offset = 30;

			Entity entity = MathExt.randomIf() ? new Rotor(this) : new Rotor2(this);

			for (int j = 0; j < 6; j++)
			{
				r = MathExt.random(5, 10);
				d = MathExt.random(10);

				new PlanetCentered(this, r, offset + d, entity);

				offset += 2 * r + d;
			}
		}

		new Line(this, 40, 30);
	}
}
