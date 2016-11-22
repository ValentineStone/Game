package com.valentine.game.games;

import java.awt.Dimension;

import com.valentine.game.entity.base.RootContainer;
import com.valentine.game.entity.vfx.line.*;

public class PolatingLines extends RootContainer
{

	public PolatingLines(Dimension _dimension)
	{
		super(_dimension);
		
		Line line = new FiniteCubicLineTry2(this, 5, 20);
	}
}
