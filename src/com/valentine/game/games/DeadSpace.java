package com.valentine.game.games;

import java.awt.*;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.ambient.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.entity.vfx.*;

public class DeadSpace extends Game implements KeyListener
{
	private boolean NEW_GAME = false;
	
	private long tick = 0;
	
	public void assemble()
	{
		super.assemble();
		
		new StarrySkyMoving(this);
		
		//new Star(this, getWidth() / 2 - getHeight() / 3, 0, getHeight() / 1.5);
		//new Star(this, getWidth() / 2 - getHeight() / 3, 0, getHeight() / .43);
		new Star(this, 14, 190, 230);
		
		Input.addKeyListener(this);
		
		setFillColor(Color.BLACK);
		setDrawColor(Color.WHITE);
		
		for (int i = 0; i < 5; i++) new Collider(this);
		for (int i = 0; i < 5; i++) new Rotor(this);
		for (int i = 0; i < 5; i++) new Rotor2(this);
		
		Player player = new PlayerSpacecraft(this);
		new Trail(this, player, 5);
		
		new OnPointInfo(this, 10, 10);
		/*
		new Killider(this, player, 1800, 210);
		new Killider(this, player, 1800, 420);
		new Killider(this, player, 1800, 630);
		new Killider(this, player, 1800, 840);
		*/
		
		//new Killider(this, player, 1800, 540);
		
		EntityCounter entityCounter = new EntityCounter(this, Rotor2.class, 10, 130);
		
		new FpsUpsCounter(this, entityCounter.getX() + entityCounter.getWidth() + 17, entityCounter.getY());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void update()
	{
		if (NEW_GAME)
		{
			Input.removeKeyListener(this);
			NEW_GAME = false;
			assemble();
		}
		
		if (tick++ % 20 == 0)
		{
			double rand = Math.random();
			if (rand < 0.333)
			{
				new Collider(this);
			}
			else if (rand > 0.666)
			{
				new Rotor(this);
			}
			else
			{
				new Rotor2(this);
			}
		}
		
		super.update();
	}
	
	
	
	
	
	
	
	
	
	public void keyPressed(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_ESCAPE:
			{
				if (!isUpdatable())
				{
					NEW_GAME = true;
					setUpdatable(true);
				}
				else
				{
					if (Looper.isRunning()) Looper.pause();
					else Looper.play();
				}
				break;
			}
		}
	}

	public void keyReleased(KeyEvent _keyEvent) {}

	public void keyTyped(KeyEvent _keyEvent) {}
}
