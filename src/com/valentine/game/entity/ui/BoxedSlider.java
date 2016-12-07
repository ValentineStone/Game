package com.valentine.game.entity.ui;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.utils.*;

public class BoxedSlider extends EntityBasicAI implements Valuable<Double>
{
	public enum Orientation
	{
		X, Y
	}

	private RefNotifying<Double> value;
	private Circle slider;
	private DragHandler dragHandler;

	private double sliderR;

	private Orientation orientation;

	public BoxedSlider(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container);
		setPosition(_x, _y);
		setSize(_width, _height);

		orientation = getWidth() > getHeight() ? Orientation.X : Orientation.Y;

		sliderR = (orientation == Orientation.X ? getHeight() : getWidth()) / 2.;

		slider = new Circle(getContainer(), sliderR);
		slider.setPosition(getCenterX() - sliderR, getCenterY() - sliderR);
		value = new RefNotifying<Double>(.5);

		dragHandler = new DragHandler(_container, slider, orientation == Orientation.X ? DragHandler.Orientation.X : DragHandler.Orientation.Y);

		dragHandler.setUpdatable(false);

		slider.setDrawColor(getDrawColor());
		slider.setFillColor(getFillColor());
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 2 * sliderR, 2 * sliderR);

		_screen.setColor(getDrawColor());
		_screen.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 2 * sliderR, 2 * sliderR);
		/*
		 * Screen.drawLine ( getX() + sliderR, getY() + sliderR, getX() +
		 * (orientation == Orientation.X ? getWidth() - sliderR : sliderR),
		 * getY() + (orientation == Orientation.X ? sliderR : getHeight() -
		 * sliderR) );
		 */
	}

	public void update()
	{
		dragHandler.update();

		switch (orientation)
		{
			case X:
			{
				if (slider.getX() < getX())
				{
					slider.setX(getX());
				}
				else if (slider.getX() + slider.getWidth() > getX() + getWidth())
				{
					slider.setX(getX() + getWidth() - slider.getWidth());
				}

				break;
			}
			case Y:
			{
				if (slider.getY() < getY())
				{
					slider.setY(getY());
				}
				else if (slider.getY() + slider.getHeight() > getY() + getHeight())
				{
					slider.setY(getY() + getHeight() - slider.getHeight());
				}

				break;
			}
		}

		switch (orientation)
		{
			case X:
			{
				value.set((slider.getX() - getX()) / (getWidth() - 2 * sliderR));
				break;
			}
			case Y:
			{
				value.set((slider.getY() - getY()) / (getHeight() - 2 * sliderR));
				break;
			}
		}
	}

	public Double getValue()
	{
		return value.get();
	}
	
	public RefNotifying<Double> getRef()
	{
		return value;
	}

	public void setValue(double _value)
	{
		if (_value > 1)
		{
			setValue(1);
			return;
		}
		else if (_value < 0)
		{
			setValue(0);
			return;
		}

		value.set(_value);

		switch (orientation)
		{
			case X:
			{
				slider.setX(value.get() * (getWidth() - 2 * sliderR) + getX());
				break;
			}
			case Y:
			{
				slider.setY(value.get() * (getHeight() - 2 * sliderR) + getY());
				break;
			}
		}

		dragHandler.appoint();
	}

}
