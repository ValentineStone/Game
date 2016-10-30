package com.valentine.game.entity.ui;

import java.awt.*;

import com.valentine.game.core.*;
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
		
		fps = Looper.fps;
		ups = Looper.ups;
		
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
		
		
		double idealUps = Looper.updatesPerSecond;
		
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
		
		
		
		setSize((n + 1) * dy, 4 * dy);
	}

}
