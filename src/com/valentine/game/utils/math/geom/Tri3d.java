package com.valentine.game.utils.math.geom;

public class Tri3d extends Tri2d
{
	public Tri3d(Dot3d _a, Dot3d _b, Dot3d _c)
	{
		setA(new Dot3d(_a));
		setB(new Dot3d(_b));
		setC(new Dot3d(_c));
	}
	
	public Tri3d(Tri3d _t)
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
	public double getAz()
	{
		return getA().getZ();
	}
	public double getBx()
	{
		return getB().getX();
	}
	public double getBy()
	{
		return getB().getY();
	}
	public double getBz()
	{
		return getB().getZ();
	}
	public double getCx()
	{
		return getC().getX();
	}
	public double getCy()
	{
		return getC().getY();
	}
	public double getCz()
	{
		return getC().getZ();
	}

	
	
	public Dot3d getA()
	{
		return new Dot3d((Dot3d)super.getA());
	}
	public Dot3d getB()
	{
		return new Dot3d((Dot3d)super.getB());
	}
	public Dot3d getC()
	{
		return new Dot3d((Dot3d)super.getC());
	}
	
	
	
	public void setA(Dot3d _a)
	{
		super.setA(new Dot3d(_a));
	}
	public void setB(Dot3d _b)
	{
		super.setB(new Dot3d(_b));
	}
	public void setC(Dot3d _c)
	{
		super.setC(new Dot3d(_c));
	}
	
	
	
	public boolean equals(Object _o)
	{
		if (_o instanceof Tri3d)
		{
			Tri3d _t = (Tri3d) _o;
			return getA().equals(_t.getA()) && getB().equals(_t.getB()) && getC().equals(_t.getC());
		}
		else
			return false;
	}
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Tri3d[a:")
				.append(getA())
				.append(", b:")
				.append(getB())
				.append(", c:")
				.append(getC())
				.append(']')
			.toString();
	}

}
