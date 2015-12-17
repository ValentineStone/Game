package com.valentine.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.valentine.game.GameWorld;
import com.valentine.game.utils.Painter;

public class Collider implements Entity
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
	
	public Collider(int _id)
	{
		boolean isHuge = Math.random() < 0.01;
		id = _id;
		x = Math.random() * GameWorld.getDimension().width;
		y = Math.random() * GameWorld.getDimension().height;
		a = Math.random() * Math.PI * 2;
		if (isHuge)
		{
			rx = Math.random() * 50 + 100;
			ry = Math.random() * 50 + 100;	
			da = 0;
		} else
		{
			rx = Math.random() * 20 + 10;
			ry = Math.random() * 20 + 10;
			da = (Math.random() > 0.5 ? 1 : -1) * (Math.random() * 0.01 + 0.05);
		}
		ds = ((Math.random() * 5) + 4);
		color = new Color(((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0));
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
		_graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
		
		_graphics.fillRect((int)(x - rx + ds * Math.cos(a) * Painter.getInterpolation() + 0.5), (int)(y - ry + ds * Math.sin(a) * Painter.getInterpolation() + 0.5), (int)(2 * rx + 0.5), (int)(2 * ry + 0.5));
		
		_graphics.setColor(color);
		
		_graphics.drawRect((int)(x - rx + ds * Math.cos(a) * Painter.getInterpolation() + 0.5), (int)(y - ry + ds * Math.sin(a) * Painter.getInterpolation() + 0.5), (int)(2 * rx + 0.5), (int)(2 * ry + 0.5));
		
		_graphics.drawString(id + "", (int)(x + rx + ds * Math.cos(a) * Painter.getInterpolation() + 3.5), (int)(y - ry + ds * Math.sin(a) * Painter.getInterpolation() - 2.5));

		_graphics.drawLine((int)(x + ds * Math.cos(a) * Painter.getInterpolation() + 0.5), (int)(y + ds * Math.sin(a) * Painter.getInterpolation() + 0.5), (int)(x + ds * Math.cos(a) + ds * Math.cos(a) * Painter.getInterpolation() + 0.5), (int)(y + ds * Math.sin(a) + ds * Math.sin(a) * Painter.getInterpolation() + 0.5));
		
		if (rx < ry) 
			_graphics.drawOval((int)(x - rx + ds * Math.cos(a) * Painter.getInterpolation() + 0.5), (int)(y - rx + ds * Math.sin(a) * Painter.getInterpolation() + 0.5), (int)(2 * rx + 0.5), (int)(2 * rx + 0.5));
		else
			_graphics.drawOval((int)(x - ry + ds * Math.cos(a) * Painter.getInterpolation() + 0.5), (int)(y - ry + ds * Math.sin(a) * Painter.getInterpolation() + 0.5), (int)(2 * ry + 0.5), (int)(2 * ry + 0.5));
	}
	
	public void rotate(double _da)
	{
		if ( da > 0)
			a = a + _da > Math.PI * 2 ? a + _da - Math.PI * 2 : a + _da;
		else
			a = a + _da < 0 ? Math.PI * 2 + (a + _da) : a + _da;
	}
	
	public boolean collide(Collider _entity)
	{

		if ( (Math.abs(x - _entity.x) < rx + _entity.rx) && (Math.abs(y - _entity.y) < ry + _entity.ry) )
		{
			double oldds = ds;
			double olda = a;
			double _oldds = _entity.ds;
			double _olda = _entity.a;
			
			a = _olda;
			_entity.a = olda;
			
			ds = _oldds;
			_entity.ds = oldds;
			
			color = new Color(((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0));
			_entity.color = new Color(((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0));
			
			update();
			_entity.update();
			
			/*
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
			*/
			return true;
		}

		return false;
	}
}
