package com.valentine.game.utils;

public class CylinderFlowVelocityPotentialFunction extends MathFunction
{
	public Double evaluate(Double... _params)
	{
		double value = _params[2] * _params[0] * (1 + Math.pow(_params[3] / _params[0], 2)) * Math.cos(_params[1]);
		
		return value;
	}
}
