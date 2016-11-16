package com.valentine.game.entity.vfx;

import java.awt.*;
import java.io.*;

import javax.sound.sampled.*;

import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

public class BeepingCircle extends EntityBasicAI
{
	private static final File soundFile = new File("C:/Users/Kostya/Desktop/alien_beep.wav");

	private boolean isTouchingFriend = false;
	private double dr = 0.09;
	private double rmax;
	private double rmin;

	public BeepingCircle(Container _container, double _r)
	{
		super(_container);

		setR(_r);

		setPositionRandom();

		setAcceleration(0.01);
		setFriction(1);
		setVelocityMax(MathExt.random(5, 15));
		setRotation(MathExt.random(0, MathExt.PI_2_1));
		setActive(true);

		calculateRLimits();
	}

	public BeepingCircle(Container _container, double _r, double _x, double _y)
	{
		this(_container, _r);
		setPosition(_x, _y);
	}

	public void paint(Screen _screen)
	{
		_screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		_screen.setColor(getDrawColor());
		_screen.drawOval(getX(), getY(), getWidth(), getHeight());

		double indicatorSize = 50;
		double maxX = getCenterX() + Math.cos(rmax) * indicatorSize;
		double maxY = getCenterY() + Math.sin(rmax) * indicatorSize;
		double minX = getCenterX() + Math.cos(rmin) * indicatorSize;
		double minY = getCenterY() + Math.sin(rmin) * indicatorSize;
		double rX = getCenterX() + Math.cos(getRotation()) * indicatorSize;
		double rY = getCenterY() + Math.sin(getRotation()) * indicatorSize;

		_screen.drawLine(getCenterX(), getCenterY(), maxX, maxY);
		_screen.drawString("max:" + String.format("%.3f", rmax), maxX, maxY);
		_screen.drawLine(getCenterX(), getCenterY(), minX, minY);
		_screen.drawString("min:" + String.format("%.3f", rmin), minX, minY);
		_screen.drawLine(getCenterX(), getCenterY(), rX, rY);
		_screen.drawString(String.format("%.3f", getRotation()), rX, rY);
		_screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
	}

	public void update()
	{
		boolean wasTouching = isTouchingFriend;

		isTouchingFriend = false;

		for (Entity entity : getContainer())
		{
			if (entity instanceof BeepingCircle && !entity.equals(this))
			{
				if (MathExt.distanceMake(getCenterX(), getCenterY(), entity.getCenterX(), entity.getCenterY()) < getR() + ((BeepingCircle) entity).getR())
				{
					isTouchingFriend = true;
					break;
				}
			}
		}

		if (isTouchingFriend)
		{
			setDrawColor(Color.RED);
		}
		else
		{
			setDrawColor(Color.WHITE);
		}

		if (!wasTouching && isTouchingFriend)
		{
			playSound(soundFile);
		}

		boolean changedDirection = false;
		if (isTouchingEdge())
		{
			changedDirection = true;
		}

		if (Math.abs(rmax - getRotation()) <= Math.abs(dr) || Math.abs(rmin - getRotation()) <= Math.abs(dr))
		{
			dr *= -1;

		}

		setRotation(getRotation() + dr);

		move();
		accelerate();
		keepContained();

		if (changedDirection)
		{
			calculateRLimits();
		}
	}

	private void calculateRLimits()
	{
		rmax = MathExt.rotationNormalize(getRotation() + MathExt.PI_1_4);
		rmin = MathExt.rotationNormalize(getRotation() - MathExt.PI_1_4);
	}

	public double getR()
	{
		return getWidth() / 2.;
	}

	public void setR(double _r)
	{
		setSize(_r * 2, _r * 2);
	}

	public void playSound(File _file)
	{
		try
		{
			final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

			clip.addLineListener(new LineListener()
			{

				public void update(LineEvent event)
				{
					if (event.getType() == LineEvent.Type.STOP)
					{
						clip.close();
					}
				}
			});

			clip.open(AudioSystem.getAudioInputStream(_file));
			clip.start();
		}
		catch (Exception _exc)
		{
			_exc.printStackTrace(System.out);
		}
	}

}
