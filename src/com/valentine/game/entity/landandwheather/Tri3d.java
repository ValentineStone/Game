package com.valentine.game.entity.landandwheather;

import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class Tri3d implements SurfaceScan
{
	public Dot3d a;
	public Dot3d b;
	public Dot3d c;
	
	private double _ab;
	private double _bc;
	private double _ca;
	
	private double _a;
	private double _b;
	private double _c;
	
	public Tri3d(Dot3d _a, Dot3d _b, Dot3d _c)
	{
		a = _a;
		b = _b;
		c = _c;
		calcSides();
	}
	
	public boolean contains(Dot3d _dot)
	{
		calcDistances(_dot);
		
		if
		(
			   _a + _b <= _bc + _ca
			&& _b + _c <= _ca + _ab
			&& _c + _a <= _ab + _bc
		)
			return true;
		else
			return false;
	}

	public Double valueAt(double _x, double _y)
	{
		calcDistances(_x, _y);
		
		double value = 0;
		 
		System.err.println("-------------------");
		System.err.println(a.z + " | " + b.z + " | " + c.z);
		System.err.println(_a + " | " + _b + " | " + _c);
		System.err.println(_ab + " | " + _bc + " | " + _ca);
		
		value += a.z * (_b + _c - _bc) / (_ab + _ca - _bc);
		value += b.z * (_c + _a - _ca) / (_ab + _bc - _ca);
		value += c.z * (_a + _b - _ab) / (_bc + _ca - _ab);
		
		return value;
	}
	
	private void calcSides()
	{
		_ab = MathExt.distanceMake(a.x, a.y, b.x, b.y);
		_bc = MathExt.distanceMake(b.x, b.y, c.x, c.y);
		_ca = MathExt.distanceMake(c.x, c.y, a.x, a.y);
	}
	
	private void calcDistances(Dot3d _dot)
	{
		calcDistances(_dot.x, _dot.y);
	}
	

	
	private void calcDistances(double _x, double _y)
	{
		_a = MathExt.distanceMake(a.x, a.y, _x, _y);
		_b = MathExt.distanceMake(b.x, b.y, _x, _y);
		_c = MathExt.distanceMake(c.x, c.y, _x, _y);
	}
}
