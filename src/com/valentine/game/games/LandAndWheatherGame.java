package com.valentine.game.games;

import java.awt.*;
import java.awt.event.*;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;

public class LandAndWheatherGame extends RootContainer implements MouseListener
{
	public LandAndWheatherGame(Dimension _dimension)
	{
		super(_dimension);
		
		Input.addMouseListener(this);
		
		new ColorGrader(this, new HelloWorld(this), true, false);
	}

	public void mouseClicked(MouseEvent _e)
	{
		//mesh.valueAt(_e.getX(), _e.getY());
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
