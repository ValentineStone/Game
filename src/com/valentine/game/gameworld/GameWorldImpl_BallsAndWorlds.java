package com.valentine.game.gameworld;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import com.valentine.game.GameWorld;
import com.valentine.game.gameworld.entity.Circle;
import com.valentine.game.gameworld.entity.HelloWorld;
import com.valentine.game.gameworld.entity.Player;

public class GameWorldImpl_BallsAndWorlds extends GameWorld {

	private ArrayList<OldEntity> entities;
	private Player player;

	public void update() {
		for (OldEntity entity : entities)
			entity.update();
	}

	public void paint(Graphics _graphics) {
		for (OldEntity entity : entities)
			entity.paint(_graphics);
	}

	public void assemble() {
		player = new Player();
		entities = new ArrayList<OldEntity>(Arrays.asList(genRandEntities(100)));
		entities.add(player);
		ready = true;
	}
	
	static public OldEntity[] genRandEntities(int _n) {
		OldEntity[] entities = new OldEntity[_n];
		for (int i = 0; i < _n; i++) {
			entities[i] = Math.random() > 0.5 ? new Circle() : new HelloWorld();
		}
		return entities;
	}
	
	public void keyPressed(KeyEvent _event) { player.takeAction(_event);}
	public void keyReleased(KeyEvent _event) { player.takeAction(_event);}

}