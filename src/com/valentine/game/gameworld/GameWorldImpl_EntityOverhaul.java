package com.valentine.game.gameworld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.valentine.game.GameWorld;

public class GameWorldImpl_EntityOverhaul extends GameWorld
{
	private ArrayList<Colliding> entities;
	
	private int n = 30;

	public void assemble()
	{
		entities = new ArrayList<Colliding>();
		for (int i = 0; i < n; i++) entities.add(new Colliding(i));
		ready = true;
	}

	public void paint(Graphics _graphics)
	{
		for (Colliding entity : entities)
			entity.paint(_graphics);
		
	}
	
	public void update()
	{
		for (int i = 0; i < n; i++)
		{
			entities.get(i).update();
			for (int j = i + 1; j < n; j++) {
				entities.get(i).collide(entities.get(j));
			}
		}
			
	}

	public void keyPressed(KeyEvent _event) {}
	
	public void keyReleased(KeyEvent _event) {}

	public void mousePressed(MouseEvent _mouseEvent) {}

	public void mouseReleased(MouseEvent _mouseEvent) {}

	public void mouseDragged(MouseEvent _e) {}	

}
