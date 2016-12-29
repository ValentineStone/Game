package com.valentine.game.utils.math.geom;

public class PlaneCommon3d extends Plane3d
{
	public double A = 0;
	public double B = 0;
	public double C = 0;
	public double D = 0;
	
	public PlaneCommon3d(double _A, double _B, double _C, double _D)
	{
		A = _A;
		B = _B;
		C = _C;
		D = _D;
	}


	
	
	
	
	public double getA()
	{
		return A;
	}

	public double getB()
	{
		return B;
	}

	public double getC()
	{
		return C;
	}

	public double getD()
	{
		return D;
	}
	
	
	
	public static PlaneCommon3d toPlane(Dot3d _a, Dot3d _b, Dot3d _c)
	{
		return toPlane(new Tri3d(_a, _b, _c));
	}
	
	public static PlaneCommon3d toPlane(Tri3d _t)
	{
		PlaneCommon3d plane = new PlaneCommon3d(0, 0, 0, 0);
		
		double x1 = _t.getAx();
		double y1 = _t.getAy();
		double z1 = _t.getAz();
		
		double x2 = _t.getBx() - x1;
		double y2 = _t.getBy() - y1;
		double z2 = _t.getBz() - z1;

		double x3 = _t.getCx() - x1;
		double y3 = _t.getCy() - y1;
		double z3 = _t.getCz() - z1;
		
		plane.A = y2*z3 - z2*y3;
		plane.B = z2*x3 - x2*z3;
		plane.C = x2*y3 - y2*x3;
		plane.D = - (plane.A * x1 + plane.B * y1 + plane.C * z1);
		
		return plane;
	}
}
