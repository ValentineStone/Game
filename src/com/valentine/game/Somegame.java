package com.valentine.game;

import java.awt.Color;

import com.valentine.game.entity.Player;
import com.valentine.game.utils.Game;
import com.valentine.game.utils.Input;

public class Somegame extends Game
{
	public void assemble()
	{
		setBackgroundColor(new Color(0,0,20));
		
		/*
		LagrangeLine line = new LagrangeLine(this, 5, 30);
		add(line);
		Input.addMouseListener(line);
		Input.addMouseMotionListener(line);
		
		Container ballContainer = new Container(this, 500, 30, 400, 400);
		ballContainer.setBorderColor(Color.MAGENTA);
		add(ballContainer);
		for (int i = 0; i < 30; i++) ballContainer.add(new Circle(ballContainer));
		
		Container colliderContainer = new Container(this, 10, 10, 200, 200);
		colliderContainer.setBorderColor(Color.CYAN);
		add(colliderContainer);
		for (int i = 0; i < 2; i++) colliderContainer.add(new Collider(colliderContainer));
		
		*/
		
		Player player = new Player(this);
		add(player);
		Input.addKeyListener(player);
	}
}
