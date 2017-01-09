package com.valentine.game.utils.math.geom;

public class Dot3d extends Dot2d
{
	private double z;
	
	public Dot3d(double _x, double _y, double _z)
	{
		super(_x, _y);
		setZ(_z);
	}
	
	public Dot3d(Dot3d _d)
	{
		this(_d.getX(), _d.getY(), _d.getZ());
	}
	


	public double getZ()
	{
		return z;
	}

	public void setZ(double _z)
	{
		z = _z;
	}

	public boolean equals(Object _o)
	{
		if (_o instanceof Dot3d)
		{
			Dot3d d = (Dot3d) _o;
			return getX() == d.getX() && getY() == d.getY() && getZ() == d.getZ();
		}
		else if (_o instanceof Dot2d)
		{
			Dot2d d = (Dot2d) _o;
			return getX() == d.getX() && getY() == d.getY();
		}
		else
			return false;
	}
	
	
	
	public String toString()
	{
		return
			new StringBuilder()
				.append("Dot3d[")
				.append(getX())
				.append(", ")
				.append(getY())
				.append(", ")
				.append(getZ())
				.append(']')
			.toString();
	}
}
