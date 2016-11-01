package com.valentine.game.entity.ambient;

import java.awt.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class Link extends Entity {

	private Entity source;
	private Entity destination;
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	private Color drawColor = Color.WHITE;
	
	public Link(Entity _source, Entity _destination)
	{
		super(_source.getContainer());
		destination = _destination;
		source = _source;
	}
	
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
		
		if (source instanceof EntityBasicAI)
		if (!((EntityBasicAI)source).isTouchingEdge())
		{
			x1 += Interpolation.make(source.getVelocityX());
			y1 += Interpolation.make(source.getVelocityY());
		}
		
		if (destination instanceof EntityBasicAI)
		if (!((EntityBasicAI)destination).isTouchingEdge())
		{
			x2 += Interpolation.make(destination.getVelocityX());
			y2 += Interpolation.make(destination.getVelocityY());
		}
		
		Screen.drawLine(x1, y1, x2, y2);
	}
}
