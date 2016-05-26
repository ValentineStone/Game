package com.valentine.game.entity.ui;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.util.Calendar;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.utils.MathExt;

public class Clock extends Entity
{
	private double radius;
	
	private double hour = 11;
	private double min = 25;
	private double sec = 40;
	private double ms = 0;
	
	private double hourRotation;
	private double minRotation;
	private double secRotation;
	
	private Stroke hourStroke = new BasicStroke(6);
	private Stroke minStroke = new BasicStroke(3);
	private Stroke secStroke = new BasicStroke(1);
	
	private int cntrDotRad = 10;
	
	private Calendar calendar = Calendar.getInstance();
	
	public Clock(Container _container, double _r)
	{
		super(_container);
		radius = _r;
		setSize(radius*2, radius*2);
		setPositionCentered();
	}

	public void paint()
	{
		Screen.setColor(getFillColor());
		Screen.fillOval(getX(), getY(), getWidth(), getHeight());
		Screen.setColor(getDrawColor());
		Screen.drawOval(getX(), getY(), getWidth(), getHeight());
		Screen.setStroke(hourStroke);
		Screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(hourRotation), getCenterY() + getInnerR() * Math.sin(hourRotation));
		Screen.setStroke(minStroke);
		Screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(minRotation), getCenterY() + getInnerR() * Math.sin(minRotation));
		Screen.setStroke(secStroke);
		Screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(secRotation), getCenterY() + getInnerR() * Math.sin(secRotation));
		
		Screen.fillOval(getX() + getR() - cntrDotRad, getY() + getR() - cntrDotRad, 2*cntrDotRad, 2*cntrDotRad);
		Screen.setColor(getFillColor());
		Screen.fillOval(getX() + getR() - cntrDotRad, getY() + getR() - cntrDotRad, 2*cntrDotRad, 2*cntrDotRad);
		Screen.setColor(getDrawColor());
		Screen.drawOval(getX() + getR() - cntrDotRad, getY() + getR() - cntrDotRad, 2*cntrDotRad, 2*cntrDotRad);
	}

	public void update()
	{
		calendar = Calendar.getInstance();

		ms = calendar.get(Calendar.MILLISECOND);
		sec = calendar.get(Calendar.SECOND) + (ms / 1000);
		min = calendar.get(Calendar.MINUTE) + (sec / 60);
		hour = calendar.get(Calendar.HOUR) + (min / 60);
		
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
