package com.valentine.game.entity.ambient;

import java.awt.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

public class PlanetCentered extends Entity
{
	private Entity center;
	private double r;
	private double d;
	private double angle;
	private double dAngle = 0.04;
	private Color targetColor;
	
	public PlanetCentered(Container _container, double _r, double _d, Entity _center)
	{
		super(_container);
		
		setCenter(_center);
		setD(_d);
		setR(_r);
		setAngle(MathExt.random(MathExt.PI_2_1));
		setdAngle((MathExt.randomIf(0.05) ? -1 : 1) * MathExt.random(0.02, 0.05) * 1000 / (1000 + getD()));
		setSize(getR() * 2, getR() * 2);
		setPosition(getCenter().getCenterX() - getR(), getCenter().getCenterY() - getR());
		setFillColor(ColorExt.randomColor(20, 255));
		setTargetColor(ColorExt.randomColor(20, 255));
	}

	@Override
	public void paint(Screen _screen)
	{
		double x = getX() + getD() * MathExt.rotationMakeX(getAngle());
		double y = getY() + getD() * MathExt.rotationMakeY(getAngle());
		
		_screen.setColor(getFillColor());
		_screen.fillOval(x, y, getWidth(), getHeight());
	}

	@Override
	public void update()
	{
		setAngle(MathExt.rotationNormalize(getAngle() + getdAngle()));
		
		if (getFillColor().equals(getTargetColor()))
		{
			setTargetColor(ColorExt.randomColor(20, 255));
		}
		
		setFillColor(ColorExt.fadeto(getFillColor(), getTargetColor()));
		
		setPosition(getCenter().getCenterX() - getR(), getCenter().getCenterY() - getR());
	}

	public Entity getCenter()
	{
		return center;
	}

	public void setCenter(Entity _center)
	{
		center = _center;
	}

	public double getR()
	{
		return r;
	}

	public void setR(double _r)
	{
		r = _r;
	}

	public double getD()
	{
		return d;
	}

	public void setD(double _d)
	{
		d = _d;
	}

	public double getAngle()
	{
		return angle;
	}

	public void setAngle(double _angle)
	{
		angle = _angle;
	}

	public double getdAngle()
	{
		return dAngle;
	}

	public void setdAngle(double _dAngle)
	{
		dAngle = _dAngle;
	}

	public Color getTargetColor()
	{
		return targetColor;
	}

	public void setTargetColor(Color _targetColor)
	{
		targetColor = _targetColor;
	}

	
	
}
