package com.valentine.game.entity.creatures;

import java.awt.Color;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;

public class Explosion extends Entity
{
	private double r = 0;
	private double R = MathExt.random(50, 100);
	private double dr = MathExt.random(10, 20);
	private double rI = 0;
	
	
	public Explosion(Container _container, double _x, double _y, double _R)
	{
		this(_container, _x, _y);
		
		R = _R;
		
	}
	
	
	public Explosion(Container _container, double _x, double _y)
	{
		super(_container);
		
		setX(_x);
		setY(_y);
		
		r = 0;
		
		setDrawColor(Color.RED);
		setFillColor(ColorExt.makeTransparent(getDrawColor(), 40));
		
	}

	public void paint()
	{
		rI = r + Interpolation.make(dr);
		
		Screen.setColor(getDrawColor());
		
		Screen.drawOval(getX() - rI, getY() - rI, 2 * rI, 2 * rI);
		
		Screen.setColor(getFillColor());
		
		Screen.fillOval(getX() - rI, getY() - rI, 2 * rI, 2 * rI);
	}

	public void update()
	{
		if (r > R)
		{
			kill(this);
		}
		
		r += dr;
		
		for (Entity entity : getContainer())
			if (entity instanceof EntityBasicAI)
			{
				if (((EntityBasicAI)entity).isCenterClose(getX(), getY(), r))
				{
					if (entity.kill(this))
					{
						new Explosion(getContainer(), entity.getCenterX(), entity.getCenterY());
					}
				}
			}
	}

}
