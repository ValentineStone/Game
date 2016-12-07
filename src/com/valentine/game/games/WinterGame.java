package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.ambient.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.entity.winter.*;
import com.valentine.game.utils.*;

public class WinterGame extends RootContainer
{
	private final SnowHeapManager snowMgr1;
	private final SnowHeapManager snowMgr2;
	
	private final FpsUpsCounter fpser;
	
	private Color frameColor = new Color(170,170,75);
	
	private double boardW;
	private double boardH;
	
	private double board1X;
	private double board2X;
	private double board3X;
	
	private double board1Y;
	private double board2Y;
	private double board3Y;
	
	public WinterGame(Dimension _dimension)
	{
		super(_dimension);
		
		snowMgr1 = new SnowHeapManager((int) getWidth());
		snowMgr2 = new SnowHeapManager((int) getWidth());
		
		boardW = getWidth() / 31;
		boardH = getHeight() / 21;
		
		board1X = 0;
		board2X = boardW * 15;
		board3X = boardW * 30;
		
		board1Y = 0;
		board2Y = boardH * 10;
		board3Y = boardH * 20;
		
		snowMgr1.rase(board2Y + boardH);
		snowMgr2.rase(boardH);
		
		PhasedMoon phasedMoon = new PhasedMoon(this, 50);
		phasedMoon.setPosition(boardW + 10, boardH + 10);
		
		for (int i = 0; i < 100; i++)
			new BlinkingStar(this);
		
		fpser = new FpsUpsCounter(this, board2X + boardW + 5, boardH + 5);
		fpser.setPosition(-1000, -1000);
	}
	
	
	public void update()
	{
		super.update();
		
		if (fpser.getFps() >= 60)
			new SnowFlake(this, MathExt.randomIf() ? snowMgr1 : snowMgr2, MathExt.random(getWidth()), 0);
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		
		paintFrame(_screen);
	}
	
	public void paintFrame(Screen _screen)
	{
		_screen.setColor(frameColor);
		
		_screen.fillRect(board1X, board1Y, getWidth(), boardH);
		_screen.fillRect(board1X, board2Y, getWidth(), boardH);
		_screen.fillRect(board1X, board3Y, getWidth(), boardH);
		
		_screen.fillRect(board1X, board1Y, boardW, getHeight());
		_screen.fillRect(board2X, board1Y, boardW, getHeight());
		_screen.fillRect(board3X, board1Y, boardW, getHeight());
	}

}
