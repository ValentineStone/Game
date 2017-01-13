package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;
import com.valentine.game.utils.math.geom.*;

public class ApproximationTest extends RootContainer
{
	private final EntityBasicAI[] entities = new EntityBasicAI[30];
	private final Dot2d[] dots = new Dot2d[entities.length];
	
	private final GFunctionBox[] gfboxs = new GFunctionBox[5];
	
	public ApproximationTest(Dimension _dimension)
	{
		super(_dimension);
		
		for (int i = 0; i < gfboxs.length; i++)
		{
			gfboxs[i] = new GFunctionBox(this, 0, 0, getWidth(), getHeight());
			__prepgfbox(gfboxs[i]);
		}
		
		//entities[0] = new PlayerSpacecraft(this);
		
		for (int i = 0; i < entities.length; i++)
			entities[i] = new Rotor(this);
	}
	
	public void update()
	{
		super.update();
		
		for (int i = 0; i < entities.length; i++)
			dots[i] = entities[i].getCenterDot();
		
		for (int i = 0; i < gfboxs.length; i++)
			gfboxs[i].setFunction(Approximation.approximate(dots, i+1));
	}
	
	private void __prepgfbox(GFunctionBox _gfbox)
	{
		_gfbox.setXmin(0);
		_gfbox.setXmax(getWidth());
		_gfbox.setXstep(2);
		_gfbox.setYzeroline(0);
		_gfbox.setYmultiplyer(-1);
		_gfbox.setFillColor(ColorExt.TRANSPARENT);
		_gfbox.setDrawColor(ColorExt.randomColor(30, 255));
	}
}
