package com.valentine.game.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.valentine.game.GameWorld;
import com.valentine.game.entity.*;

public class GameWorldImpl_EntityOverhaul extends GameWorld
{
	private ArrayList<Entity> entities;
	
	private int n = 70;

	public void assemble()
	{
		entities = new ArrayList<Entity>();
		
		//for (int i = 0; i < n; i++) entities.add(Math.random() > 0.666 ? new Collider(i) : (Math.random() > 0.5 ? new Circle() : new HelloWorld()));
		
		entities.add(new Container(){
			public void make()
			{
				entities.add(new Circle());
				entities.add(new Container(){
					public void make()
					{
						x = 60;
						y = 500;
						entities.add(new HelloWorld());
					}
				});
			}
		});
		
		ready = true;
	}

	public void paint(Graphics _graphics)
	{
		for (Entity entity : entities)
			entity.paint(_graphics);
		
	}
	
	public void update()
	{
		for (int i = 0; i < n; i++)
		{
			entities.get(i).update();
			
			if (entities.get(i) instanceof Collider)
				{
				for (int j = i + 1; j < n; j++)
				{
					if (entities.get(j) instanceof Collider)
					{
						((Collider)(entities.get(i))).collide((Collider)(entities.get(j)));
					}
				}
			}
		}
			
	}

	public void keyPressed(KeyEvent _event) {}
	
	public void keyReleased(KeyEvent _event) {}

	public void mousePressed(MouseEvent _mouseEvent) {}

	public void mouseReleased(MouseEvent _mouseEvent) {}

	public void mouseDragged(MouseEvent _e) {}	

}
