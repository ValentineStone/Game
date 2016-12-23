package com.valentine.game.utils.math.geom;

public interface Line2dInterface extends Geometry
{
	public boolean isVertical();
	public boolean isHorizontal();
	public double xFromY(double _y);
	public double yFromX(double _x);

	public double getA();
	public double getB();
	public double getC();
	
	public double evalEquasion(Dot2d _d);
	
	
}