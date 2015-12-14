package com.valentine.game.gameworld;

import com.valentine.game.GameWorld;

public abstract class Entity implements OldEntity
{
	protected double x;
	protected double y;
	protected double rx;
	protected double ry;
	protected double dx;
	protected double dy;
	
	public void update()
	{
		// Move it
		
		x += dx;
		y += dy;
		
		// Collide with walls
		
		if (x - rx < 0)
		{
			
		}
		if (y - ry < 0)
		{
			
		}
		if (x + rx > GameWorld.getDimension().width)
		{
			
		}
		if (y + ry > GameWorld.getDimension().height)
		{
			
		}
		
		
	}
}
