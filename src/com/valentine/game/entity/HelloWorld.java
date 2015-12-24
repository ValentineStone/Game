package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class HelloWorld extends Entity
{
	String text;
	Color color;
	
	public HelloWorld(Container _container)
	{
		super(_container,0,0,0,0,0,1,1,75,12,true,true,true);
		setPositionRandom();
		setVelocityMax((Math.random()) + 1);
		setRotationRandom();
		text = "Hello world!";
		color = Screen.randomColor(7, 55);
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

	public void update()
	{
		super.update();
		accelerate();
		move();
		keepContained();

	}

	public void paint()
	{
		super.paint();
		
		Screen.setColor(color);
		Screen.drawString(text, getX() + Interpolation.make(getVelocityX()), getY() + Interpolation.make(getVelocityY()));
	}
}
