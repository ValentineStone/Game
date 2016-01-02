package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class Link extends Entity {

	private EntityLiving source;
	private EntityLiving destination;
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	private Color drawColor = Color.WHITE;
	
	public Link(EntityLiving _source, EntityLiving _destination)
	{
		destination = _destination;
		source = _source;
	}
	
	protected void reset() {}
	
	public void update() {}
	
	public void paint()
	{
		if (destination.getDrawColor() != ColorExt.TRANSPARENT) drawColor = destination.getDrawColor();
		Screen.setColor(drawColor);
		
		if (source.getContainer() != destination.getContainer())
		{
			x1 = source.getTrueCenterX() - getContainer().getTrueX();
			y1 = source.getTrueCenterY() - getContainer().getTrueY();
			x2 = destination.getTrueCenterX() - getContainer().getTrueX();
			y2 = destination.getTrueCenterY() - getContainer().getTrueY();
		}
		else
		{
			x1 = source.getCenterX();
			y1 = source.getCenterY();
			x2 = destination.getCenterX();
			y2 = destination.getCenterY();
		}
		
		if (!source.isTouchingEdge())
		{
			x1 += Interpolation.make(source.getVelocityX());
			y1 += Interpolation.make(source.getVelocityY());
		}
		
		if (!destination.isTouchingEdge())
		{
			x2 += Interpolation.make(destination.getVelocityX());
			y2 += Interpolation.make(destination.getVelocityY());
		}
		
		Screen.drawLine(x1, y1, x2, y2);
	}
}
