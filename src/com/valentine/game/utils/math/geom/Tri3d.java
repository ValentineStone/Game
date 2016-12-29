package com.valentine.game.utils.math.geom;

public class Tri3d extends Tri2d
{
	public Tri3d(Dot3d _a, Dot3d _b, Dot3d _c)
	{
		a = new Dot3d(_a);
		b = new Dot3d(_b);
		c = new Dot3d(_c);
	}
	
	public Tri3d(Tri3d _t)
	{
		this(_t.getA(), _t.getB(), _t.getC());
	}

	
	
	public double getAx()
	{
		return a.x;
	}
	public double getAy()
	{
		return a.y;
	}
	public double getAz()
	{
		return getA().z;
	}
	public double getBx()
	{
		return b.x;
	}
	public double getBy()
	{
		return b.y;
	}
	public double getBz()
	{
		return getB().z;
	}
	public double getCx()
	{
		return c.x;
	}
	public double getCy()
	{
		return c.y;
	}
	public double getCz()
	{
		return getC().z;
	}

	
	
	public Dot3d getA()
	{
		return new Dot3d((Dot3d)a);
	}
	public Dot3d getB()
	{
		return new Dot3d((Dot3d)b);
	}
	public Dot3d getC()
	{
		return new Dot3d((Dot3d)c);
	}
	
	
	
	public void setA(Dot3d _a)
	{
		a = new Dot3d(_a);
	}
	public void setB(Dot3d _b)
	{
		b = new Dot3d(_b);
	}
	public void setC(Dot3d _c)
	{
		c = new Dot3d(_c);
	}
	
	
	
	public boolean equals(Object _o)
	{
		if (_o instanceof Tri3d)
		{
			Tri3d _t = (Tri3d) _o;
			return a.equals(_t.a) && b.equals(_t.b) && c.equals(_t.c);
		}
		else
			return false;
	}
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Tri3d[a:")
				.append(a)
				.append(", b:")
				.append(b)
				.append(", c:")
				.append(c)
				.append(']')
			.toString();
	}

}
