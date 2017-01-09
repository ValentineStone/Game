package com.valentine.game.games;

import java.awt.*;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;

public class FuzzyNumbersGame extends RootContainer
{
	public FuzzyNumbersGame(Dimension _dimension)
	{
		super(_dimension);
		
		//new FpsUpsCounter(this, 10, 10);
		
		GFunctionBox[] fncs = new GFunctionBox[33];
		
		for (int i = 0; i < fncs.length; i++)
		{
			double[] coefficients = new double[(int) MathExt.random(3, 10)];
			
			//coefficients[0] = MathExt.random(-10, 10);
			
			for (int j = 0; j < coefficients.length; j++)
				coefficients[j] = MathExt.random(-j-1, j+1);
			fncs[i] = new GFunctionBox(this, 0, 0, getWidth(), getHeight(), new Polinom(coefficients), -1, 1, 1, getHeight() / 2, getHeight() / 2);
			fncs[i].setFillColor(ColorExt.TRANSPARENT);
			fncs[i].setDrawFunctionOnly(true);
			new ColorGrader(this, fncs[i], true, false);
		}
		
		new FpsUpsCounter(this, 10, 10).setFillColor(new Color(0,0,0,200));;
	}

}
