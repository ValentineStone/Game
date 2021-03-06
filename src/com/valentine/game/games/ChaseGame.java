package com.valentine.game.games;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.entity.vfx.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class ChaseGame extends RootContainer
{
	private int count = 3;

	private List<EntityBasicAI> dudes = new ArrayList<>(count);

	private FpsUpsCounter counter;

	public ChaseGame(Dimension _dimension)
	{
		super(_dimension);

		/*
		ContainerWindow funwin = new ContainerWindow(this, 10, 10, 200, 200);

		new Collider(funwin);
		new Rotor(funwin);
		new Rotor2(funwin);

		ContainerWindow flow = new ContainerWindow(this, 10, 220, 500, 500);
		FlowGame flowGame = new FlowGame(new Dimension(500, 470));
		flowGame.setY(30);
		flow.moveIn(flowGame);
		*/

		counter = new FpsUpsCounter(this, 10, 10);

		for (int i = 0; i < count; i++)
		{
			dudes.add(new Collider(this));
			((Collider) dudes.get(i)).setRotationVelocity(MathExt.randomSigned(0.009, 0.01));
		}
		//dudes.add(new PlayerSpacecraft(this));
		

		/*

		final GButton btn = new GButton(this, "")
		{
			int state = 0;
			String states = "\\|/-"; // "|\\-/";

			public void update()
			{
				super.update();
				state++;
				setText(states.substring(state - 1, state));
				if (state > states.length() - 1) state = 0;

				setPositionCentered();
			}
		};

		btn.addListener
		(
			new Runnable()
			{
				Entity entity = new Collider(btn.getContainer());

				public void run()
				{
					entity = new Tracker(btn.getContainer(), entity);
				}
			}
		);
		*/
	}

	public void update()
	{
		if (counter.getFps() > 50)
		{
			int dude;
			for (int i = 0; i < 1; i++)
			{
				dude = (int) Math.round(MathExt.random(0, count - 1));

				dudes.set(dude, new Tracker(this, dudes.get(dude)));

				new Trail(this, dudes.get(dude), 300, 2);
			}
		}

		super.update();
	}
}
