package com.valentine.game.entity.flow;

import java.awt.Color;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.entity.vfx.*;
import com.valentine.game.utils.*;

public class Particle extends EntityBasicAI
{
	private Trail trail;

	protected Circle circle;
	protected double u0 = 1;

	protected double r = 0;
	protected double a = 0;

	protected double dr = 0;
	protected double da = 0;
	
	double drx;
	double dry;
	double dax;
	double day;
	
	private boolean showGuides = false;

	public Particle(Container _container, Circle _circle, double _u0, double _x, double _y)
	{
		super(_container);

		setPosition(_x, _y);
		u0 = _u0;
		circle = _circle;

		//trail = new Trail(getContainer(),this,10);
		//trail.setDrawColor(new Color(20,20,40));
		//trail.update();

		setDrawColor(ColorExt.randomColor(30, 255));
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getDrawColor());
		_screen.drawRect(getX() -1, getY() -1, 2, 2);
		
		if (showGuides)
		{
			_screen.setColor(Color.RED);
			_screen.drawLine(getX(), getY(), getX() + drx, getY() + dry);
			
			_screen.setColor(Color.BLUE);
			_screen.drawLine(getX(), getY(), getX() + dax, getY() + day);
		}
	}

	public void update()
	{
		calculate();
		move();
	}
	
	protected void calculate()
	{
		r = MathExt.distanceMake(circle.getCenterX(), circle.getCenterY(), getX(), getY());
		a = MathExt.rotationMake(circle.getCenterX(), circle.getCenterY(), getX(), getY());
		
		double cosa =  Math.cos(a);
		double sina = Math.sin(a);
		
		// Original evaluations, undoublechecked
		// dr = -u0 * sina * ( Math.pow(circle.getR() / r, 3) / 2 + 1);
		// da = -u0 * cosa * (-Math.pow(circle.getR() / r, 3)     + 1);
		
		dr =  u0 * (1 - Math.pow(circle.getR() / r, 2.)) * cosa;
		da = -u0 * (1 + Math.pow(circle.getR() / r, 2.)) * sina;
		
		drx = dr *  cosa;
		dry = dr *  sina; // cause the screen is actualy reverced Y
		dax = da * -sina;
		day = da *  cosa;
	}
	
	protected void move()
	{
		setX(getX() + drx + dax);
		setY(getY() + dry + day);

		if
		(
			getX() > getContainer().getWidth()
			|| Math.abs(da + dr) == 0
			|| r < circle.getR()
		)
		{
			kill(getContainer());
		}
	}
	
	public boolean kill(Entity _killer)
	{
		//trail.scheduleKill();
		return super.kill(_killer);
	}

	public boolean isShowGuides()
	{
		return showGuides;
	}

	public void setShowGuides(boolean _showGuides)
	{
		showGuides = _showGuides;
	}
}
