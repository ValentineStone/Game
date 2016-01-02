package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class HelloWorld extends EntityLiving
{
	String text;
	Color color;
	
	public HelloWorld(Container _container)
	{
		setContainer(_container);
		setWidth(75);
		setHeight(12);
		setPositionRandom();
		setActive(true);
		setVelocityMax((Math.random()) + 1);
		text = "Hello world!";
		color = ColorExt.randomColor(7, 55);
		/*
		color = 
				Math.random() > 0.05
				?
				new Color(((int)(Math.random() * 55) + 0),((int)(Math.random() * 55) + 0),((int)(Math.random() * 55) + 0))
				:
				new Color(((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0))
				;
		*/
	}

	protected void reset()
	{
		setRotationRandom();
	}
	
	public void update()
	{
		accelerate();
		move();
		keepContained();

	}

	public void paint()
	{		
		Screen.setColor(color);
		Screen.drawString(text, getX() + Interpolation.make(getVelocityX()), getY() + Interpolation.make(getVelocityY()));
	}
}
