package com.valentine.game.core;

import com.valentine.game.core.interfaces.*;
import com.valentine.game.core.screen.*;

public class Cassette implements Updatable, Paintable
{
	BasicEntity data;

	public Cassette(BasicEntity _data)
	{
		data = _data;
	}

	public void update()
	{
		data.update();
	}

	public void paint(Screen _screen)
	{
		data.paint(_screen);
	}

}