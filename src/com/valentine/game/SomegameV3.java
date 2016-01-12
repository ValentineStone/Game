package com.valentine.game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.valentine.game.core.Game;
import com.valentine.game.core.Input;
import com.valentine.game.core.Looper;
import com.valentine.game.entity.ambient.Star;
import com.valentine.game.entity.creatures.Collider;
import com.valentine.game.entity.creatures.Player;
import com.valentine.game.entity.creatures.Rotor;
import com.valentine.game.entity.creatures.Rotor2;
import com.valentine.game.entity.ui.FpsUpsCounter;
import com.valentine.game.entity.ui.OnPointInfo;

public class SomegameV3 extends Game implements KeyListener
{	
	public void assemble()
	{
		super.assemble();
		
		Input.addKeyListener(this);
		
		setFillColor(Color.BLACK);
		setDrawColor(Color.WHITE);
		
		for (int i = 0; i < 1; i++) new Collider(this);
		for (int i = 0; i < 1; i++) new Rotor(this);
		for (int i = 0; i < 1; i++) new Rotor2(this);
		
		Player player = new Player(this);
		
		new OnPointInfo(this, 10, 10);
		
		new FpsUpsCounter(this, 10, 130);
		
		new Star(this, getWidth() / 2 - getHeight() / 3, 0, getHeight() / 1.5);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void keyPressed(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_ESCAPE:
			{
				if (Looper.isRunning()) Looper.pause();
				else Looper.play();
				break;
			}
		}
	}

	public void keyReleased(KeyEvent _keyEvent) {}

	public void keyTyped(KeyEvent _keyEvent) {}
}
