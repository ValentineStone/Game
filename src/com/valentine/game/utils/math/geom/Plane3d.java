package com.valentine.game.utils.math.geom;

public abstract class Plane3d implements Geometry
{
	public abstract double getA();
	public abstract double getB();
	public abstract double getC();
	public abstract double getD();

	public double evalX(double _y, double _z)
	{
		return - (getB() * _y + getC() * _z + getD()) / getA();
	}
	public double evalY(double _x, double _z)
	{
		return - (getA() * _x + getC() * _z + getD()) / getB();
	}
	public double evalZ(double _x, double _y)
	{
		return - (getA() * _x + getB() * _y + getD()) / getC();
	}

	public double evalEquasion(Dot3d _d)
	{
		return getA() * _d.getX() + getB() * _d.getY() + getC() * _d.getZ() + getD();
	}
	
	public String toString()
	{
		return "Plane3d(A=" + getA() + ", B=" + getB() + ", C=" + getC() + ", D=" + getD() + ")";
	}
}