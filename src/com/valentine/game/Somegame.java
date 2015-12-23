package com.valentine.game;

import java.awt.Color;

import com.valentine.game.entity.Collider;
import com.valentine.game.entity.Container;
import com.valentine.game.entity.EntityInfoBox;
import com.valentine.game.entity.HelloWorld;
import com.valentine.game.entity.Player;
import com.valentine.game.entity.Rotor;
import com.valentine.game.utils.Game;
import com.valentine.game.utils.Input;

public class Somegame extends Game
{
	public void assemble()
	{
		for (int i = 0; i < 100; i++) add(new HelloWorld(this));
		
		Container rotorAquarium = new Container(this,3*getWidth()/5, 0, 2*getWidth()/5, getHeight()/2);
		add(rotorAquarium);
		rotorAquarium.setFillColor(new Color(0,10,40,240));
		rotorAquarium.setDrawColor(Color.WHITE);
		for (int i = 0; i < 30; i++) rotorAquarium.add(new Rotor(rotorAquarium));
		
		Container colliderAquarium = new Container(this, 6*getWidth()/7, getHeight()/2, getWidth()/7, getHeight()/5);
		add(colliderAquarium);
		colliderAquarium.setFillColor(new Color(50,0,30,100));
		colliderAquarium.setDrawColor(Color.YELLOW);
		for (int i = 0; i < 3; i++)
		{
			colliderAquarium.add(new Collider(colliderAquarium));
			add(new EntityInfoBox(this, 6*getWidth()/7 - 250, getHeight()/2 + 115*(i), colliderAquarium.get(i)));
		}
		
		setFillColor(new Color(0,0,20));
		
		
		Player player = new Player(this);
		
		add(new EntityInfoBox(this, 0, 0, player));
		add(player);
		Input.addKeyListener(player);
	}
}
