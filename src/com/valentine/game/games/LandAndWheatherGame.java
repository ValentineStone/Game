package com.valentine.game.games;

import java.awt.*;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.landandwheather.*;
import com.valentine.game.entity.ui.*;

public class LandAndWheatherGame extends RootContainer implements MouseListener
{
	private final Mesh3d mesh;
	
	public LandAndWheatherGame(Dimension _dimension)
	{
		super(_dimension);
		
		Input.addMouseListener(this);
		
		new ColorGrader(this, new HelloWorld(this), true, false);
		
		double a = 50;
		double b = 300;
		
		mesh = new Mesh3d(new Dot3d(a,a,b), new Dot3d(a,b,b), new Dot3d(b,a,b), new Dot3d(b,b,b));
	}
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);
		mesh.paint(_screen);
	}

	public void mouseClicked(MouseEvent _e)
	{
		mesh.valueAt(_e.getX(), _e.getY());
	}

	public void mousePressed(MouseEvent _e)
	{}

	public void mouseReleased(MouseEvent _e)
	{}

	public void mouseEntered(MouseEvent _e)
	{}

	public void mouseExited(MouseEvent _e)
	{}

}
