package com.valentine.game.utils.math.geom;

import com.valentine.game.utils.math.*;

public class Tri2d implements Geometry
{
	public Dot2d a;
	public Dot2d b;
	public Dot2d c;
	
	public Tri2d(Dot2d _a, Dot2d _b, Dot2d _c)
	{
		a = _a;
		b = _b;
		c = _c;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static Circle2d getCircumcircle(Tri2d _tri)
	{
		LineFunc2d ab = LineFunc2d.toLine(_tri.a, _tri.b);
		LineFunc2d ac = LineFunc2d.toLine(_tri.a, _tri.c);
		Dot2d circumcircleDot = LineFunc2d.intersect(ab, ac);
		
		return null;
	}
	
	
	
	
	public static void main(String ... _args)
	{
		Dot2d a = new Dot2d(0.8432, -1.7123);
		Dot2d b = new Dot2d(1.52362, 1.73234);
		Dot2d c = new Dot2d(-1.78896, 1.1243);
		
		Seg2d ab = new Seg2d(a, b);
		Seg2d ac = new Seg2d(a, c);
		
		LineFunc2d abP = LineFunc2d.perpAtX(LineFunc2d.toLine(ab), ab.centerX());
		LineFunc2d acP = LineFunc2d.perpAtX(LineFunc2d.toLine(ac), ac.centerX());
		
		Dot2d circumcircleDot = LineFunc2d.intersect(abP, acP);
		
		System.err.println(a);
		System.err.println(b);
		System.err.println(c);
		
		System.err.println(ab);
		System.err.println(ac);
		
		System.err.println(abP);
		System.err.println(acP);
		
		System.err.println(circumcircleDot);
		
		Circle2d circumcircle = new Circle2d(circumcircleDot, MathExt.distanceMake(circumcircleDot, a));
		
		System.err.println(circumcircle);
		System.err.println(MathExt.distanceMake(circumcircle, b));
		System.err.println(MathExt.distanceMake(circumcircle, c));
	}
}
