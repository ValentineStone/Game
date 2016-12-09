package com.valentine.game.games;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class ClockGame extends RootContainer
{
	List<Clock> clocks = new ArrayList<>();

	public ClockGame(Dimension _dimension)
	{
		super(_dimension);

		for (int i = 0; i < 100; i++)
		{
			double r = MathExt.random(30, 100);
			clocks.add(new Clock(this, r));
			clocks.get(i).setPosition(MathExt.random(0, getWidth() - 2 * r), MathExt.random(0, getHeight() - 2 * r));
			clocks.get(i).setDrawColor(ColorExt.randomColor(10, 255));
			new DragHandler(this, clocks.get(i));
		}

		Clock clock = new Clock(this, 300);
		clock.setFillColor(new Color(0, 0, 70, 230));
		new DragHandler(this, clock);
	}
}
