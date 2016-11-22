package com.valentine.game.entity.creatures;

import java.awt.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;

public class CatchyCat extends EntityBasicAI
{
	// mouse detection radius
	double aggroDistance;

	// mice counters
	int miceCaught = 0;
	int miceGotAway = 0;

	// mice catching rate
	double efficiency;

	public CatchyCat(Container _container, double _aggroDistance)
	{
		super(_container);

		aggroDistance = _aggroDistance;

		setSize(100, 70);
		setPositionCentered();
	}

	public void paint(Screen _screen)
	{
		// cat clip
		_screen.setColor(getDrawColor());
		_screen.drawLine(getX() + 5, getY() - 10, getCenterX(), getY());
		_screen.drawLine(getX() + 5, getY() - 10, getX(), getCenterY());

		_screen.drawLine(getX() + getWidth() - 5, getY() - 10, getCenterX(), getY());
		_screen.drawLine(getX() + getWidth() - 5, getY() - 10, getX() + getWidth(), getCenterY());

		_screen.setColor(getFillColor());
		_screen.fillOval(getX(), getY(), getWidth(), getHeight());
		_screen.setColor(getDrawColor());
		_screen.drawOval(getX(), getY(), getWidth(), getHeight());

		_screen.drawLine(getCenterX(), getCenterY(), getX() - 10, getCenterY() + 20);
		_screen.drawLine(getCenterX(), getCenterY(), getX() - 20, getCenterY() - 20);
		_screen.drawLine(getCenterX(), getCenterY(), getX() - 30, getCenterY() + 5);
		_screen.drawLine(getCenterX(), getCenterY(), getX() + getWidth() + 10, getCenterY() + 15);
		_screen.drawLine(getCenterX(), getCenterY(), getX() + getWidth() + 20, getCenterY() - 10);
		_screen.drawLine(getCenterX(), getCenterY(), getX() + getWidth() + 20, getCenterY() + 40);

		// cat info
		double extraCoord = 0;
		if (getY() + 2 * getHeight() > getContainer().getHeight())
		{
			extraCoord = -2.5 * getHeight();
		}
		_screen.drawString("Caught: " + miceCaught, getX(), getY() + getHeight() + 30 + extraCoord);
		_screen.drawString("Escaped: " + miceGotAway, getX(), getY() + getHeight() + 50 + extraCoord);
		_screen.drawString("Efficiency: " + String.format("%.2f", efficiency) + '%', getX(), getY() + getHeight() + 70 + extraCoord);

		// cat aggro radius
		_screen.setColor(Color.RED);
		_screen.drawOval(getCenterX() - aggroDistance, getCenterY() - aggroDistance, aggroDistance * 2, aggroDistance * 2);
	}

	public void update()
	{
		// mice catch rate update
		efficiency = (miceCaught + miceGotAway) > 0 ? 100 * miceCaught / (double) (miceCaught + miceGotAway) : 0;
	}

	// enrage cat to jump and kill given target
	public void aggro(FlyingMouse _mouse)
	{
		setX(_mouse.getX() - getWidth() / 2);
		setY(_mouse.getY() - getHeight() / 2);
		_mouse.makeCourpse();
		miceCaught++;
	}

	public double getAggroDistance()
	{
		return aggroDistance;
	}

	// notify cat that mouse escaped
	public void notifyAboutEscaping()
	{
		miceGotAway++;
	}

	public double getEfficiency()
	{
		return efficiency;
	}

	
	
}
