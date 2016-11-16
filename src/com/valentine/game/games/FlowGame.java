package com.valentine.game.games;

import java.awt.*;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.flow.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;

public class FlowGame extends Container implements KeyListener
{
	Color fill = new Color(255, 0, 0, 50);

	boolean keepUpdating = true;
	int stepsToTake = 0;

	ContainerWindow sliders;

	Circle circle;
	
	GButton clear;
	
	BoxedSlider radiusSlider;
	BoxedSlider speedSlider;
	Ref<Double> radiusRef;
	Ref<Double> speedRef;

	double maxr = 1000;
	double minr = 10;

	public FlowGame(Dimension _dimension)
	{
		super(null, 0, 0, _dimension.getWidth(), _dimension.getHeight());

		Input.addKeyListener(this);

		maxr = getHeight() / 2. - minr;

		setDrawColor(getFillColor());

		new FpsUpsCounter(this, 10, 10);

		circle = new Circle(this, 1)
		{
			public void update()
			{
				setR(radiusSlider.getValue() * (maxr - minr) + minr);
				setPositionCentered();
			}
		};

		circle.setPositionCentered();

		sliders = new ContainerWindow(this, 10, 70, 90, 390);

		radiusSlider = new BoxedSlider(sliders, 10, 50, 30, 300);
		speedSlider = new BoxedSlider(sliders, 50, 50, 30, 300);
		radiusRef = radiusSlider.getRef();
		speedRef = speedSlider.getRef();
		

		radiusSlider.setValue(0.5);
		speedSlider.setValue(1);

		clear = new GButton(sliders, "Clear");
		clear.setX(2);
		clear.setY(2);
		
		clear.addListener(new Runnable()
		{
			
			public void run()
			{
				for (Entity entity : FlowGame.this)
				{
					if (entity instanceof Particle)
					{
						entity.kill(FlowGame.this);
					}
				}
			}
		});

		new MouseParticle(this, circle, speedRef);
	}

	public void update()
	{
		super.update();
		
		/*
		while (stepsToTake > 0)
		{
			stepsToTake--;
			
			for (int i = 0; i < getHeight(); i++)
			{
				double x;
				double y;
				double d;
				double a;
				
				x = getWidth() / 2;
				y = i;

				d = MathExt.distanceMake(getCenterX(), getCenterY(), x, y);
				a = MathExt.rotationMake(getCenterX(), getCenterY(), x, y);

				new Particle(this, circle, speedSlider.getValue(), d, a);
			}
			
		}
		*/
		
		if (isUpdatable())
		{
			for (int i = 0; i < 10; i++)
			{
				//int p = (int) MathExt.random(getHeight());
				
				double x;
				double y;
				double d;
				double a;
				
				do
				{
					x = MathExt.random(getWidth());
					y = MathExt.random(getHeight());

					d = MathExt.distanceMake(getCenterX(), getCenterY(), x, y);
					a = MathExt.rotationMake(getCenterX(), getCenterY(), x, y);
				}
				while (d <= circle.getR());

				new Particle(this, circle, 30 * speedSlider.getValue(), d, a);
			}
		}
		
	}

	public void keyPressed(KeyEvent _evt)
	{

	}

	public void keyReleased(KeyEvent _evt)
	{

	}

	public void keyTyped(KeyEvent _evt)
	{
		stepsToTake++;
		System.err.println("KEY");
	}
}
