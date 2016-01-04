package com.valentine.game;

import java.awt.Color;

import com.valentine.game.core.Game;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.creatures.*;

public class Somegame extends Game
{
	public void assemble()
	{
		setFillColor(Color.BLACK);
		
		Container box1, box2;
		
		box1 = new Container(this, 0, 0, getWidth() / 2, getHeight());
		box2 = new Container(this, getWidth() / 2, 0, getWidth() / 2, getHeight());
		
		box1.setFillColor(new Color(0, 0, 20));
		box1.setDrawColor(Color.WHITE);
		box2.setFillColor(new Color(20, 0, 0));
		box2.setDrawColor(Color.WHITE);
		
		new Player(box1);
		new Player(box2);
		
	}
}
