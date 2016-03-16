package com.valentine.game.entity.creatures;

import java.awt.Color;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.*;

public class CatchyCat extends EntityBasicAI
{
	double aggroDistance;
	
	int miceCaught = 0;
	int miceGotAway = 0;
	
	double efficiency;
	
	public CatchyCat(Container _container, double _triggerDistance)
	{
		super(_container);
		
		aggroDistance = _triggerDistance;
		
		setSize(100, 70);
		setPositionCentered();
	}

	public void paint()
	{
		Screen.setColor(getDrawColor());
		Screen.drawLine(getX() + 5, getY() - 10, getCenterX(), getY());
		Screen.drawLine(getX() + 5, getY() - 10, getX(), getCenterY());
		
		Screen.drawLine(getX() + getWidth() - 5, getY() - 10, getCenterX(), getY());
		Screen.drawLine(getX() + getWidth() - 5, getY() - 10, getX() + getWidth(), getCenterY());
		
		Screen.setColor(getFillColor());
		Screen.fillOval(getX(), getY(), getWidth(), getHeight());
		Screen.setColor(getDrawColor());
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
		
		Screen.drawLine(getCenterX(), getCenterY(), getX() - 10, getCenterY() + 20);
		Screen.drawLine(getCenterX(), getCenterY(), getX() - 20, getCenterY() - 20);
		Screen.drawLine(getCenterX(), getCenterY(), getX() - 30, getCenterY() + 5);
		Screen.drawLine(getCenterX(), getCenterY(), getX() + getWidth() + 10, getCenterY() + 15);
		Screen.drawLine(getCenterX(), getCenterY(), getX() + getWidth() + 20, getCenterY() - 10);
		Screen.drawLine(getCenterX(), getCenterY(), getX() + getWidth() + 20, getCenterY() + 40);
		
		Screen.drawString("Caught: " + miceCaught, getX(), getY() + getHeight() + 30);
		Screen.drawString("Escaped: " + miceGotAway, getX(), getY() + getHeight() + 50);
		Screen.drawString("Efficiency: " + String.format("%.2f",efficiency) + '%', getX(), getY() + getHeight() + 70);
		
		Screen.setColor(Color.RED);
		Screen.drawOval(getCenterX() - aggroDistance, getCenterY() - aggroDistance, aggroDistance * 2, aggroDistance * 2);
	}

	public void update()
	{
		efficiency = (miceCaught + miceGotAway) > 0 ? 100 * miceCaught / (double)(miceCaught + miceGotAway) : 0;
	}
	
	public void aggro(FlyingMouse _mouse)
	{
		setX(_mouse.getX() - getWidth() / 2);
		setY(_mouse.getY() - getHeight() / 2);
		miceCaught++;
	}

	public double getAggroDistance()
	{
		return aggroDistance;
	}
	
	public void notifyAboutEscaping()
	{
		miceGotAway++;
	}
	
	

}
