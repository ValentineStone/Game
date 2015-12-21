package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class Collider extends Entity
{	
	protected double rotationVelocity;
	
	protected Color color;
	
	
	
	public Collider(Container _container)
	{
		this(_container, 0, 0);
		x = Math.random() * container.getWidth();
		y = Math.random() * container.getHeight();
	}
	
	
	
	public Collider(Container _container, double _x, double _y)
	{
		container = _container;
		boolean isHuge = Math.random() < 0.01;
		x = _x;
		y = _y;
		rotation = Math.random() * Math.PI * 2;
		if (isHuge)
		{
			width = Math.random() * 50 + 100;
			height = Math.random() * 50 + 100;	
			rotationVelocity = 0;
		} else
		{
			width = Math.random() * 20 + 10;
			height = Math.random() * 20 + 10;
			rotationVelocity = (Math.random() > 0.5 ? 1 : -1) * (Math.random() * 0.01 + 0.05);
		}
		velocity = ((Math.random() * 5) + 4);
		color = new Color(((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0),((int)(Math.random() * 225) + 0));
	}
	
	
	public boolean hit(double _x, double _y)
	{
		if (_x > (x - width) && _x < (x + width) && _y > (y - height) && _y < (y + height)) return true;
		return false;
	}
	
	
	public void update()
	{
		// Move it
		
		x += velocity * Math.cos(rotation);
		y += velocity * Math.sin(rotation);
		rotate(rotationVelocity);
		
		// Collide with walls
		
		if (x - width < 0)
		{
			x = width;
			rotation = Math.PI - rotation;
		}
		if (y - height < 0)
		{
			y = height;
			rotation = 2 * Math.PI - rotation;
		}
		if (x + width > container.getWidth())
		{
			x = container.getWidth() - width;
			rotation = Math.PI - rotation;
		}
		if (y + height > container.getHeight())
		{
			y = container.getHeight() - height;
			rotation = 2 * Math.PI - rotation;
		}
		
		for (Entity entity : container)
		{
			if (entity instanceof Collider)
				if (entity.getId() < getId())
					collide((Collider)entity);
		}
	}
	
	
	
	
	public void paint()
	{	
		Screen.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
		
		Screen.fillRect(x - width + Interpolation.make(velocity * Math.cos(rotation)), y - height + Interpolation.make(velocity * Math.sin(rotation)), 2 * width , 2 * height);
		
		Screen.setColor(color);
		
		Screen.drawRect(x - width + Interpolation.make(velocity * Math.cos(rotation)), y - height + Interpolation.make(velocity * Math.sin(rotation)), 2 * width , 2 * height);
		
		Screen.drawString(id + "", x + width + Interpolation.make(velocity * Math.cos(rotation)) + 3, y - height + Interpolation.make(velocity * Math.sin(rotation)) - 3);

		Screen.drawLine(
						x + Interpolation.make(velocity * Math.cos(rotation)),
						y + velocity * Interpolation.make(Math.sin(rotation)),
						x + velocity * Math.cos(rotation) + Interpolation.make(velocity * Math.cos(rotation)),
						y + velocity * Math.sin(rotation) + Interpolation.make(velocity * Math.sin(rotation))
						);
		
		if (width < height) 
			Screen.drawOval(x - width + Interpolation.make(velocity * Math.cos(rotation)), y - width + Interpolation.make(velocity * Math.sin(rotation)), 2 * width , 2 * width );
		else
			Screen.drawOval(x - height + Interpolation.make(velocity * Math.cos(rotation)), y - height + Interpolation.make(velocity * Math.sin(rotation)), 2 * height , 2 * height );
	}
	
	
	
	
	
	
	
	public void rotate(double _rotationVelocity)
	{
		if ( rotationVelocity > 0)
			rotation = rotation + _rotationVelocity > Math.PI * 2 ? rotation + _rotationVelocity - Math.PI * 2 : rotation + _rotationVelocity;
		else
			rotation = rotation + _rotationVelocity < 0 ? Math.PI * 2 + (rotation + _rotationVelocity) : rotation + _rotationVelocity;
	}
	
	
	
	
	
	
	public boolean collide(Collider _collider)
	{
		if ( (Math.abs(x - _collider.x) < width + _collider.width) && (Math.abs(y - _collider.y) < height + _collider.height) )
		{
			double tmp;
			
			tmp = rotation;
			rotation = _collider.rotation;
			_collider.rotation = tmp;
			
			tmp = velocity;
			velocity = _collider.velocity;
			_collider.velocity = tmp;
			
			color = Screen.randomColor(0,255);
			_collider.color = Screen.randomColor(0,255);
			
			x += velocity * Math.cos(rotation);
			
			/*
			if ( (width + _collider.width - Math.abs(x - _collider.x)) > (height + _collider.height - Math.abs(y - _collider.y)) )
			{
				if (x - _collider.x > 0) x = _collider.x + _collider.width + width;
				else x = _collider.x - _collider.width - width;
			}
			else
			{
				if (y - _collider.y > 0) y = _collider.y + _collider.height + height;
				else y = _collider.y - _collider.height - height;
			}
			
			*/
			return true;
		}

		return false;
	}
	
}
