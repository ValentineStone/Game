package com.valentine.game.games;

import com.valentine.game.core.*;
import com.valentine.game.entity.vfx.*;
import com.valentine.game.entity.vfx.line.*;

public class PolatingLines extends Game
{
	public void assemble()
	{
		super.assemble();
		
		Line line  = new FiniteQuadraticLine(this, 7, 20);
	}
}
