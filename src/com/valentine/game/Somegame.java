package com.valentine.game;

import java.awt.Color;

import com.valentine.game.entity.*;
import com.valentine.game.entity.line.*;
import com.valentine.game.utils.Game;
import com.valentine.game.utils.InputHandler;

public class Somegame extends Game {
	
	LivingBox box1;
	LivingBox box2;
	LivingBox box3;
	LivingBox box4;
	Player player1;
	Player player2;
	
	public void assemble()
	{
		InputHandler.addInputListener((LagrangeLine)add(new LagrangeLine(this, 5, 10)));
		add(new BezierLine(this, 5, 10));
		add(new Line(this, 4, 10));
		
		for (int i = 0; i < 40; i++) add(new HelloWorld(this));
		
		for (int i = 0; i < 100; i++) add(new Circle(i, this));
		
		for (int i = 0; i < 40; i++) add(new Collider(i, this));
		
		box1 = new LivingBox(this, 20,20,200,200, new Color(100,100,255), new Color(0,0,20,100));
		add(box1);
		for (int i = 0; i < 10; i++) box1.add(new Circle(i, box1));
		box1.add(new Collider(1, box1));
		box1.add(new Collider(2, box1));
		
		box2 = new LivingBox(this, 270,50,400,300, Color.GREEN, new Color(0,0,25,240));
		add(box2);
		box2.add(new LagrangeLine(box2, 4, 5));
		for (int i = 0; i < 30; i++) box2.add(new Circle(i, box2));
		
		box3 = new LivingBox(this, 70,480,600,200, new Color(255,255,255), new Color(0,0,255,100));
		add(box3);
		for (int i = 0; i < 50; i++) box3.add(new Circle(i, box3));
		box3.add(new BezierLine(box3, 10, 0));
		box3.add(new BezierLine(box3, 10, 0));
		box3.add(new BezierLine(box3, 10, 0));
		box3.add(new BezierLine(box3, 10, 0));
		box3.add(new BezierLine(box3, 10, 0));
		box3.add(new BezierLine(box3, 10, 0));
		
		box4 = new LivingBox(box2, 0,0,70,80, Color.GREEN, new Color(0,0,0,240));
		box2.add(box4);
		for (int i = 0; i < 10; i++) box4.add(new Circle(i, box4));
		
		player1 = new Player(box2);
		box2.add(player1);
		InputHandler.addInputListener(player1);
		
		player2 = new Player(this);
		add(player2);
		InputHandler.addInputListener(player2);
		
		setReady(true);
		super.assemble();
	}
}
