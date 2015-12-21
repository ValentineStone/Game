package com.valentine.game.utils;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Initiolizer implements ComponentListener
{
	public static void init()
	{
		Input.addComponentListener(this);
	}

	public void componentHidden(ComponentEvent _arg0)
	{
	}

	public void componentMoved(ComponentEvent _arg0)
	{
	}

	public void componentResized(ComponentEvent _arg0)
	{
	}

	public void componentShown(ComponentEvent _arg0)
	{
	}

}
