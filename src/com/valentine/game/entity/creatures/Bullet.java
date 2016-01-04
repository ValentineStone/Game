package com.valentine.game.entity.creatures;

import java.awt.Color;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class Bullet extends EntityBasicAI
{
	private double x0;
	private double y0;

	public Bullet(Container _container, double _x, double _y)
	{
		super(_container);
		
		setX(_x);
		setY(_y);
		
		x0 = _x;
		y0 = _y;
		
		setVelocityMax(50);
		
		//setRotation(MathExt.randomSigned(MathExt.PI_1_4 / 50));
		
		setRotationRandom();
		
		setDrawColor(ColorExt.opposite(getContainer().getFillColor()));
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
				if (entity instanceof Rotor2)
				{
					if (((EntityBasicAI)entity).isHitting(getX() + tmpX, getY() + tmpY))
					{
						entity.setPaintable(false);
						entity.setUpdatable(false);
						getContainer().remove(entity);
						
						setPaintable(false);
						setUpdatable(false);
						getContainer().remove(this);
						
						new Explosion(getContainer(), getX() + tmpX, getY() + tmpY);
						
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
				new Explosion(getContainer(), getX(), getY(), 20);
				
				//setPaintable(false);
				setUpdatable(false);
				getContainer().remove(this);
			}
		}
		
		setActive(true);
	}

}
