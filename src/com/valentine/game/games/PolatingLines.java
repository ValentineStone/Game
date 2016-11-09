package com.valentine.game.games;

import com.valentine.game.entity.vfx.line.*;

public class PolatingLines extends GameContainer
{

	public void assemble()
	{
		super.assemble();

		Line line = new FiniteCubicLineTry2(this, 5, 20);
	}
}
