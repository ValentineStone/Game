package com.valentine.game.entity.polarcoordinated;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.entity.objects.*;
import com.valentine.game.entity.vfx.*;
import com.valentine.game.utils.*;

public class Particle extends EntityBasicAI
{
	private Trail trail;
	
	Circle circle;
	private double u0 = 1;
	
	private double r = 0;
	private double a = 0;
	
	private double dr = 0;
	private double da = 0;
	
	
	public Particle(Container _container, Circle _circle, double _u0, double _r, double _a)
	{
		super(_container);
		circle = _circle;
		
		r = _r;
		a = _a;
		
		u0 = _u0;
		dr = _u0;
		
		//trail = new Trail(getContainer(),this,10);
		
		//setDrawColor(trail.getDrawColor());
		
		setDrawColor(ColorExt.randomColor(30, 255));
	}

	public void paint()
	{
		Screen.setColor(getDrawColor());
		//Screen.drawRect(getX(), getY(), 2, 2);
		Screen.drawLine(getX(), getY(), getX(), getY());
	}

	public void update()
	{
		//System.err.println("(" + getX() + ", " + getY() + ")\n" + "[" + a + ", " + r + "]\n" + "da: " + da + " dr: " + dr);
		
		dr = (-u0 / 2.) * Math.pow(circle.getR() / r, 3.) * Math.sin(a) - u0 * Math.sin(a);
		da = u0 * Math.pow(circle.getR() / r, 3.) * Math.cos(a) - u0 * Math.cos(a);
		
		r += dr;
		r = r < circle.getR() ? circle.getR() : r;
		a += da;
		
		setX(circle.getCenterX() + r * Math.cos(a));
		setY(circle.getCenterY() + r * Math.sin(a));
		
		//System.err.println("(" + getX() + ", " + getY() + ")\n" + "[" + a + ", " + r + "]\n" + "da: " + da + " dr: " + dr + "\n\n");
		
		if (isOutOfContainer() || Math.abs(da) == 0)
		{
			//trail.scheduleKill();
			//new Explosion(getContainer(), getX(), getY(), true, 1);
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
	
	

}
