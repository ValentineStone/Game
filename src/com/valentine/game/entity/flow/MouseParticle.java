package com.valentine.game.entity.flow;

import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.utils.*;

public class MouseParticle extends Entity implements MouseMotionListener
{
	private int mousex;
	private int mousey;

	private double dy;

	Circle circle;

	protected double r = 0;
	protected double a = 0;

	protected double dr = 0;
	protected double da = 0;

	
	private double fi;
	private Ref<Double> u0;

	private String u0text = "";
	private String rtext = "";
	private String drtext = "";
	private String atext = "";
	private String datext = "";
	private String fitext = "";

	public MouseParticle(Container _container, Circle _circle, Ref<Double> _u0)
	{
		super(_container);
		
		circle = _circle;
		
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

		_screen.drawString(u0text, mousex, mousey - dy * 0);
		_screen.drawString(rtext, mousex, mousey - dy * 1);
		_screen.drawString(drtext, mousex, mousey - dy * 2);
		_screen.drawString(atext, mousex, mousey - dy * 3);
		_screen.drawString(datext, mousex, mousey - dy * 4);
		_screen.drawString(fitext, mousex, mousey - dy * 5);
	}

	public void update()
	{
		r = MathExt.distanceMake(circle.getTrueCenterX(), circle.getTrueCenterY(), mousex, mousey);
		a = MathExt.rotationMake(circle.getTrueCenterX(), circle.getTrueCenterY(), mousex, mousey);

		if (r == 0)
		{
			rtext = "Radius equals zero.";
			return;
		}

		dr = u0.get() * (1 - Math.pow(circle.getR() / r, 2.)) * Math.cos(a);
		da = -u0.get() * (1 + Math.pow(circle.getR() / r, 2.)) * Math.sin(a);

		fi = u0.get() * (r + Math.pow(circle.getR(), 2.) / r) * Math.cos(a);

		rtext = "R : " + r;
		drtext = "DR: " + dr;
		atext = "A : " + a;
		datext = "DA: " + da;
		fitext = "FI: " + fi;
		u0text = "U0: " + u0;
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
