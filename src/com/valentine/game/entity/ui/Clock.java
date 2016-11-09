package com.valentine.game.entity.ui;

import java.awt.*;
import java.util.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

public class Clock extends EntityBasicAI
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
		setSize(radius * 2, radius * 2);
		setPositionCentered();
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillOval(getX(), getY(), getWidth(), getHeight());
		_screen.setColor(getDrawColor());
		_screen.drawOval(getX(), getY(), getWidth(), getHeight());
		_screen.setStroke(hourStroke);
		_screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(hourRotation),
				getCenterY() + getInnerR() * Math.sin(hourRotation));
		_screen.setStroke(minStroke);
		_screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(minRotation),
				getCenterY() + getInnerR() * Math.sin(minRotation));
		_screen.setStroke(secStroke);
		_screen.drawLine(getCenterX(), getCenterY(), getCenterX() + getInnerR() * Math.cos(secRotation),
				getCenterY() + getInnerR() * Math.sin(secRotation));

		_screen.fillOval(getX() + getR() - cntrDotRad, getY() + getR() - cntrDotRad, 2 * cntrDotRad, 2 * cntrDotRad);
		_screen.setColor(getFillColor());
		_screen.fillOval(getX() + getR() - cntrDotRad, getY() + getR() - cntrDotRad, 2 * cntrDotRad, 2 * cntrDotRad);
		_screen.setColor(getDrawColor());
		_screen.drawOval(getX() + getR() - cntrDotRad, getY() + getR() - cntrDotRad, 2 * cntrDotRad, 2 * cntrDotRad);
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
