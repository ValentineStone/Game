package com.valentine.misc;

import java.util.*;
import java.util.function.*;

public class HoleyJar
{
	public static final double GRAVITATIONAL_ACCELERATION_CONSTATNT = 9.80665;
	public static final double DEFAULT_STEP_TIME_SECONDS = 1 / 1000.;
	
	private DoubleUnaryOperator heightByVolume;
	private double volume;
	private double orifice;
	private double lambda;
	
	
	
	public HoleyJar(DoubleUnaryOperator _heightByVolume, double _orifice, double _lambda, double _volume)
	{
		heightByVolume = Objects.requireNonNull(_heightByVolume);
		orifice = _orifice;
		lambda = _lambda;
		volume = _volume;
	}
	
	public HoleyJar(DoubleUnaryOperator _heightByVolume, double _orifice, double _lambda)
	{
		this(_heightByVolume, _orifice, _lambda, 0);
	}



	public double poorIn(double _volume)
	{
		volume += _volume;
		return getHeight();
	}
	
	
	
	public double poorOut(double _seconds, double _stepTimeSeconds)
	{
		double lastStepSeconds = _seconds % _stepTimeSeconds;
		int stepCount = (int) Math.floor(_seconds / _stepTimeSeconds);
		
		for (int i = 0; i < stepCount; i++)
			if (poorOutOneStep(_stepTimeSeconds) == 0)
				return volume;
		
		poorOutOneStep(lastStepSeconds);
		
		return volume;
	}
	
	public double poorOut()
	{
		return poorOut(DEFAULT_STEP_TIME_SECONDS, DEFAULT_STEP_TIME_SECONDS);
	}
	
	public double poorOutOneStep(double _seconds)
	{
		return subtractVolume(getOutburstVolume() * _seconds);
	}
	
	
	
	public double getOutburstSpeed(double _height)
	{
		return lambda * Math.sqrt(2 * GRAVITATIONAL_ACCELERATION_CONSTATNT * _height);
	}
	
	public double getOutburstSpeed()
	{
		return getOutburstSpeed(getHeight());
	}
	
	
	
	public double getOutburstVolume(double _height)
	{
		return orifice * getOutburstSpeed(_height);
	}
	
	public double getOutburstVolume()
	{
		return getOutburstVolume(getHeight());
	}
	
	
	
	public double getHeight(double _volume)
	{
		return heightByVolume.applyAsDouble(_volume);
	}
	
	public double getHeight()
	{
		return heightByVolume.applyAsDouble(volume);
	}
	
	
	
	public DoubleUnaryOperator getHeightByVolume()
	{
		return heightByVolume;
	}

	public void setHeightByVolume(DoubleUnaryOperator _heightByVolume)
	{
		heightByVolume = _heightByVolume;
	}

	public double getLambda()
	{
		return lambda;
	}

	public void setLambda(double _lambda)
	{
		lambda = _lambda;
	}

	public double getOrifice()
	{
		return orifice;
	}

	public void setOrifice(double _orifice)
	{
		orifice = _orifice;
	}

	public double getVolume()
	{
		return volume;
	}

	public boolean isEmpty()
	{
		return volume == 0;
	}

	public void setVolume(double _volume)
	{
		volume = _volume;
	}

	public double subtractVolume(double _volume)
	{
		if (volume > _volume)
			return volume -= _volume;
		else
			return volume = 0;
	}
}