package com.valentine.game.entity.base;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.creatures.CatchyCat;
import com.valentine.game.utils.*;

public class FlyingMouse extends EntityBasicAI
{
	CatchyCat enimy;
	
	boolean isCourpse = false;
	
	public FlyingMouse(Container _container, CatchyCat _enimy)
	{
		super(_container);
		
		enimy = _enimy;
		
		setSize(30, 27);
		setPositionRandom();
		
		setActive(true);
		
		setVelocityMax(5);
		setAcceleration(0.07);
		setFriction(1);
	}

	public void paint()
	{
		Screen.setColor(getDrawColor());
		
		Screen.drawOval(getX() - 5, getY() - 10, getWidth() / 2, getWidth() / 2);
		Screen.drawOval(getX() + getWidth() / 2 + 5, getY() - 10, getWidth() / 2, getWidth() / 2);

		Screen.setColor(getDrawColor());
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
		
		if (isCourpse)
		{
			Screen.drawLine(getCenterX() - 9, getCenterY() - 7, getCenterX() - 2, getCenterY());
			Screen.drawLine(getCenterX() - 9, getCenterY(), getCenterX() - 2, getCenterY() - 7);
			
			Screen.drawLine(getCenterX() + 2, getCenterY() - 7, getCenterX() + 9, getCenterY());
			Screen.drawLine(getCenterX() + 2, getCenterY(), getCenterX() + 9, getCenterY() - 7);
		}
		else
		{
			Screen.drawRect(getCenterX() - 9, getCenterY() - 7, 7, 7);
			Screen.drawRect(getCenterX() + 2, getCenterY() - 7, 7, 7);
		}
	}

	public void update()
	{
		if (isCourpse)
		{
			if (getDrawColor().getAlpha() > 0)
			{
				setDrawColor(ColorExt.makeTransparent(getDrawColor(), getDrawColor().getAlpha() - 3));
				return;
			}
			else
			{
				kill(this);
			}
		}
		
		if (enimy.getAggroDistance() > MathExt.distanceMake(getCenterX(), getCenterY(), enimy.getCenterX(), enimy.getCenterY()))
		{
			enimy.aggro(this);
			isCourpse = true;
		}
		else
		{
			setRotation(MathExt.rotationMake(getCenterX() - enimy.getCenterX(), getCenterY() - enimy.getCenterY()));
			
			accelerate();
			move();
			
			if (isOutOfContainer())
			{
				kill(this);
				enimy.notifyAboutEscaping();
			}
		}
	}

}
