package com.valentine.game.games;

import java.util.ArrayList;
import java.util.List;

import com.valentine.game.core.Game;
import com.valentine.game.entity.vfx.BeepingCircle;
import com.valentine.game.entity.vfx.Line;

public class PolatingLines extends Game
{
	List<BeepingCircle> circles = new ArrayList<BeepingCircle>();
	
	public void assemble()
	{
		super.assemble();
		
		Line line  = new Line(this, 4, 20, Line.Style.LAGRANGE);
	}
}
