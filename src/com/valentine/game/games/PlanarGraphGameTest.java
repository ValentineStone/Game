package com.valentine.game.games;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.math.geom.*;
import com.valentine.misc.*;

public class PlanarGraphGameTest extends RootContainer
{
	int n = 100;
	List<Dot2d> dots = new ArrayList<>(n);
	
	public PlanarGraphGameTest(Dimension _dimension)
	{
		super(_dimension);
		
		PlanarGravitatedMovement pgm = new PlanarGravitatedMovement(new Dot2d(10,0), new Dot2d(0,5), null, 1);
		
		double stepTimeSecons = Metrics.microseconsToSeconds(1000);
		int    stepsPerSecond = (int) Math.round(1 / stepTimeSecons);
		int    stepCount = 0;
		
		dots.add(pgm.getPosition());
		
		for (int i = 1; i < n && pgm.move(stepTimeSecons, stepTimeSecons); i++)
		{
			dots.add(pgm.getPosition());
		}
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		
		_screen.localize(getWidth() / 2, getHeight() / 2);
		_screen.setColor(Color.WHITE);
		_screen.drawLines(dots);
		_screen.delocalize(getWidth() / 2, getHeight() / 2);
	}
}
