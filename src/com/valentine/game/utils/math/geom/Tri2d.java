package com.valentine.game.utils.math.geom;

import com.valentine.game.utils.math.*;

public class Tri2d
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
		Line2d ab = Line2d.toLine(_tri.a, _tri.b);
		Line2d ac = Line2d.toLine(_tri.a, _tri.c);
		Dot2d circumcircleDot = Line2d.intersect(ab, ac);
		
		return null;
	}
	
	
	
	
	public static void main(String ... _args)
	{
		Dot2d a = new Dot2d(0.8432, -1.7123);
		Dot2d b = new Dot2d(1.52362, 1.73234);
		Dot2d c = new Dot2d(-1.78896, 1.1243);
		
		Seg2d ab = new Seg2d(a, b);
		Seg2d ac = new Seg2d(a, c);
		
		Line2d abP = Line2d.perpAtX(Line2d.toLine(ab), ab.centerX());
		Line2d acP = Line2d.perpAtX(Line2d.toLine(ac), ac.centerX());
		
		Dot2d circumcircleDot = Line2d.intersect(abP, acP);
		
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
