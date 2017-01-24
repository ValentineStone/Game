package com.valentine.game.utils.math.geom;

import com.valentine.game.utils.math.*;

public class Tri2d implements Geometry
{
	protected Dot2d a;
	protected Dot2d b;
	protected Dot2d c;
	
	protected Tri2d()
	{}
	
	public Tri2d(Dot2d _a, Dot2d _b, Dot2d _c)
	{
		setA(new Dot2d(_a));
		setB(new Dot2d(_b));
		setC(new Dot2d(_c));
	}
	
	public Tri2d(Tri2d _t)
	{
		this(_t.getA(), _t.getB(), _t.getC());
	}

	
	
	public double getAx()
	{
		return getA().getX();
	}
	public double getAy()
	{
		return getA().getY();
	}
	public double getBx()
	{
		return getB().getX();
	}
	public double getBy()
	{
		return getB().getY();
	}
	public double getCx()
	{
		return getC().getX();
	}
	public double getCy()
	{
		return getC().getY();
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
		a = new Dot2d(_a);
	}
	public void setB(Dot2d _b)
	{
		b = new Dot2d(_b);
	}
	public void setC(Dot2d _c)
	{
		c = new Dot2d(_c);
	}
	
	
	
	public boolean equals(Object _o)
	{
		if (_o instanceof Tri2d)
		{
			Tri2d _t = (Tri2d) _o;
			return getA().equals(_t.getA()) && getB().equals(_t.getB()) && getC().equals(_t.getC());
		}
		else
			return false;
	}
	
	
	public boolean contains(Dot2d _d)
	{
		double min = MathExt.rotationMake(_d, getA());
		double mid = MathExt.rotationMake(_d, getB());
		double max = MathExt.rotationMake(_d, getC());
		
		double swap;
		
		if (min > mid)
		{
			swap = min;
			min = mid;
			mid = swap;
		}
		if (mid > max)
		{

			swap = mid;
			mid = max;
			max = swap;
		}
		if (min > mid)
		{
			swap = min;
			min = mid;
			mid = swap;
		}
		
		if (max - min < MathExt.PI_1_1)
			return false;
		
		if (mid <= MathExt.PI_1_1 + min && mid >= max - MathExt.PI_1_1)
			return true;
		else
			return false;
	}

	public static Circle2d getCircumcircle(Tri2d _tri)
	{
		Seg2d ab = new Seg2d(_tri.getA(), _tri.getB());
		Seg2d ac = new Seg2d(_tri.getA(), _tri.getC());
		
		Dot2d abCenter = ab.getCenter();
		Dot2d acCenter = ac.getCenter();
		
		Line2d abPerp = LineCommon2d.perpendicularAt(ab, abCenter);
		Line2d acPerp = LineCommon2d.perpendicularAt(ac, acCenter);
		
		Dot2d circumcircleDot = Line2d.intersect(abPerp, acPerp);
		
		if (circumcircleDot == null)
			return null;
		
		double r = MathExt.distanceMake(_tri.getA(), circumcircleDot);
		
		return new Circle2d(circumcircleDot, r);
	}
	
	
	
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Tri2d[a:")
				.append(getA())
				.append(", b:")
				.append(getB())
				.append(", c:")
				.append(getC())
				.append(']')
			.toString();
	}
}
