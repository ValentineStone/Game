package com.valentine.game.entity.ui;

import java.awt.*;

import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.utils.*;

public class FpsUpsCounter extends EntityBasicAI
{
	private String fpsText;
	private String upsText;

	private Color fpsColor;
	private Color upsColor;

	private int n;

	private double dy = 0;

	private int fps;
	private int ups;
	private int _fps;
	private int _ups;
	private long second = 0;

	public FpsUpsCounter(Container _container, double _x, double _y)
	{
		super(_container);
		setPosition(_x, _y);
		setDrawColor(Color.WHITE);
	}

	public void paint(Screen _screen)
	{
		_fps++;

		if (dy == 0)
		{
			dy = _screen.getGraphics().getFontMetrics().getHeight() + 1;
		}

		_screen.setColor(getDrawColor());
		_screen.drawRect(getX(), getY(), getWidth(), getHeight());

		_screen.setColor(fpsColor);
		_screen.drawString(fpsText, getX() + dy, getY() + 2 * dy);
		_screen.setColor(upsColor);
		_screen.drawString(upsText, getX() + dy, getY() + 3 * dy);

		_screen.setColor(getDrawColor());
		_screen.drawString("FPS:", getX() + dy, getY() + 2 * dy);
		_screen.drawString("UPS:", getX() + dy, getY() + 3 * dy);
	}

	public void update()
	{
		if (second == 0)
		{
			second = System.currentTimeMillis();
		}

		_ups++;

		if (System.currentTimeMillis() - second > 1000)
		{
			second += 1000;
			fps = _fps;
			_fps = 0;
			ups = _ups;
			_ups = 0;
		}

		fpsText = "FPS:" + fps;
		upsText = "UPS:" + ups;

		n = fpsText.length() > upsText.length() ? fpsText.length() : upsText.length();

		if (fps >= 60)
		{
			fpsColor = ColorExt.GREEN;
		}
		else if (fps >= 30)
		{
			fpsColor = Color.YELLOW;
		}
		else if (fps >= 15)
		{
			fpsColor = ColorExt.ORANGE;
		}
		else
		{
			fpsColor = Color.RED;
		}

		double idealUps = 25;

		if (ups > idealUps + 2)
		{
			upsColor = Color.RED;
		}
		else if (ups == idealUps + 2)
		{
			upsColor = ColorExt.ORANGE;
		}
		else if (ups == idealUps + 1)
		{
			upsColor = Color.YELLOW;
		}
		else if (ups == idealUps)
		{
			upsColor = ColorExt.GREEN;
		}
		else if (ups == idealUps - 1)
		{
			upsColor = Color.YELLOW;
		}
		else if (ups == idealUps - 2)
		{
			upsColor = ColorExt.ORANGE;
		}
		else
		{
			upsColor = Color.RED;
		}

		setSize((n + 2) * dy, 4 * dy);
	}

	public int getFps()
	{
		return fps;
	}

	public int getUps()
	{
		return ups;
	}

}
