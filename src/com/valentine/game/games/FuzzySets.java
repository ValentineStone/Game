package com.valentine.game.games;

import java.awt.Dimension;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.entity.fuzzyset.*;

public class FuzzySets extends Container
{
	public FuzzySets(Dimension _dimension)
	{
		super(null, 0, 0, _dimension.getWidth(), _dimension.getHeight());
		
		ContainerWindow funwin = new ContainerWindow(this, 10, 10, 200, 200);
		
		new Collider(funwin);
		new Rotor(funwin);
		new Rotor2(funwin);
		
		/*
		ContainerWindow flow = new ContainerWindow(this, 10, 220, 500, 500);
		FlowGame flowGame = new FlowGame(new Dimension(500, 470));
		flowGame.setY(30);
		flow.moveIn(flowGame);
		*/
		
		new DragHandler(this, new Clock(this, 50));
		
		new FuzzySetsWindow(this, 10, 220, 500, 500);
	}
}
