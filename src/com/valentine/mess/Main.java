package com.valentine.mess;

import java.util.function.*;

import com.valentine.game.utils.math.geom.*;
import com.valentine.misc.*;

public class Main
{
	public static void main(String[] _args) throws InterruptedException
	{
		//planarGravitatedMovementsTest(1, 10, 3);
		holeyJarTest();
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void planarGravitatedMovementsTest(double _a, double _d, double _v) throws InterruptedException
	{
		PlanarGravitatedMovement pgm = new PlanarGravitatedMovement(new Dot2d(_d,0), new Dot2d(0,_v), null, _a);
		
		double stepTimeSecons = Metrics.microseconsToSeconds(1000);
		int    stepsPerSecond = (int) Math.round(1 / stepTimeSecons);
		int    stepCount = 0;
		
		do
		{
			
			if (stepCount % stepsPerSecond == 0)
			{
				System.err.println("a: " + pgm.getAcceleration());
				System.err.println("v: " + pgm.getSpeed());
				System.err.println("p: " + pgm.getPosition());
				System.err.println("----------------------->");
				//Thread.sleep(100);
			}
			
			stepCount++;
		}
		while (pgm.move(stepTimeSecons, stepTimeSecons));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void holeyJarTest()
	{
		final double holeRadius     = Metrics.centimetersToMeters(1);
		final double basementRadius = Metrics.centimetersToMeters(15);
		final double coneHeight     = Metrics.centimetersToMeters(50);
		final double orifice        = Metrics.squareCentimetersToSquarecMeters(1);
		
		final double missingHeight = holeRadius * coneHeight / basementRadius;
		final double missingVolume = Math.PI * missingHeight * Math.pow(holeRadius, 2) / 3;
		
		final double constant = Math.PI * Math.pow(basementRadius / coneHeight, 2);
		
		// full cone precision
		final DoubleUnaryOperator heightByVolume = (_volume) -> Math.cbrt(3 * _volume / constant);
		final double initialVolume = Math.PI * Math.pow(basementRadius, 2) * coneHeight / 3;
		
		HoleyJar holeyJar = new HoleyJar(heightByVolume, orifice, 0.6);
		
		holeyJar.poorIn(initialVolume);
		System.err.println("Initial height: " + holeyJar.getHeight() + " meters");
		System.err.println("Initial volume: " + holeyJar.getOutburstVolume() + " cubic meters");
		System.err.println("Emptying... (meters lowered per second)");
		
		
		double stepTimeSecons = Metrics.microseconsToSeconds(1000);
		int    stepsPerSecond = (int) Math.round(1 / stepTimeSecons);
		double heightChange;
		int    stepCount = 0;
		double prevHeight = holeyJar.getHeight();
		
		do
		{
			holeyJar.poorOut(stepTimeSecons, stepTimeSecons);
			
			if (stepCount % stepsPerSecond == 0)
			{
				heightChange = prevHeight - holeyJar.getHeight();
				prevHeight = holeyJar.getHeight();
				System.err.println(heightChange);
			}
			stepCount++;
		}
		while (!holeyJar.isEmpty());
		
		System.err.println("Emptied in " + (stepTimeSecons * stepCount) + " seconds");
		
	}
}