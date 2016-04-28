package com.valentine.game.games;

import com.valentine.game.core.*;
import com.valentine.game.entity.vfx.*;

public class PolatingLines extends Game
{
	public void assemble()
	{
		super.assemble();
		
		Line line  = new FiniteStraightLine(this, 4, 20);
	}
}
