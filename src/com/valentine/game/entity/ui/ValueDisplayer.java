package com.valentine.game.entity.ui;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;

public class ValueDisplayer extends Rect
{
	private Valuable valuable;
	private Object value;
	
	public ValueDisplayer(Container _container, Valuable _valuable, double _x, double _y)
	{
		super(_container);
		valuable = _valuable;
		setPosition(_x, _y);
	}

	public void paint()
	{
		super.paint();
		
		Screen.drawString
		(
			value.toString(),
			getX() + Screen.getGraphics().getFontMetrics().getHeight(),
			getY() + 2*Screen.getGraphics().getFontMetrics().getHeight()
		);
	}

	public void update()
	{
		value = valuable.getValue();
		
		setSize
		(
			(value.toString().length() + 2) * Screen.getGraphics().getFontMetrics().getHeight(),
			3 * Screen.getGraphics().getFontMetrics().getHeight()
		);
	}

}
