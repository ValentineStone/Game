package com.valentine.game.entity.creatures;

import java.awt.Color;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;

public class FpsUpsCounter extends Entity
{
	private String fpsText;
	private String upsText;
	
	private Color fpsColor;
	private Color upsColor;
	
	private int n;
	
	private double dy = 0;
	
	public FpsUpsCounter(Container _container, double _x, double _y)
	{
		super(_container);
		setPosition(_x, _y);
		setDrawColor(Color.WHITE);
	}

	public void paint()
	{
		if (dy == 0) dy = Screen.getGraphics().getFontMetrics().getHeight() + 1;
		
		Screen.setColor(getDrawColor());
		Screen.drawRect(getX(), getY(), (n + 1) * dy, 4 * dy);
		
		Screen.setColor(fpsColor);
		Screen.drawString(fpsText, getX(), getY() + 2 * dy);
		Screen.setColor(upsColor);
		Screen.drawString(upsText, getX(), getY() + 3 * dy);
	}

	public void update()
	{
		fpsText = " FPS:" + Looper.fps;
		upsText = " UPS:" + Looper.ups;
		
		n = fpsText.length() > upsText.length() ? fpsText.length() : upsText.length();
		
		
		
		if (Looper.fps >= 60)
		{
			fpsColor = Color.GREEN;
		}
		else if (Looper.fps >= 30)
		{
			fpsColor = Color.YELLOW;
		}
		else if (Looper.fps >= 15)
		{
			fpsColor = Color.ORANGE;
		}
		else
		{
			fpsColor = Color.RED;
		}
		
		
		
		if (Looper.ups >= 25)
		{
			upsColor = Color.GREEN;
		}
		else if (Looper.ups >= 30)
		{
			upsColor = Color.YELLOW;
		}
		else if (Looper.ups >= 15)
		{
			upsColor = Color.ORANGE;
		}
		else
		{
			upsColor = Color.RED;
		}
	}

}
