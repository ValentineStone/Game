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

	boolean showGuides = false;
	int stepsToTake = 0;

	ContainerWindow sliders;

	Circle circle;

	GButton clear;
	GButton guides;
	
	BoxedSlider radiusSlider;
	BoxedSlider speedSlider;
	Ref<Double> radiusRef;
	Ref<Double> speedRef;

	double maxr = 1000;
	double minr = 0;

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

		sliders = new ContainerWindow(this, 10, 70, 90, 425);

		radiusSlider = new BoxedSlider(sliders, 10, 82, 30, 300);
		speedSlider = new BoxedSlider(sliders, 50, 82, 30, 300);
		radiusRef = radiusSlider.getRef();
		speedRef = speedSlider.getRef();
		

		radiusSlider.setValue(0.5);
		speedSlider.setValue(1);

		clear = new GButton(sliders, "Clear", 5, 5, 80, 38);
		
		clear.addListener(new Runnable()
		{
			public void run()
			{
				for (Entity entity : FlowGame.this)
				{
					if (entity instanceof Particle && !(entity instanceof MouseParticle))
					{
						entity.kill(FlowGame.this);
					}
				}
			}
		});
		
		guides = new GButton(sliders, "Guide", 5, 48, 80, 25);
		
		guides.addListener
		(
			new Runnable()
			{
				public void run()
				{
					showGuides = !showGuides;
					
					for (Entity entity : FlowGame.this)
					{
						if (entity instanceof Particle)
						{
							((Particle)entity)
								.setShowGuides(showGuides);;
						}
					}
				}
			}
		);

		// new MouseParticle(this, circle, speedRef);
	}

	public void update()
	{
		super.update();
		
		
		while (stepsToTake > 0)
		{
			stepsToTake--;
			
			double x = MathExt.random(getWidth()/2);
			double y = MathExt.random(getHeight());

			Particle particle = new Particle(this, circle, 30 * speedSlider.getValue(), x, y);
			particle.setShowGuides(showGuides);
			
		}
		
		for (int i = 0; i < 10; i++)
		{
			double x = MathExt.random(getWidth()/2);
			double y = MathExt.random(getHeight());

			Particle particle = new Particle(this, circle, 30 * speedSlider.getValue(), x, y);
			particle.setShowGuides(showGuides);
			
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
	}
}
