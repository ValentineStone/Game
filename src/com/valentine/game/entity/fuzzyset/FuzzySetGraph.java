package com.valentine.game.entity.fuzzyset;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.valentine.game.core.screen.Screen;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.EntityBasicAI;
import com.valentine.game.utils.Ref;

public class FuzzySetGraph extends EntityBasicAI
{
	private Ref<FuzzySet> setRef;
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
	
	public FuzzySetGraph(Container _container, double _x, double _y, double _width, double _height)
	{
		this(_container, new Ref<FuzzySet>(new FuzzySet()), _x, _y, _width, _height);
	}
	
	public FuzzySetGraph(Container _container, Ref<FuzzySet> _setRef, double _x, double _y, double _width, double _height)
	{
		super(_container);
		
		setPosition(_x, _y);
		setSize(_width, _height);
		
		setRef = _setRef;
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
				_screen.drawOval(getX() + setRef.get().getMin(), getCornerY() - setRef.get().getSet().get(setRef.get().getMin()) - 1, 2, 2);
				
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
		if (setRef.get().isEmpty())
		{
			state = State.EMPTY;
		}
		else if (setRef.get().size() == 1)
		{
			paintset.clear();
			
			
			state = State.SINGLE_ELEMENT;
		}
		else
		{
			state = State.MULTIPLE_ELEMENTS;
			
			double min = setRef.get().getMin();
			double max = setRef.get().getMax();
			double distance = max - min;
			
			paintset.clear();
			
			for (Entry<Double, Double> entry : setRef.get())
			{
				paintset.put
				(
					paddings + (getWidth() - 2*paddings) * (entry.getKey() - min) / (distance),
					paddings + (getHeight() - 2*paddings) * entry.getValue()
				);
			}
		}
	}
	
	public Ref<FuzzySet> getSetRef()
	{
		return setRef;
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
