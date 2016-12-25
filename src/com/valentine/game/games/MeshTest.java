package com.valentine.game.games;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.math.geom.*;
import com.valentine.game.utils.painters.*;

public class MeshTest extends RootContainer
{
	int count = 30;
	
	Dot2d[] dots = new Dot2d[count];
	Rotor[] rotors = new Rotor[count];
	
	Entity entity;
	
	List<Tri2d> tris;
	
	public MeshTest(Dimension _dimension)
	{
		super(_dimension);
		
		for (int i = 0; i < count; i++)
			rotors[i] = new Rotor(this);
		
		//mesh = new Mesh3d(dots[0], dots[1], dots[2], dots);
		
		entity = new PlayerSpacecraft(this);
		
		new FpsUpsCounter(this, 10, 10);
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		
		for (Tri2d tri : tris)
		{
			_screen.setColor(Color.WHITE);
			GeometryPainter.paint(_screen, tri);
			Circle2d circumcircle = Tri2d.getCircumcircle(tri);
			if (Circle2d.contains(circumcircle, entity.getCenterDot()))
			{
				_screen.setColor(Color.YELLOW);
				//GeometryPainter.paint(_screen, circumcircle);
				GeometryPainter.paint(_screen, tri);
			}
		}
	}
	
	public void update()
	{
		super.update();
		
		for (int i = 0; i < count; i++)
			dots[i] = rotors[i].getCenterDot();
		
		tris = Triangulation.triangulate(Arrays.asList(dots));
	}

}
