package com.valentine.game.games;

import java.awt.Dimension;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.fuzzyset.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;

public class FuzzySetsGame extends RootContainer
{
	private FpsUpsCounter fpser;

	private final FuzzySet setX;
	private final FuzzySet setY;
	
	private final FuzzySet setConX;
	private final FuzzySet setConY;
	private final FuzzySet setDilX;
	private final FuzzySet setDilY;
	
	
	private final FuzzySetWindow winX;
	private final FuzzySetWindow winY;
	
	private final FuzzySetWindow winConX;
	private final FuzzySetWindow winConY;
	private final FuzzySetWindow winDilX;
	private final FuzzySetWindow winDilY;
	
	
	public FuzzySetsGame(Dimension _dimension)
	{
		super(_dimension);
		
		
		
		/*
		
		double count = 40;
		
		StringBuilder sbX = new StringBuilder();
		for (int i = 0; i < count; i++)
		{
			sbX.append(i).append(" ").append(Math.random()).append(" ");
		}
		
		StringBuilder sbY = new StringBuilder();
		for (int i = 0; i < count; i++)
		{
			sbY.append(i).append(" ").append(.5).append(" ");
		}
		
		setX = new FuzzySet("1 .1 3 .6 5 .9 7 .8 8 .4");
		winX = new FuzzySetWindow(this, "X", setX, 0*FuzzySetWindow.WIDTH + 1*10, 10);
		
		setConX = new FuzzySet(setX);
		setConX.con();
		winConX = new FuzzySetWindow(this, "CON(X)", setConX, 1*FuzzySetWindow.WIDTH + 2*10, 10);
		
		setDilX = new FuzzySet(setX);
		setDilX.dil();
		winDilX = new FuzzySetWindow(this, "DIL(X)", setDilX, 2*FuzzySetWindow.WIDTH + 3*10, 10);
		
		setY = new FuzzySet("1 .7 2 .5 3 1 4 .4 5 .6 6 .3 8 1");
		winY = new FuzzySetWindow(this, "Y", setY, 3*FuzzySetWindow.WIDTH + 4*10, 10);
		
		setConY = new FuzzySet(setY);
		setConY.con();
		winConY = new FuzzySetWindow(this, "CON(Y)", setConY, 4*FuzzySetWindow.WIDTH + 5*10, 10);
		
		setDilY = new FuzzySet(setY);
		setDilY.dil();
		winDilY = new FuzzySetWindow(this, "DIL(Y)", setDilY, 5*FuzzySetWindow.WIDTH + 6*10, 10);
		
		fpser = new FpsUpsCounter(this, 10, winX.getHeight() + 20);
		new DragHandler(this, fpser);
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		double count = 40;
		
		StringBuilder sbX = new StringBuilder();
		for (int i = 0; i < count; i++)
		{
			sbX.append(i).append(" ").append(Math.random()).append(" ");
		}
		
		StringBuilder sbY = new StringBuilder();
		for (int i = 0; i < count; i++)
		{
			sbY.append(i).append(" ").append(i / count).append(" ");
		}
		
		setX = new FuzzySet("1 .1 3 .6 5 .9 7 .8 8 .4");
		winX = new FuzzySetWindow(this, "X", setX, 10, 10);
		
		setY = new FuzzySet("1 .7 2 .5 3 1 4 .4 5 .6 6 .3 8 1");
		winY = new FuzzySetWindow(this, "Y", setY, FuzzySetWindow.WIDTH + 20, 10);
		
		
		
		
		double POSY = 10;
		
		
		setConX = new FuzzySet(setX);
		setConX.con();
		winConX = new FuzzySetWindow(this, "CON(X)", setConX, 2*FuzzySetWindow.WIDTH + 30, POSY);
		winConX.toggleShown();
		POSY += 40;
		
		setConY = new FuzzySet(setY);
		setConY.con();
		winConY = new FuzzySetWindow(this, "CON(Y)", setConY, 2*FuzzySetWindow.WIDTH + 30, POSY);
		winConY.toggleShown();
		POSY += 40;
		
		setDilX = new FuzzySet(setX);
		setDilX.dil();
		winDilX = new FuzzySetWindow(this, "DIL(X)", setDilX, 2*FuzzySetWindow.WIDTH + 30, POSY);
		winDilX.toggleShown();
		POSY += 40;
		
		setDilY = new FuzzySet(setY);
		setDilY.dil();
		winDilY = new FuzzySetWindow(this, "DIL(Y)", setDilY, 2*FuzzySetWindow.WIDTH + 30, POSY);
		winDilY.toggleShown();
		POSY += 40;
		
		

		new FuzzySetWindow
		(
			this,
			"!X",
			FuzzySet.operate(new FuzzySet.Compliment(), setX, null),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		new FuzzySetWindow
		(
			this,
			"!Y",
			FuzzySet.operate(new FuzzySet.Compliment(), setY, null),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		
		
		new FuzzySetWindow
		(
			this,
			"X UG Y",
			FuzzySet.operate(new FuzzySet.Union(FuzzySet.Union.Type.GENERAL), setX, setY),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		new FuzzySetWindow
		(
			this,
			"X IG Y",
			FuzzySet.operate(new FuzzySet.Intersection(FuzzySet.Intersection.Type.GENERAL), setX, setY),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		new FuzzySetWindow
		(
			this,
			"![X UG Y]",
			FuzzySet.operate
			(
				new FuzzySet.Compliment(),
				FuzzySet.operate(new FuzzySet.Union(FuzzySet.Union.Type.GENERAL), setX, setY),
				null
			),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		new FuzzySetWindow
		(
			this,
			"[X UG !Y] IG [X UD Y]",
			FuzzySet.operate
			(
				new FuzzySet.Intersection(FuzzySet.Intersection.Type.GENERAL),
				FuzzySet.operate
				(
					new FuzzySet.Union(FuzzySet.Union.Type.GENERAL),
					setX,
					FuzzySet.operate(new FuzzySet.Compliment(), setY, null)
				),
				FuzzySet.operate
				(
					new FuzzySet.Union(FuzzySet.Union.Type.DRASTICAL),
					setX,
					setY
				)
			),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		new FuzzySetWindow
		(
			this,
			"[X UB Y] IA [X UA Y]",
			FuzzySet.operate
			(
				new FuzzySet.Intersection(FuzzySet.Intersection.Type.ALGEBRAIC),
				FuzzySet.operate
				(
					new FuzzySet.Union(FuzzySet.Union.Type.BOUNDARY),
					setX,
					setY
				),
				FuzzySet.operate
				(
					new FuzzySet.Union(FuzzySet.Union.Type.ALGEBRAIC),
					setX,
					setY
				)
			),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		new FuzzySetWindow
		(
			this,
			"X SD Y",
			FuzzySet.operate(new FuzzySet.Summ(FuzzySet.Summ.Type.DISJUNCTIVE), setX, setY),
			2*FuzzySetWindow.WIDTH + 30,
			POSY
		).toggleShown();
		POSY += 40;
		
		
		
		
		fpser = new FpsUpsCounter(this, 10, winX.getHeight() + 20);
		new DragHandler(this, fpser);
		
		
	}

}
