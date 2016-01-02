package com.valentine.game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.valentine.game.entity.Collider;
import com.valentine.game.entity.Container;
import com.valentine.game.entity.EntityInfoBox;
import com.valentine.game.entity.HelloWorld;
import com.valentine.game.entity.Link;
import com.valentine.game.entity.Player;
import com.valentine.game.entity.Rotor;
import com.valentine.game.utils.Game;
import com.valentine.game.utils.Input;
import com.valentine.game.utils.Looper;

public class SomegameV1 extends Game implements KeyListener
{
	public void assemble()
	{	
		Input.addKeyListener(this);
		
		setFillColor(new Color(0,0,20));
		
		Collider colli = new Collider();
		colli.rotationVelocity = 0.01;
		add(colli);
		
		for (int i = 0; i < 50; i++) add(new HelloWorld(this));
		
		Container rotorAquarium = new Container(this,3*getWidth()/5, 0, 2*getWidth()/5, getHeight()/2);
		add(rotorAquarium);
		rotorAquarium.setFillColor(new Color(0,10,40,240));
		rotorAquarium.setDrawColor(Color.WHITE);
		for (int i = 0; i < 30; i++) rotorAquarium.add(new Rotor());
		
		Container colliderAquarium = new Container(this, 6*getWidth()/7, getHeight()/2, getWidth()/7, getHeight()/5);
		add(colliderAquarium);
		colliderAquarium.setFillColor(new Color(50,0,30,100));
		colliderAquarium.setDrawColor(Color.YELLOW);
		for (int i = 0; i < 10; i++)
		{
			Collider collider = new Collider();
			EntityInfoBox infobox = new EntityInfoBox(6*getWidth()/7 - EntityInfoBox.WIDTH - 1, getHeight()/2 + i * (EntityInfoBox.HEIGHT + 1), collider);
			Link link = new Link(infobox, collider);
			add(infobox);
			colliderAquarium.add(collider);
			add(link);
		}	
		
		Container playerContainer = new Container(this, 50, 50, getWidth()/2, 4 * getHeight()/5);
		playerContainer.setFillColor(new Color(0,30,0,50));
		playerContainer.setDrawColor(new Color(0,130,0));
		add(playerContainer);
		
		Player player = new Player(playerContainer);
		Input.addKeyListener(player);
		
		EntityInfoBox playerInfo = new EntityInfoBox(1, 1, player);
		
		playerContainer.add(new Link(playerInfo, player));
		
		playerContainer.add(playerInfo);
		
		playerContainer.add(player);
		
		
		Container secondRotorContainer = new Container(this, getWidth()/2 + 100, getHeight()/2 + 50, 300, 400);
		
		secondRotorContainer.setFillColor(new Color(0,0,0,180));
		secondRotorContainer.setDrawColor(Color.WHITE);
		
		
		add(secondRotorContainer);
		
		Rotor rotor1 = new Rotor();
		Rotor rotor2 = new Rotor();
		Rotor rotor3 = new Rotor();
		Link rotorLink1 = new Link(rotor1, rotor2);
		Link rotorLink2 = new Link(rotor2, rotor3);
		Link rotorLink3 = new Link(rotor3, rotor1);
		secondRotorContainer.add(rotorLink1);
		secondRotorContainer.add(rotorLink2);
		secondRotorContainer.add(rotorLink3);
		secondRotorContainer.add(rotor1);
		secondRotorContainer.add(rotor2);
		secondRotorContainer.add(rotor3);
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
			case KeyEvent.VK_SPACE:
			{
				if (!Looper.isRunning()) Looper.loop();
				
				break;
			}

		}
	}

	public void keyReleased(KeyEvent _keyEvent) {}

	public void keyTyped(KeyEvent _keyEvent) {}
}
