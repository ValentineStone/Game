package com.valentine.game.utils.math.geom;

public class Seg2d implements Line2dInterface
{
	public Dot2d d1;
	public Dot2d d2;
	
	public Seg2d(Dot2d _d1, Dot2d _d2)
	{
		d1 = _d1;
		d2 = _d2;
	}
	
	public Dot2d center()
	{
		return new Dot2d(centerX(), centerY());
	}
	
	public double centerX()
	{
		return d1.x + (d2.x - d1.x) / 2;
	}
	
	public double centerY()
	{
		return d1.y + (d2.y - d1.y) / 2;
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

	public boolean isVertical()
	{
		return false;
	}

	public boolean isHorizontal()
	{
		return false;
	}
	
	public double xFromY(double _y)
	{
		return - (getB() * _y + getC()) / getA();
	}

	public double yFromX(double _x)
	{
		return - (getA() * _x + getC()) / getB();
	}

	public double getA()
	{
		return d2.y - d1.y;
	}

	public double getB()
	{
		return d1.x - d2.x;
	}

	public double getC()
	{
		return d1.x * (d1.y - d2.y) + d1.y * (d2.x - d1.x);
	}

	public double evalEquasion(Dot2d _d)
	{
		return getA() * _d.x + getB() * _d.y + getC();
	}
	
	public static void main(String ... _args)
	{
		Seg2d seg = new Seg2d(new Dot2d(-1, 1), new Dot2d(1, -1));
		
		System.err.println("A: " + seg.getA() + "\nB: " + seg.getB() + "\nC: " + seg.getC());
		
		System.err.println(seg.evalEquasion(new Dot2d(1,1)));
	}
}
