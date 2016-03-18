package com.valentine.game.entity.vfx;

import java.awt.Color;
import java.awt.Toolkit;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.entity.base.EntityBasicAI;
import com.valentine.game.utils.MathExt;

public class BeepingCircle extends EntityBasicAI
{
	public BeepingCircle(Container _container, double _r)
	{
		super(_container);
		
		setR(_r);
		
		setPositionRandom();
	}

	public void paint()
	{
		Screen.setColor(getDrawColor());
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		boolean isTouching = false;
		for (Entity entity : getContainer())
		{
			if (entity instanceof BeepingCircle && !entity.equals(this))
			{
				if (MathExt.distanceMake(getCenterX(), getCenterY(), entity.getCenterX(), entity.getCenterY()) < getR() + ((BeepingCircle)entity).getR())
				{
					isTouching = true;
				}
			}
		}
		
		if (isTouching)
		{
			setDrawColor(Color.RED);
			Toolkit.getDefaultToolkit().beep();
			System.out.print((char)7);
		}
		else
		{
			setDrawColor(Color.WHITE);
		}

	}

	public double getR()
	{
		return getWidth() / 2.;
	}

	public void setR(double _r)
	{
		setSize(_r * 2, _r * 2);
	}
	
	

}
