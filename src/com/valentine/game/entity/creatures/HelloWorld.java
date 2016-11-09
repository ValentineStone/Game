package com.valentine.game.entity.creatures;

import java.awt.*;

import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;

public class HelloWorld extends EntityBasicAI
{
	String text;

	public HelloWorld(Container _container)
	{
		super(_container);
		setWidth(75);
		setHeight(12);
		setPositionRandom();
		setActive(true);
		setVelocityMax((Math.random()) + 1);
		text = "Hello world!";

		setDrawColor(Math.random() > 0.05
				? new Color(((int) (Math.random() * 55) + 0), ((int) (Math.random() * 55) + 0),
						((int) (Math.random() * 55) + 0))
				: new Color(((int) (Math.random() * 255) + 0), ((int) (Math.random() * 255) + 0),
						((int) (Math.random() * 255) + 0)));
	}

	public void update()
	{
		accelerate();
		move();
		keepContained();

	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());
		_screen.drawString(text, getX() + Interpolation.make(getVelocityX()), getY() + Interpolation.make(getVelocityY()));
	}
}
