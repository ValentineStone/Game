package com.valentine.game.entity.ui;

import java.awt.Font;
import java.awt.Color;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.ColorExt;

public class GameOver extends Entity
{
	Font font;// = new Font(Screen.getFont().getFontName(), Font.ITALIC, 100);
	
	public GameOver(Container _container)
	{
		super(_container);

		System.err.println("Static screen call is no longer supported");
		
		setFillColor(ColorExt.makeTransparent(Color.RED, 20));
		setDrawColor(Color.WHITE);
	}

	@Override
	public void paint(Screen _screen)
	{
		_screen.setColor(getFillColor());
		_screen.fillRect(0, 0, getContainer().getWidth(), getContainer().getHeight());
		
		_screen.setColor(getDrawColor());
		_screen.setFont(font);
		_screen.drawString("GAME OVER", getContainer().getCenterX() - 450, getContainer().getCenterY() + 40);
		_screen.resetFont();
		_screen.drawString("Press ESC to retry.", getContainer().getCenterX() - 100, getContainer().getCenterY() + 80);
	}

	@Override
	public void update()
	{
		getContainer().setUpdatable(false);
	}

}
