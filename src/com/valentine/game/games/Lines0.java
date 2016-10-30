package com.valentine.game.games;

import com.valentine.game.core.*;
import com.valentine.game.entity.vfx.line.*;

public class Lines0 extends Yame
{
	public void assemble()
	{
		super.assemble();
		
		new PolynomLine(this, 4, 30);
	}
}
