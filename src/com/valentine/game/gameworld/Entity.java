package com.valentine.game.gameworld;

import java.awt.Color;
import java.awt.Graphics;

import com.valentine.game.GamePainter;
import com.valentine.game.GameWorld;

public class Entity implements OldEntity
{	
	protected double x;
	protected double y;
	protected double a;
	protected double rx;
	protected double ry;
	protected double ds;
	protected double da;
	
	protected int id;
	
	protected Color color;
	
	protected boolean visibleHitbox = true;
	
	public Entity(int _id)
	{
		id = _id;
		x = Math.random() * GameWorld.getDimension().width;
		y = Math.random() * GameWorld.getDimension().height;
		a = Math.random() * Math.PI * 2;
		rx = Math.random() * 20 + 10;
		ry = Math.random() * 20 + 10;
		ds = ((Math.random() * 5) + 3);
		da = 0; //Math.random() * 0.05 + 0.05;
		color = new Color(((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0),((int)(Math.random() * 255) + 0));
	}
	
	public void update()
	{
		// Move it
		
		x += ds * Math.cos(a);
		y += ds * Math.sin(a);
		rotate(da);
		
		// Collide with walls
		
		if (x - rx < 0)
		{
			x = rx;
			a = Math.PI - a;
		}
		if (y - ry < 0)
		{
			y = ry;
			a = 2 * Math.PI - a;
		}
		if (x + rx > GameWorld.getDimension().width)
		{
			x = GameWorld.getDimension().width - rx;
			a = Math.PI - a;
		}
		if (y + ry > GameWorld.getDimension().height)
		{
			y = GameWorld.getDimension().height - ry;
			a = 2 * Math.PI - a;
		}
	}

	public void paint(Graphics _graphics)
	{
		_graphics.setColor(color);
		_graphics.drawRect((int)(x - rx + ds * Math.cos(a) * GamePainter.getInterpolation() + 0.5), (int)(y - ry + ds * Math.sin(a) * GamePainter.getInterpolation() + 0.5), (int)(2 * rx + 0.5), (int)(2 * ry + 0.5));
		_graphics.drawString(id + "", (int)(x + rx + 0.5), (int)(y - ry + 0.5));
	}
	
	public void rotate(double _da)
	{
		a = a + _da >= Math.PI * 2 ? a + _da - Math.PI * 2 : a + _da;
	}
	
	public boolean collide(Entity _entity)
	{
		double oldx = x;
		double oldy = y;
		double olda = a;
		double _oldx = _entity.x;
		double _oldy = _entity.y;
		double _olda = _entity.a;

		if ( (Math.abs(x - _entity.x) < rx + _entity.rx) && (Math.abs(y - _entity.y) < ry + _entity.ry) )
		{
			System.err.println(id + " with " + _entity.id);
			
			if (rx + _entity.rx - Math.abs(x - _entity.x) < ry + _entity.ry - Math.abs(y - _entity.y))
			{
				if (x + rx > _entity.x - _entity.rx)
				{
					System.err.println(">>>");
				}
				else if (x - rx < _entity.x + _entity.rx)
				{
					System.err.println("<<<");
				}
			}
			else
			{
				if (y + ry > _entity.y - _entity.ry)
				{
					System.err.println("\\/\\/\\/");
				}
				else if (y - ry < _entity.y + _entity.ry)
				{
					System.err.println("/\\/\\/\\");
				}
			}
			return true;
		}

		return false;
	}
}
