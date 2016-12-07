package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.vfx.line.*;

public class Lines0 extends RootContainer
{

	public Lines0(Dimension _dimension)
	{
		super(_dimension);

		new PolynomLine(this, 4, 30);
	}
}
