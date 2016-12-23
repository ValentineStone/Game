package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.math.geom.*;

public class GeometryTest extends RootContainer
{
	public final GeometryContainer geomCont;
	
	public GeometryTest(Dimension _dimension)
	{
		super(_dimension);
		
		geomCont = new GeometryContainer(this, 0, 0, getWidth(), getHeight());
		
		geomCont.add(new Dot2d(10, 10));
		geomCont.add(new Seg2d(new Dot2d(50, 50), new Dot2d(30, 20)));
		geomCont.add(new Circle2d(new Dot2d(100, 100),  30));
	}
}
