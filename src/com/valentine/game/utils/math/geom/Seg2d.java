package com.valentine.game.utils.math.geom;

public class Seg2d extends Line2d
{
	private Dot2d d1;
	private Dot2d d2;
	
	public Seg2d(Dot2d _d1, Dot2d _d2)
	{
		d1 = new Dot2d(_d1);
		d2 = new Dot2d(_d2);
	}
	
	public Dot2d getCenter()
	{
		return new Dot2d(getCenterX(), getCenterY());
	}
	
	public double getCenterX()
	{
		return d1.getX() + (d2.getX() - d1.getX()) / 2;
	}
	
	public double getCenterY()
	{
		return d1.getY() + (d2.getY() - d1.getY()) / 2;
	}
	
	
	
	

	public double getA()
	{
		return d2.getY() - d1.getY();
	}

	public double getB()
	{
		return d1.getX() - d2.getX();
	}

	public double getC()
	{
		return d1.getX() * (d1.getY() - d2.getY()) + d1.getY() * (d2.getX() - d1.getX());
	}
	
	
	

	public double getD1X()
	{
		return d1.getX();
	}
	public double getD1Y()
	{
		return d1.getY();
	}
	public double getD2X()
	{
		return d2.getX();
	}
	public double getD2Y()
	{
		return d2.getY();
	}
	
	
	
	public Dot2d getD1()
	{
		return new Dot2d(d1);
	}

	public Dot2d getD2()
	{
		return new Dot2d(d2);
	}

	
	
	
	
	
	
	public LineCommon2d getLine()
	{
		return new LineCommon2d(getA(), getB(), getC());
	}
	

	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Seg2d[a:")
				.append(d1)
				.append(", b:")
				.append(d2)
				.append(']')
			.toString();
	}
}
