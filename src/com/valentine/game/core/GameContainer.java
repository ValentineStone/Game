package com.valentine.game.core;

import java.awt.Dimension;

import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.base.Entity;

public class GameContainer extends Container
{
	public GameContainer(Entity _game, Dimension _size)
	{
		super(null, 0, 0, _size.getWidth(), _size.getHeight());
		
		this.moveIn(_game);
	}
}
