package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class Link extends Entity {

	private Entity source;
	private Entity destination;
	
	private Color drawColor = Color.DARK_GRAY;
	
	public Link(Container _container, Entity _source, Entity _destination)
	{
		super(_container,0,0,0,0,0,1,1,0,0,true,true,false);
		destination = _destination;
		source = _source;
	}
	
	public void update() {}
	
	public void paint()
	{
		Screen.setColor(drawColor);
		Screen.drawLine(
						source.getCenterX() + Interpolation.make(source.getVelocityX()),
						source.getCenterY() + Interpolation.make(source.getVelocityY()),
						destination.getCenterX() + Interpolation.make(destination.getVelocityX()),
						destination.getCenterY() + Interpolation.make(destination.getVelocityY())
						);
	}
}
