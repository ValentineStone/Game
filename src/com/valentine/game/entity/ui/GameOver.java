package com.valentine.game.entity.ui;

import java.awt.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

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

	public void update()
	{
		getContainer().setUpdatable(false);
	}

}
