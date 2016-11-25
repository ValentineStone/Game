package com.valentine.game.entity.fuzzyset;

import java.util.Map.*;
import java.util.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;

public class FuzzySetGraph extends EntityBasicAI
{
	private FuzzySet set;
	private SortedMap<Double, Double> paintset = new TreeMap<>();
	
	public enum State
	{
		EMPTY,
		SINGLE_ELEMENT,
		MULTIPLE_ELEMENTS,
	};
	
	private double paddings = 10;
	private double dotr = 3;
	
	private State state;
	
	@Deprecated
	public FuzzySetGraph(Container _container, double _x, double _y, double _width, double _height)
	{
		this(_container, new FuzzySet(), _x, _y, _width, _height);
	}
	
	public FuzzySetGraph(Container _container, FuzzySet _set, double _x, double _y, double _width, double _height)
	{
		super(_container);
		
		setPosition(_x, _y);
		setSize(_width, _height);
		
		set = _set;
	}

	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		_screen.setColor(getDrawColor());
		
		switch (state)
		{
			case EMPTY:
				break;
			case SINGLE_ELEMENT:
			{
				_screen.drawOval(getX() + set.getMin(), getCornerY() - set.getSet().get(set.getMin()) - 1, 2, 2);
				
				break;
			}
			case MULTIPLE_ELEMENTS:
			{
				boolean isFirst = true;
				
				double x0 = 0;
				double y0 = 0;
				
				double x1;
				double y1;
				
				for (Entry<Double, Double> entry : paintset.entrySet())
				{
					x1 = getX() + entry.getKey();
					y1 = getCornerY() - entry.getValue();
					
					// draw lines
					if (isFirst)
					{
						isFirst = false;
					}
					else 
					{
						_screen.drawLine(x1, y1, x0, y0);
					}
					x0 = x1;
					y0 = y1;
					
					_screen.drawOval(x1 - dotr, y1 - dotr, 2*dotr, 2*dotr);
				}
				break;
			}
		}
		_screen.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	public void update()
	{
		if (set == null || set.isEmpty())
		{
			state = State.EMPTY;
		}
		else if (set.size() == 1)
		{
			paintset.clear();
			
			
			state = State.SINGLE_ELEMENT;
		}
		else
		{
			state = State.MULTIPLE_ELEMENTS;
			
			double min = set.getMin();
			double max = set.getMax();
			double distance = max - min;
			
			paintset.clear();
			
			for (Entry<Double, Double> entry : set)
			{
				paintset.put
				(
					paddings + (getWidth() - 2*paddings) * (entry.getKey() - min) / (distance),
					paddings + (getHeight() - 2*paddings) * entry.getValue()
				);
			}
		}
	}
	
	public FuzzySet getSet()
	{
		return set;
	}
	
	public void setSet(FuzzySet _set)
	{
		set = _set;
	}
	
	

	public double getPaddings()
	{
		return paddings;
	}

	public void setPaddings(double _paddings)
	{
		paddings = _paddings;
	}

	public double getDotr()
	{
		return dotr;
	}

	public void setDotr(double _dotr)
	{
		dotr = _dotr;
	}
	
	
	
	
}
