package com.valentine.game;

import java.awt.Color;

import com.valentine.game.entity.Player;
import com.valentine.game.entity.Rotor;
import com.valentine.game.utils.Game;
import com.valentine.game.utils.Input;

public class Somegame extends Game
{
	public void assemble()
	{
		setBackgroundColor(new Color(0,0,20));
		
		Player player = new Player(this);
		add(player);
		Input.addKeyListener(player);
		
		for (int i = 0; i < 100; i++) add(new Rotor(this));
	}
}
