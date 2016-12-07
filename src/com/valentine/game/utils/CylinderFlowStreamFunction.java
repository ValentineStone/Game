package com.valentine.game.utils;

public class CylinderFlowStreamFunction extends MathFunction
{
	private final static CylinderFlowStreamFunction inst = new CylinderFlowStreamFunction();
	
	public Double evaluate(Double... _params)
	{
		return _params[2] * _params[0] * (1 - Math.pow(_params[3] / _params[0], 2) * Math.cos(_params[1]));
	}

	public MathFunction instance()
	{
		return inst;
	}

}
