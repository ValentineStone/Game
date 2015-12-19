package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.*;

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
	
	protected Box box;
	
	protected boolean visibleHitbox = true;
	
	public Collider(int _id, Box _box)
	{
		box = _box;
		boolean isHuge = Math.random() < 0.01;
		id = _id;
		x = Math.random() * box.getWidth();
		y = Math.random() * box.getHeight();
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
		if (x + rx > box.getWidth())
		{
			x = box.getWidth() - rx;
			a = Math.PI - a;
		}
		if (y + ry > box.getHeight())
		{
			y = box.getHeight() - ry;
			a = 2 * Math.PI - a;
		}
		
		for (int i = 0; i < box.size(); i++)
		{
			if (box.get(i) instanceof Collider)
				if ( ((Collider)(box.get(i))).getId() > getId() )
					collide((Collider)(box.get(i)));
		}
	}
	
	public void paint()
	{	
		Canvas.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
		
		Canvas.fillRect(x - rx + Interpolation.make(ds * Math.cos(a)), y - ry + Interpolation.make(ds * Math.sin(a)), 2 * rx , 2 * ry);
		
		Canvas.setColor(color);
		
		Canvas.drawRect(x - rx + Interpolation.make(ds * Math.cos(a)), y - ry + Interpolation.make(ds * Math.sin(a)), 2 * rx , 2 * ry);
		
		Canvas.drawString(id + "", x + rx + Interpolation.make(ds * Math.cos(a)) + 3, y - ry + Interpolation.make(ds * Math.sin(a)) - 3);

		Canvas.drawLine(
						x + Interpolation.make(ds * Math.cos(a)),
						y + ds * Interpolation.make(Math.sin(a)),
						x + ds * Math.cos(a) + Interpolation.make(ds * Math.cos(a)),
						y + ds * Math.sin(a) + Interpolation.make(ds * Math.sin(a))
						);
		
		if (rx < ry) 
			Canvas.drawOval(x - rx + Interpolation.make(ds * Math.cos(a)), y - rx + Interpolation.make(ds * Math.sin(a)), 2 * rx , 2 * rx );
		else
			Canvas.drawOval(x - ry + Interpolation.make(ds * Math.cos(a)), y - ry + Interpolation.make(ds * Math.sin(a)), 2 * ry , 2 * ry );
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
			
			x += ds * Math.cos(a);
			y += ds * Math.sin(a);
			return true;
		}

		return false;
	}
	
	public int getId()
	{
		return id;
	}
}
