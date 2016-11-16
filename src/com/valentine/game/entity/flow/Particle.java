package com.valentine.game.entity.flow;

import java.awt.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.entity.vfx.*;
import com.valentine.game.utils.*;

public class Particle extends EntityBasicAI
{
	private Trail trail;

	Circle circle;
	protected double u0 = 1;

	protected double r = 0;
	protected double a = 0;

	protected double dr = 0;
	protected double da = 0;

	public Particle(Container _container, Circle _circle, double _u0, double _r, double _a)
	{
		super(_container);
		circle = _circle;

		r = _r;
		a = _a;

		u0 = _u0;
		dr = _u0;

		setX(circle.getCenterX() + r * Math.cos(a));
		setY(circle.getCenterY() + r * Math.sin(a));

		trail = new Trail(getContainer(),this,10);
		trail.setDrawColor(new Color(20,20,40));
		trail.update();

		// setDrawColor(trail.getDrawColor());

		setDrawColor(ColorExt.randomColor(30, 255));
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());
		// Screen.drawRect(getX(), getY(), 2, 2);
		_screen.drawOval(getX() -1, getY() -1, 2, 2);
	}

	public void update()
	{
		// System.err.println("(" + getX() + ", " + getY() + ")\n" + "[" + a +
		// ", " + r + "]\n" + "da: " + da + " dr: " + dr);

		dr = u0 * (1 - Math.pow(circle.getR() / r, 2.)) * Math.cos(a);
		da = -u0 * (1 + Math.pow(circle.getR() / r, 2.)) * Math.sin(a);
		
		if (r < circle.getR())
		{
			kill(circle);
			return;
		}

		r += dr;
		a += da;

		setX(circle.getCenterX() + r * Math.cos(a));
		setY(circle.getCenterY() + r * Math.sin(a));

		// System.err.println("(" + getX() + ", " + getY() + ")\n" + "[" + a +
		// ", " + r + "]\n" + "da: " + da + " dr: " + dr + "\n\n");

		if (isOutOfContainer() || Math.abs(da) == 0)
		{
			kill(getContainer());
		}
	}

	public double getR()
	{
		return r;
	}

	public void setR(double _r)
	{
		r = _r;
	}

	public double getA()
	{
		return a;
	}

	public void setA(double _a)
	{
		a = _a;
	}

	public double getDr()
	{
		return dr;
	}

	public void setDr(double _dr)
	{
		dr = _dr;
	}

	public double getDa()
	{
		return da;
	}

	public void setDa(double _da)
	{
		da = _da;
	}

	public Particle randomSpeed()
	{
		setDr(MathExt.random(-3, 3));
		setDa(MathExt.random(-0.1, 0.1));
		return this;
	}
	
	public boolean kill(Entity _killer)
	{
		trail.scheduleKill();
		return super.kill(_killer);
	}

}
