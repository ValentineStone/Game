package com.valentine.game.entity.objects;

import java.awt.Color;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class Bullet extends EntityBasicAI implements Explodable
{
	private double x0;
	private double y0;
	
	private Entity master;

	public Bullet(Container _container, Entity _master, double _x, double _y, double _rotation)
	{
		super(_container);
		
		setX(_x);
		setY(_y);
		
		x0 = _x;
		y0 = _y;
		
		setVelocityMax(50);
		
		setRotation(_rotation);
		
		//setRotation(MathExt.randomSigned(MathExt.PI_1_2));
		
		//setRotationRandom();
		
		setDrawColor(ColorExt.opposite(getContainer().getFillColor()));
		
		master = _master;
	}

	public void paint()
	{
		Screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		
		Screen.setColor(Color.RED);
		Screen.drawLine(x0, y0, getX(), getY());
		
		Screen.setColor(getDrawColor());
		
		Screen.drawOval(getX() - 2, getY() - 2, 4, 4);
		Screen.fillOval(getX() - 2, getY() - 2, 4, 4);
		
		Screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}

	public void update()
	{
		x0 = getX();
		y0 = getY();
		
		
		double dx = getVelocityX() / getVelocity();
		double dy = getVelocityY() / getVelocity();
		
		for (double tmpX = 0, tmpY = 0; Math.abs(tmpX) <= Math.abs(getVelocityX()); tmpX += dx, tmpY += dy)
		{	
			for (Entity entity : getContainer())
			{
				if (entity instanceof Explodable && entity != master)
				{
					if (((EntityBasicAI)entity).isGettingHit(getX() + tmpX, getY() + tmpY))
					{
						entity.kill(this);
						
						kill(this);
						
						new Explosion(getContainer(), entity.getCenterX(), entity.getCenterY());
						
						return;
					}
				}
			}
		}
		
		if (isActive())
		{
			accelerate();
			move();
			
			if (isTouchingEdge())
			{
				kill(this);
				
				new Explosion(getContainer(), getX(), getY(), 20);
			}
		}
		
		setActive(true);
	}

	public Entity getMaster()
	{
		return master;
	}

	public void setMaster(Entity _master)
	{
		master = _master;
	}
	
	

}
