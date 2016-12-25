package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.math.*;
import com.valentine.game.utils.math.geom.*;
import com.valentine.game.utils.painters.*;

public class GeometryTest extends RootContainer
{
	public final GeometryContainer geomCont;
	
	public final Entity r1;
	public final Entity r2;
	public final Entity r3;
	
	public final Tri2d entityTri;
	
	public GeometryTest(Dimension _dimension)
	{
		super(_dimension);
		
		geomCont = new GeometryContainer(this, 0, 0, getWidth(), getHeight());
		
		
		
		geomCont.add(new Dot2d(10, 10));
		geomCont.add(new Seg2d(new Dot2d(50, 50), new Dot2d(30, 20)));
		geomCont.add(new Circle2d(new Dot2d(100, 100),  30));
		
		LineCommon2d ln0 = new LineCommon2d(1, 0.7, 160);
		LineCommon2d ln1 = LineCommon2d.perpendicular(ln0);
		LineCommon2d ln2 = LineCommon2d.perpendicular(ln1);
		LineCommon2d ln3 = LineCommon2d.perpendicular(ln2);
		

		geomCont.add(ln0);
		geomCont.add(ln1);
		geomCont.add(ln2);
		geomCont.add(ln3);
		
		geomCont.add(new Tri2d(new Dot2d(-30, -30), new Dot2d(-150, -210), new Dot2d(-10, -90)));
		
		
		
		for (int i = 0; i < 1; i++)
		{
			geomCont.add
			(
				new Tri2d
				(
					new Dot2d(MathExt.random(- getWidth() / 5, getWidth() / 5), MathExt.random(- getHeight() / 5, getHeight() / 5)),
					new Dot2d(MathExt.random(- getWidth() / 5, getWidth() / 5), MathExt.random(- getHeight() / 5, getHeight() / 5)),
					new Dot2d(MathExt.random(- getWidth() / 5, getWidth() / 5), MathExt.random(- getHeight() / 5, getHeight() / 5))
				)
			);
		}
		
		
		
		r1 = new Rotor(this);
		r2 = new Rotor(this);
		r3 = new Rotor(this);
		
		entityTri = new Tri2d(r1.getCenterDot(), r2.getCenterDot(), r3.getCenterDot());
		
		new FpsUpsCounter(this, 10, 10);
	}
	
	public void update()
	{
		super.update();
		
		entityTri.setA(r1.getCenterDot());
		entityTri.setB(r2.getCenterDot());
		entityTri.setC(r3.getCenterDot());
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		_screen.setColor(Color.WHITE);
		GeometryPainter.paint(_screen, entityTri);
	}
}
