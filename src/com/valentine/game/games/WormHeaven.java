package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.entity.vfx.*;

public class WormHeaven extends RootContainer
{
	public WormHeaven(Dimension _dimension)
	{
		super(_dimension);

		ScrollContainer scrollContainer = new ScrollContainer(this, 100, 100, getWidth() - 200, getHeight() - 200);
		scrollContainer.setDrawColor(Color.YELLOW);
		scrollContainer.setFillColor(new Color(0, 0, 10));

		Player player = new Player(scrollContainer);
		player.setInvulnerable(true);
		new Trail(scrollContainer, player, 100);

		new DragHandler(this, player);

		for (int i = 0; i < 3; i++)
		{
			new DragHandler(scrollContainer, new Collider(scrollContainer));
			new DragHandler(scrollContainer, new Rotor(scrollContainer));
			new DragHandler(scrollContainer, new Rotor2(scrollContainer));
		}

		EntityBasicAI clock = new Clock(scrollContainer, 100);

		new DragHandler(scrollContainer, clock);
		new ColorGrader(scrollContainer, clock, false, true);

		new DragHandler(scrollContainer, new FpsUpsCounter(scrollContainer, 0, 0), DragHandler.Orientation.Y);

		BoxedSlider fill = new BoxedSlider(scrollContainer, 120, 120, 500, 40);

		new ValueDisplayer(scrollContainer, fill, 100, 100).setDrawColor(Color.BLUE);
		;

	}
}
