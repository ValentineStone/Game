package com.valentine.game.utils.math.geom;

import com.valentine.game.utils.math.*;

public class Tri2d implements Geometry
{
	private Dot2d a;
	private Dot2d b;
	private Dot2d c;
	
	public Tri2d(Dot2d _a, Dot2d _b, Dot2d _c)
	{
		a = new Dot2d(_a);
		b = new Dot2d(_b);
		c = new Dot2d(_c);
	}

	
	
	public double getAx()
	{
		return a.x;
	}
	public double getAy()
	{
		return a.y;
	}
	public double getBx()
	{
		return b.x;
	}
	public double getBy()
	{
		return b.y;
	}
	public double getCx()
	{
		return c.x;
	}
	public double getCy()
	{
		return c.y;
	}

	
	
	public Dot2d getA()
	{
		return new Dot2d(a);
	}
	public Dot2d getB()
	{
		return new Dot2d(b);
	}
	public Dot2d getC()
	{
		return new Dot2d(c);
	}
	
	
	
	public void setA(Dot2d _a)
	{
		a = _a;
	}
	public void setB(Dot2d _b)
	{
		b = _b;
	}
	public void setC(Dot2d _c)
	{
		c = _c;
	}



	public static Circle2d getCircumcircle(Tri2d _tri)
	{
		Seg2d ab = new Seg2d(_tri.a, _tri.b);
		Seg2d ac = new Seg2d(_tri.a, _tri.c);
		
		Dot2d abCenter = ab.getCenter();
		Dot2d acCenter = ac.getCenter();
		
		Line2d abPerp = LineCommon2d.perpendicularAt(ab, abCenter);
		Line2d acPerp = LineCommon2d.perpendicularAt(ac, acCenter);
		
		Dot2d circumcircleDot = Line2d.intersect(abPerp, acPerp);
		
		if (circumcircleDot == null)
			return null;
		
		double r = MathExt.distanceMake(_tri.a, circumcircleDot);
		
		return new Circle2d(circumcircleDot, r);
	}
	
	
	
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Tri2d[a:")
				.append(a)
				.append(", b:")
				.append(b)
				.append(", c:")
				.append(c)
				.append(']')
			.toString();
	}
}
