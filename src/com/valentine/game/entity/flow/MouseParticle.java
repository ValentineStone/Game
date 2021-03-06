package com.valentine.game.entity.flow;

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
	
	private double dy;
	
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
		super(_container, _circle, _u0.get(), 0, 0);
		
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

		_screen.drawString(u0text, mousex, mousey - dy * 1);
		_screen.drawString(rtext,  mousex, mousey - dy * 2);
		_screen.drawString(drtext, mousex, mousey - dy * 3);
		_screen.drawString(atext,  mousex, mousey - dy * 4);
		_screen.drawString(datext, mousex, mousey - dy * 5);
		_screen.drawString(fitext, mousex, mousey - dy * 6);
		
		super.paint(_screen);
	}

	public void update()
	{
		setPosition(mousex, mousey);
		super.u0 = 100 * u0.get();
		
		super.calculate();

		fi = u0.get() * r * (1 + Math.pow(circle.getR() / 2, 2.)) * Math.cos(a);

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
