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

	double md = -1;

	boolean keepUpdating = true;
	int stepsToTake = 0;

	ContainerWindow sliders;

	Circle circle;
	BoxedSlider radiusSlider;
	BoxedSlider speedSlider;

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

		sliders = new ContainerWindow(this, 10, 70, 90, 350);

		radiusSlider = new BoxedSlider(sliders, 10, 40, 30, 300);
		speedSlider = new BoxedSlider(sliders, 50, 40, 30, 300);

		radiusSlider.setValue(1);
		speedSlider.setValue(1);

		// new DragHandler(this, circle);
		
		
		ContainerWindow gameWin = new ContainerWindow(this, 200, 70, 840, 650);
		Entity game = new DeadSpace(new Dimension(840,620));
		game.setY(30);
		gameWin.moveIn(game);
	}

	public void update()
	{
		if (stepsToTake > 0)
		{
			stepsToTake--;
		}
		else
		{
			setUpdatable(keepUpdating);
		}
		super.update();

		if (isUpdatable())
		{
			for (int i = 0; i < 10; i++)
			{
				int p = (int) MathExt.random(getWidth());

				// System.err.println(getCenterX() + " | " + getCenterY() + " |
				// " + (getWidth() - p) + " | " + (getCenterY() +
				// getCenterY()/10));

				double d = MathExt.distanceMake(getCenterX(), getCenterY(), getWidth() - p, getHeight());
				double a = MathExt.rotationMake(getCenterX() - p, getCenterY());

				if (md < 0)
				{
					md = d;
				}
				else if (md > d)
				{
					md = d;
				}

				new Particle(this, circle, speedSlider.getValue() * 0.05 + 0.01, d, a);
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
		if (_evt.getKeyChar() == ' ')
		{
			keepUpdating = keepUpdating ? false : true;
		}
		else
		{
			stepsToTake++;
			setUpdatable(true);
		}
	}
}
