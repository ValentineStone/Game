package com.valentine.game.entity.creatures;

import com.valentine.game.entity.base.Entity;

public class Fractal extends Entity {
	public Fractal()
	{
		super(null,0,0,0,0,0,1,1,0,0,true,true,false);
	}
}

class FractalSide extends Entity
{
	
	private FractalSide F1, F2, F3, F4;
	
	int depth;
	
	double x1;
	double y1;
	double x2;
	double y2;
	
	public FractalSide(int _depth, double _x1, double _y1, double _x2, double _y2)
	{
		super(null,0,0,0,0,0,1,1,0,0,true,true,false);
		depth = _depth;
		x1 = _x1;
		y1 = _y1;
		x2 = _x2;
		y2 = _y2;
	}
	
	private void assemble()
	{
		double dot1X = x1 + (x2 - x1)/4;
		double dot1Y = y1 + (y2 - y1)/4;
		double normal = rotationNormalize(rotationMake((x2 - x1), (y2 - y1)) + Math.PI/2);
		double dot2X = x1 + (x2 - x1) + (Math.abs(x2 - x1) * Math.pow(3,.5) / 2) * Math.cos(normal);
		double dit2Y = y1 + (y2 - y1) + (Math.abs(y2 - y1) * Math.pow(3,.5) / 2) * Math.sin(normal);
		
		F1 = new FractalSide(depth+1, x1, y1,x1 + x2/4, y2/4);
		F2 = new FractalSide(depth+1, x2/4, y2/4, topX, topY);
		F3 = new FractalSide(depth+1, topX, topY, x, topY);
		Fr = new FractalSide(depth+1, x2/4, y2/4, topX, topY);
	}
	
}
