package com.valentine.game.games;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.math.geom.*;
import com.valentine.misc.*;

public class PlanarGraphGameTest extends RootContainer
{
	int n = 100;
	List<Dot2d> dots = new ArrayList<>(n+1);
	
	
	double stepTimeSecons = Metrics.microseconsToSeconds(1000);
	int    stepsPerSecond = (int) Math.round(1 / stepTimeSecons);
	int    stepCount = 0;
	PlanarGravitatedMovement pgm;
	
	Color lineColor = new Color(200, 200, 255, 50);
	
	public PlanarGraphGameTest(Dimension _dimension)
	{
		super(_dimension);
		
		new FpsUpsCounter(this, 10, 10);
		
		pgm = new PlanarGravitatedMovement(new Dot2d(300,0), new Dot2d(0,100), new Dot2d(60,30), 1);
		
		double stepTimeSecons = Metrics.microseconsToSeconds(1000);
		int    stepsPerSecond = (int) Math.round(1 / stepTimeSecons);
		int    stepCount = 0;
		
		dots.add(pgm.getPosition());
		/*
		for (int i = 1; i < n && pgm.move(stepTimeSecons, stepTimeSecons);)
		{
			if (stepCount % stepsPerSecond == 0)
			{
				dots.add(pgm.getPosition());
				i++;
			}
			stepCount++;
		}
		
		dots.add(pgm.getPosition());
		*/
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		
		_screen.setColor(Color.WHITE);
		_screen.drawLine(0, getCenterY(), getWidth(), getCenterY());
		_screen.drawLine(getCenterX(), 0, getCenterX(), getHeight());
		
		_screen.localize(getWidth() / 2, getHeight() / 2);
		
		_screen.setColor(Color.YELLOW);
		_screen.drawDot(pgm.getGravityCenter(), 3);
		
		_screen.setColor(lineColor);
		_screen.drawLines(dots);
		
		_screen.setColor(Color.RED);
		_screen.drawDot(dots.get(dots.size() - 1), 6);
		
		_screen.delocalize(getWidth() / 2, getHeight() / 2);
	}
	
	public void update()
	{
		super.update();
		
		stepCount++;
		
		while (stepCount % 100 != 0)
		{
			stepCount++;
			pgm.move(stepTimeSecons, stepTimeSecons);
		}
		
		dots.add(pgm.getPosition());
		
		if (dots.size() >= n)
			dots.remove(0);
	}
}
