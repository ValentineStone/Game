package com.valentine.game.entity.ui;

import java.util.*;
import java.util.function.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.function.*;
import com.valentine.game.utils.math.geom.*;

public class GFunctionBox extends EntityBasicAI
{
	private DoubleFunction<Double> function;
	private double xmin;
	private double xmax;
	private double xstep;
	
	private double ymultiplyer;
	private double yzeroline;
	private double xzeroline;
	
	private List<Dot2d> generatedDots;
	private FunctionCompressor ycompressor = new FunctionCompressor();
	private FunctionShifter yshifter = new FunctionShifter();
	private FunctionCompressor xcompressor = new FunctionCompressor();
	private FunctionShifter xshifter = new FunctionShifter();
	
	private boolean drawFunctionOnly = false;

	public GFunctionBox(Container _container, double _x, double _y, double _width, double _height, DoubleFunction<Double> _function, double _xmin, double _xmax, double _xstep, double _ymultiplyer, double _yzeroline)
	{
		super(_container);
		
		setPosition(_x, _y);
		setSize(_width, _height);
		
		function = _function;
		
		xmin = _xmin;
		xmax = _xmax;
		xstep = _xstep;
		
		ymultiplyer = _ymultiplyer;
		yzeroline = _yzeroline;
		
		generateDots();
	}
	
	public GFunctionBox(Container _container, double _x, double _y, double _width, double _height)
	{
		this(_container, _x, _y, _width, _height, null, 0, 0, 0, 0, 0);
	}

	public void paint(Screen _screen)
	{
		if (!drawFunctionOnly)
		{
			_screen.setColor(getFillColor());
			_screen.fillRect(getX(), getY(), getWidth(), getHeight());
			
			_screen.setColor(getDrawColor());
			_screen.drawRect(getX(), getY(), getWidth(), getHeight());
		}
		_screen.localize(getX(), getY());
		
		_screen.setClip(0, 0, getWidth(), getHeight());
		
		_screen.setColor(getDrawColor());
		if (!drawFunctionOnly)
		{
			_screen.drawLine(0, yzeroline, getWidth(), yzeroline);
			_screen.drawLine(xzeroline, 0, xzeroline, getHeight());
		}
		_screen.drawLines(generatedDots);
		
		_screen.setClip(null);
		
		_screen.delocalize(getX(), getY());
		
	}

	public void update()
	{
		setUpdatable(false);
	}
	
	private void generateDots()
	{
		if (xmin >= xmax || xstep == 0 || function == null || ymultiplyer == 0)
		{
			generatedDots = new ArrayList<>(0);
			return;
		}
		
		ycompressor.setFunction(function);
		ycompressor.setYmultiplier(-ymultiplyer);
		
		yshifter.setFunction(ycompressor);
		yshifter.setYoffset(yzeroline);
		
		xcompressor.setFunction(yshifter);
		xcompressor.setXmultiplier(getWidth() / (xmax - xmin));
		
		xzeroline = -xmin * getWidth() / (xmax - xmin);

		xshifter.setFunction(xcompressor);
		xshifter.setXoffset(-xzeroline);
		
		generatedDots = IterableExt.asArrayList(IterableExt.asIterable(xshifter, 0, getWidth(), xstep));
	}

	public boolean isDrawFunctionOnly()
	{
		return drawFunctionOnly;
	}

	public void setDrawFunctionOnly(boolean _drawFunctionOnly)
	{
		drawFunctionOnly = _drawFunctionOnly;
	}
	
	public void set(DoubleFunction<Double> _function, double _xmin, double _xmax, double _xstep, double _ymultiplyer, double _yzeroline)
	{
		function = _function;
		xmin = _xmin;
		xmax = _xmax;
		xstep = _xstep;
		ymultiplyer = _ymultiplyer;
		yzeroline = _yzeroline;
		generateDots();
	}

	public DoubleFunction<Double> getFunction()
	{
		return function;
	}

	public void setFunction(DoubleFunction<Double> _function)
	{
		function = _function;
		generateDots();
	}

	public double getXmin()
	{
		return xmin;
	}

	public void setXmin(double _xmin)
	{
		xmin = _xmin;
		generateDots();
	}

	public double getXmax()
	{
		return xmax;
	}

	public void setXmax(double _xmax)
	{
		xmax = _xmax;
		generateDots();
	}

	public double getYzeroline()
	{
		return yzeroline;
	}

	public void setYzeroline(double _yzeroline)
	{
		yzeroline = _yzeroline;
		generateDots();
	}
	
	
	
	
	
	
	
	
}