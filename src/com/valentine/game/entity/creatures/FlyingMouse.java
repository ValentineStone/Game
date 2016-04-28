package com.valentine.game.entity.creatures;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.EntityBasicAI;
import com.valentine.game.utils.*;

public class FlyingMouse extends EntityBasicAI
{
	// the cat mouse will play with
	CatchyCat enimy;
	
	// mouse state, courpses wont aggro cat
	boolean isCourpse = false;
	
	public FlyingMouse(Container _container, CatchyCat _enimy)
	{
		super(_container);
		
		enimy = _enimy;
		
		setSize(30, 27);
		
		// drop randomly
		setPositionRandom();
		
		//enable movement and set parameters
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
				// keep fading
				setDrawColor(ColorExt.makeTransparent(getDrawColor(), getDrawColor().getAlpha() - 3));
				return;
			}
			else
			{
				// if fully faded remove
				kill(this);
			}
		}
		
		if (enimy.getAggroDistance() > MathExt.distanceMake(getCenterX(), getCenterY(), enimy.getCenterX(), enimy.getCenterY()))
		{
			// if close enough, aggro and turn into courpse
			enimy.aggro(this);
		}
		else
		{
			// if far from cat, determine direction and run away
			setRotation(MathExt.rotationMake(getCenterX() - enimy.getCenterX(), getCenterY() - enimy.getCenterY()));
			
			accelerate();
			move();
			
			if (isOutOfContainer())
			{
				// if out of vision - remove self
				kill(this);
				enimy.notifyAboutEscaping();
			}
		}
	}
	
	public void makeCourpse()
	{
		isCourpse = true;
	}

}
