package com.valentine.game.entity.flow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.valentine.game.core.Input;
import com.valentine.game.core.screen.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;
import com.valentine.game.entity.geometry.Circle;
import com.valentine.game.utils.MathExt;

public class MouseParticle extends Particle implements MouseMotionListener
{
	private int mousex;
	private int mousey;
	
	private double dy;
	
	private double fi;
	
	private String u0text = "";
	private String rtext = "";
	private String drtext = "";
	private String atext = "";
	private String datext = "";
	private String fitext = "";
	
	public MouseParticle(Container _container, Circle _circle, double _u0)
	{
		super(_container, _circle, _u0, 0, 0);
		
		u0text = "U0: " + _u0;
		
		Input.addMouseMotionListener(this);
	}
	
	public void paint(Screen _screen)
	{
		if (dy == 0)
		{
			dy = _screen.getGraphics().getFontMetrics().getHeight() + 1;
		}
		
		_screen.setColor(getDrawColor());
		
		_screen.drawString(u0text , mousex, mousey - dy * 0);
		_screen.drawString(rtext , mousex, mousey - dy * 1);
		_screen.drawString(drtext , mousex, mousey - dy * 2);
		_screen.drawString(atext , mousex, mousey - dy * 3);
		_screen.drawString(datext , mousex, mousey - dy * 4);
		_screen.drawString(fitext , mousex, mousey - dy * 5);
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
		
		dr = (-u0 / 2.) * Math.pow(circle.getR() / r, 3.) * Math.sin(a) - u0 * Math.sin(a);
		da = u0 * Math.pow(circle.getR() / r, 3.) * Math.cos(a) - u0 * Math.cos(a);
		
		fi = (0.5 * u0 * Math.pow(circle.getR(), 3.) / Math.pow(r, 3.)) * Math.cos(a) +  u0 * r * Math.cos(a);
		
		rtext = "R : " + r;
		drtext = "DR: " + dr;
		atext = "A : " + a;
		datext = "DA: " + da;
		fitext = "FI: " + fi;
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
