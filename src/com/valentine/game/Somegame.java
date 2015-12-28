package com.valentine.game;

import java.awt.Color;

import com.valentine.game.entity.*;
import com.valentine.game.utils.Game;
import com.valentine.game.utils.Input;

public class Somegame extends Game
{
	public void assemble()
	{
		setFillColor(new Color(0,0,20));
		
		for (int i = 0; i < 100; i++) add(new HelloWorld(this));
		
		Container rotorAquarium = new Container(this,3*getWidth()/5, 0, 2*getWidth()/5, getHeight()/2);
		add(rotorAquarium);
		rotorAquarium.setFillColor(new Color(0,10,40,240));
		rotorAquarium.setDrawColor(Color.WHITE);
		for (int i = 0; i < 130; i++) rotorAquarium.add(new Rotor(rotorAquarium));
		
		Container colliderAquarium = new Container(this, 6*getWidth()/7, getHeight()/2, getWidth()/7, getHeight()/5);
		add(colliderAquarium);
		colliderAquarium.setFillColor(new Color(50,0,30,100));
		colliderAquarium.setDrawColor(Color.YELLOW);
		for (int i = 0; i < 2; i++)
		{
			colliderAquarium.add(new Collider(colliderAquarium));
			add(new Link(
							this,
							add(new EntityInfoBox(this, 6*getWidth()/7 - EntityInfoBox.WIDTH - 1, getHeight()/2 + i * (EntityInfoBox.HEIGHT + 1), colliderAquarium.get(i))),
							colliderAquarium.get(i)
							));
		}	
		
		Container playerContainer = new Container(this, 50, 50, getWidth()/2, 4 * getHeight()/5);
		playerContainer.setFillColor(new Color(0,30,0,50));
		playerContainer.setDrawColor(new Color(0,130,0));
		add(playerContainer);
		
		Player player = new Player(playerContainer);
		Input.addKeyListener(player);
		
		EntityInfoBox playerInfo = new EntityInfoBox(playerContainer, 1, 1, player);
		
		playerContainer.add(new Link(playerContainer, playerInfo, player));
		
		playerContainer.add(playerInfo);
		
		playerContainer.add(player);
		
		
		Container secondRotorContainer = new Container(this, getWidth()/2 + 100, getHeight()/2 + 50, 300, 400);
		
		secondRotorContainer.setFillColor(new Color(0,0,0,180));
		secondRotorContainer.setDrawColor(Color.WHITE);
		
		
		add(secondRotorContainer);
		
		Rotor rotor1 = new Rotor(secondRotorContainer);
		Rotor rotor2 = new Rotor(secondRotorContainer);
		Rotor rotor3 = new Rotor(secondRotorContainer);
		Link rotorLink1 = new Link(secondRotorContainer, rotor1, rotor2);
		Link rotorLink2 = new Link(secondRotorContainer, rotor2, rotor3);
		Link rotorLink3 = new Link(secondRotorContainer, rotor3, rotor1);
		secondRotorContainer.add(rotorLink1);
		secondRotorContainer.add(rotorLink2);
		secondRotorContainer.add(rotorLink3);
		secondRotorContainer.add(rotor1);
		secondRotorContainer.add(rotor2);
		secondRotorContainer.add(rotor3);
		
		
	}
}
