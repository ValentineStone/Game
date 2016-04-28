package com.valentine.game.entity.vfx;

import java.awt.BasicStroke;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.utils.MathExt;

public class Clock extends Entity
{
	private double radius;
	
	private double hour = 0;
	private double min = 25;
	private double sec = 40;
	
	private double hourRotation;
	private double minRotation;
	private double secRotation;
	
	Stroke hourStroke = new BasicStroke(4);
	Stroke minStroke = new BasicStroke(4);
	Stroke secStroke = new BasicStroke(4);
	
	
	public Clock(Container _container, double _r)
	{
		super(_container);
		radius = _r;
		setSize(radius*2, radius*2);
		setPositionCentered();
	}

	public void paint()
	{
		Screen.setColor(getDrawColor());
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
		Screen.setStroke(new BasicStroke(3));
		Screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(hourRotation), getCenterY() + getInnerR() * Math.sin(hourRotation));
		Screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(minRotation), getCenterY() + getInnerR() * Math.sin(minRotation));
		Screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(secRotation), getCenterY() + getInnerR() * Math.sin(secRotation));
	}

	public void update()
	{
		secRotation = MathExt.PI_2_1 * sec / 60 - MathExt.PI_1_2;
		minRotation = MathExt.PI_2_1 * min / 60 - MathExt.PI_1_2;
		hourRotation = MathExt.PI_2_1 * hour / 60 - MathExt.PI_1_2;
	}
	
	double getR()
	{
		return radius;
	}
	
	double getInnerR()
	{
		return radius * 0.8;
	}
	
}
