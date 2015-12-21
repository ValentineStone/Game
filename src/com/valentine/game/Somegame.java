package com.valentine.game;

import com.valentine.game.entity.Player;
import com.valentine.game.utils.Game;
import com.valentine.game.utils.Input;

public class Somegame extends Game
{
	
	public void assemble()
	{
		Input.addInputListener((Player)add(new Player(this)));
		
		super.assemble();
	}
}
