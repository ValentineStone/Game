package com.valentine.game;

import com.valentine.game.entity.*;

public class Somegame extends Game {
	
	public void assemble()
	{
		for (int i = 0; i < 800; i++) add(new Circle(i));
		setReady(true);
		super.assemble();
	}
}
