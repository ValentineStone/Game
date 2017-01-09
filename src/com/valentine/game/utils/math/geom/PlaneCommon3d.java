package com.valentine.game.utils.math.geom;

public class PlaneCommon3d extends Plane3d
{
	private double A = 0;
	private double B = 0;
	private double C = 0;
	private double D = 0;
	
	public PlaneCommon3d(double _A, double _B, double _C, double _D)
	{
		setA(_A);
		setB(_B);
		setC(_C);
		setD(_D);
	}


	
	
	
	
	public double getA()
	{
		return A;
	}

	public void setA(double _a)
	{
		A = _a;
	}






	public double getB()
	{
		return B;
	}

	public void setB(double _b)
	{
		B = _b;
	}






	public double getC()
	{
		return C;
	}

	public void setC(double _c)
	{
		C = _c;
	}






	public double getD()
	{
		return D;
	}
	
	
	
	public void setD(double _d)
	{
		D = _d;
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
		
		plane.setA(y2*z3 - z2*y3);
		plane.setB(z2*x3 - x2*z3);
		plane.setC(x2*y3 - y2*x3);
		plane.setD(- (plane.getA() * x1 + plane.getB() * y1 + plane.getC() * z1));
		
		return plane;
	}
}
