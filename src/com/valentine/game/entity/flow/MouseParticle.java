package com.valentine.game.entity.flow;

import java.awt.Color;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.utils.*;

public class MouseParticle extends Particle implements MouseMotionListener
{
	private int mousex;
	private int mousey;
	
	private double dy = 0;
	
	private RefNotifying<Double> u0;

	private String u0text = "";
	private String rtext = "";
	private String drtext = "";
	private String atext = "";
	private String datext = "";

	public MouseParticle(Container _container, Circle _circle, RefNotifying<Double> _u0)
	{
		super(_container, _circle, 0, 0, 0);
		
		u0 = _u0;

		Input.addMouseMotionListener(this);
	}

	public void paint(Screen _screen)
	{
		if (dy == 0)
		{
			dy = _screen.getGraphics().getFontMetrics().getHeight() + 1;
		}

		_screen.setColor(getDrawColor());

		_screen.drawString(u0text, mousex, mousey - dy * 2);
		_screen.drawString(rtext, mousex, mousey - dy * 3);
		_screen.drawString(drtext, mousex, mousey - dy * 4);
		_screen.drawString(atext, mousex, mousey - dy * 5);
		_screen.drawString(datext, mousex, mousey - dy * 6);
		
		super.paint(_screen);
	}

	public void update()
	{
		setPosition(mousex, mousey);
		
		super.u0 = 30 * u0.get();
		
		r = MathExt.distanceMake(circle.getTrueCenterX(), circle.getTrueCenterY(), mousex, mousey);
		a = MathExt.rotationMake(circle.getTrueCenterX(), circle.getTrueCenterY(), mousex, mousey);
		
		calculate();
		
		if (r == 0)
		{
			rtext = "Radius equals zero.";
			return;
		}

		rtext = "R : " + r;
		drtext = "DR: " + dr;
		atext = "A : " + a;
		datext = "DA: " + da;
		u0text = "U0: " + 30 * u0.get();
	}

	public boolean kill(Entity _killer)
	{
		Input.removeMouseMotionListener(this);
		return super.kill(_killer);
	}

	public void mouseDragged(MouseEvent _evt)
	{
		mouseMoved(_evt);
	}

	public void mouseMoved(MouseEvent _evt)
	{
		mousex = _evt.getX();
		mousey = _evt.getY();
	}

}
