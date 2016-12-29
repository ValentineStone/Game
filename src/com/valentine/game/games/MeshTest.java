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
	int count = 100;
	
	Dot2d[] dots = new Dot2d[count];
	Entity[] entities = new Entity[count];
	
	Entity entity;
	
	List<Tri2d> tris;
	
	public MeshTest(Dimension _dimension)
	{
		super(_dimension);
		
		for (int i = 1; i < count; i++)
			entities[i] = new Rotor(this);
		
		entity = new Player(this);
		entities[0] = entity;
		
		new DragHandler(this, (EntityBasicAI) entity);
		
		new FpsUpsCounter(this, 10, 10)
			.setFillColor(getFillColor());
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		
		for (Tri2d tri : tris)
		{
			_screen.setColor(Color.WHITE);
			GeometryPainter.paint(_screen, tri);
		}
	}
	
	public void update()
	{
		super.update();
		
		for (int i = 0; i < count; i++)
			dots[i] = entities[i].getCenterDot();
		
		tris = Triangulation.triangulate(Arrays.asList(dots));
	}

}
