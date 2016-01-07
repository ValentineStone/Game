package com.valentine.game.entity.creatures;

import java.awt.Font;
import java.awt.Color;

import com.valentine.game.core.Screen;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.ColorExt;

public class GameOver extends Entity
{
	Font font = new Font(Screen.getFont().getFontName(), Font.ITALIC, 100);
	
	public GameOver(Container _container)
	{
		super(_container);
		
		setFillColor(ColorExt.makeTransparent(Color.RED, 20));
		setDrawColor(Color.WHITE);
	}

	public void paint()
	{
		Screen.setColor(getFillColor());
		Screen.fillRect(0, 0, getContainer().getWidth(), getContainer().getHeight());
		
		Screen.setColor(getDrawColor());
		Screen.setFont(font);
		Screen.drawString("GAME OVER", getContainer().getCenterX() - 450, getContainer().getCenterY() + 40);
		Screen.resetFont();
		Screen.drawString("Press ESC to retry.", getContainer().getCenterX() - 100, getContainer().getCenterY() + 80);
	}

	public void update()
	{
		getContainer().setUpdatable(false);
	}

}
