package com.valentine.misc;

import java.util.function.*;

import com.valentine.game.utils.math.*;
import com.valentine.game.utils.math.geom.*;

public class PlanarGravitatedMovement
{
	public static final double DEFAULT_STEP_TIME_SECONDS = 1 / 1000.;
	
	private Dot2d gravityCenter;
	private Dot2d position;
	private Dot2d speed;
	private Dot2d acceleration;
	
	private double forceConstant;
	
	private Supplier<Dot2d> accelerationFunction;
	
	public PlanarGravitatedMovement(Dot2d _position, Dot2d _speed, Dot2d _gravityCenter, double _forceConstant)
	{
		gravityCenter = _gravityCenter != null ? new Dot2d(_gravityCenter) : new Dot2d(0,0);
		position      = _position      != null ? new Dot2d(_position)      : new Dot2d(0,0);
		speed         = _speed         != null ? new Dot2d(_speed)         : new Dot2d(0,0);
		
		forceConstant = _forceConstant;
		
		accelerationFunction = () -> getAcceleration();
	}

	public boolean move(double _seconds, double _stepTimeSeconds)
	{
		double lastStepSeconds = _seconds % _stepTimeSeconds;
		int stepCount = (int) Math.floor(_seconds / _stepTimeSeconds);
		
		for (int i = 0; i < stepCount; i++)
			if (!moveOneStep(_stepTimeSeconds))
				return false;
		
		return moveOneStep(lastStepSeconds);
	}
	
	private boolean moveOneStep(double _seconds)
	{
		acceleration = accelerationFunction.get();
		
		speed.setX(speed.getX() + acceleration.getX() * _seconds);
		speed.setY(speed.getY() + acceleration.getY() * _seconds);
		
		position.setX(position.getX() + speed.getX() * _seconds);
		position.setY(position.getY() + speed.getY() * _seconds);
		
		return !speed.isZero();
	}
	
	public boolean move()
	{
		return move(DEFAULT_STEP_TIME_SECONDS, DEFAULT_STEP_TIME_SECONDS);
	}
	
	public Dot2d getAcceleration()
	{
		double distance = MathExt.distanceMake(position, gravityCenter);
		double forceStrenghtDividedByMass = forceConstant * distance;
		return MathExt.makeVectorTowards(position, gravityCenter, forceStrenghtDividedByMass);
	}

	public Dot2d getPosition()
	{
		return new Dot2d(position);
	}

	public Dot2d getSpeed()
	{
		return new Dot2d(speed);
	}
	
	
	
}
