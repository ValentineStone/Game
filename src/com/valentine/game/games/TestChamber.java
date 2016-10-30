package com.valentine.game.games;

import java.awt.Color;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.ambient.Link;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.EntityInfoBox;

public class TestChamber extends Yame implements KeyListener
{
	public void assemble()
	{	
		Input.addKeyListener(this);
		
		setFillColor(new Color(0,0,20));
		
		Collider colli = new Collider(this);
		colli.setRotationVelocity(0.01);
		
		for (int i = 0; i < 50; i++) new HelloWorld(this);
		
		Container rotorAquarium = new Container(this, 3*getWidth()/5, 0, 2*getWidth()/5, getHeight()/2);
		rotorAquarium.setFillColor(new Color(0,10,40,240));
		rotorAquarium.setDrawColor(Color.WHITE);
		for (int i = 0; i < 30; i++) new Rotor(rotorAquarium);
		
		Container colliderAquarium = new Container(this, 6*getWidth()/7, getHeight()/2, getWidth()/7, getHeight()/5);

		colliderAquarium.setFillColor(new Color(50,0,30,100));
		colliderAquarium.setDrawColor(Color.YELLOW);
		
		for (int i = 0; i < 10; i++)
		{
			Collider collider = new Collider(colliderAquarium);
			EntityInfoBox infobox = new EntityInfoBox(colliderAquarium, 6*getWidth()/7 - EntityInfoBox.WIDTH - 1, getHeight()/2 + i * (EntityInfoBox.HEIGHT + 1), collider);
			Link link = new Link(infobox, collider);
		}	
		
		Container playerContainer = new Container(this, 50, 50, getWidth()/2, 4 * getHeight()/5);
		playerContainer.setFillColor(new Color(0,30,0,50));
		playerContainer.setDrawColor(new Color(0,130,0));
		
		Player player = new Player(playerContainer);
		Input.addKeyListener(player);
		
		EntityInfoBox playerInfo = new EntityInfoBox(playerContainer, 1, 1, player);
		
		new Link(playerInfo, player);
		
		
		Container secondRotorContainer = new Container(this, getWidth()/2 + 100, getHeight()/2 + 50, 300, 400);
		
		secondRotorContainer.setFillColor(new Color(0,0,0,180));
		secondRotorContainer.setDrawColor(Color.WHITE);
		
		Rotor rotor1 = new Rotor(secondRotorContainer);
		Rotor rotor2 = new Rotor(secondRotorContainer);
		Rotor rotor3 = new Rotor(secondRotorContainer);
		Link rotorLink1 = new Link(rotor1, rotor2);
		Link rotorLink2 = new Link(rotor2, rotor3);
		Link rotorLink3 = new Link(rotor3, rotor1);
	}

	public void keyPressed(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_ESCAPE:
			{
				if (NotchLoop.isRunning()) NotchLoop.pause();
				else NotchLoop.play();
				
				break;
			}
			case KeyEvent.VK_SPACE:
			{
				if (!NotchLoop.isRunning()) NotchLoop.loop();
				
				break;
			}

		}
	}

	public void keyReleased(KeyEvent _keyEvent) {}

	public void keyTyped(KeyEvent _keyEvent) {}
}
