package com.valentine.game.games;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.valentine.game.core.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.geom.*;
import com.valentine.game.utils.painters.*;

public class LandAndWheatherGame extends RootContainer implements MouseMotionListener, MouseListener
{
	Mesh3d mesh = new Mesh3d();
	
	List<Tri3d> tris;
	
	Dot3d scanDot = new Dot3d(0, 0, 0.34);
	boolean outOfScope = true;
	
	List<Dot3d> additions = new ArrayList<>();
	
	public LandAndWheatherGame(Dimension _dimension)
	{
		super(_dimension);
		
		Input.addMouseMotionListener(this);
		Input.addMouseListener(this);
		
		new GButton(this, "Add dots", 10, 10, 120, 30)
			.addListener(additionsDialogue);
	}
	
	
	
	
	
	public void paint(Screen _screen)
	{
		super.paint(_screen);

		_screen.setColor(ColorExt.ORANGE);
		
		if (tris != null)
			for (Tri2d tri : tris)
				GeometryPainter.paint(_screen, tri);
		
		_screen.setColor(Color.WHITE);
		
		GeometryPainter.paint(_screen, mesh);
		if (outOfScope)
			_screen.drawString("Out of scope", scanDot.x, scanDot.y);
		else
			GeometryPainter.paint(_screen, scanDot);
		
	}
	
	public void update()
	{
		super.update();
		
		boolean added = additions.size() > 0;
		
		for (Dot3d dot : additions)
			mesh.add(dot);
		
		additions.clear();
		
		if (added && mesh.size() >= 3)
			tris = Triangulation.triangulate(mesh.iterable3d(), true);
		
		outOfScope = true;
		if (tris != null)
		{
			for (Tri3d tri : tris)
				if (tri.contains(scanDot))
				{
					scanDot.z = PlaneCommon3d.toPlane(tri).evalZ(scanDot.x, scanDot.y);
					outOfScope = false;
					break;
				}
		}
	}
	
	
	
	
	public Runnable additionsDialogue = () ->
	{
		addDots
		(
			JOptionPane.showInputDialog
			(
				"Enter dots to add:",
				"x1 y1 z1 x2 y2 z2 ..."
			)
		);
	};
	
	public void addDots(String _dots)
	{
		if (_dots == null)
			return;
		
		String[] coords = _dots.split(" ");
		
		for (int i = 0; i+2 < _dots.length(); i += 3)
			try
			{
				additions.add
				(
					new Dot3d
					(
						Double.valueOf(coords[i+0]),
						Double.valueOf(coords[i+1]),
						Double.valueOf(coords[i+2])
					)
				);
			}
			catch (Exception _e)
			{
				return;
			}
	}
	
	
	
	
	


	public void mouseDragged(MouseEvent _e)
	{
		mouseMoved(_e);
	}

	public void mouseMoved(MouseEvent _e)
	{
		scanDot.x = _e.getX() - getTrueX();
		scanDot.y = _e.getY() - getTrueY();
	}


	public void mousePressed(MouseEvent _e)
	{

		if (SwingUtilities.isRightMouseButton(_e))
		{
			if (_e.isShiftDown())
				additionsDialogue.run();
			else
				try
				{
					additions.add
					(
						new Dot3d
						(
							_e.getX() - getTrueX(),
							_e.getY() - getTrueY(),
							Double.valueOf(JOptionPane.showInputDialog("Enter z for this position:","z"))
						)
					);
				}
				catch (Exception _exc)
				{
					return;
				}
		}
	}
	public void mouseClicked(MouseEvent _e)
	{}
	public void mouseReleased(MouseEvent _e)
	{}
	public void mouseEntered(MouseEvent _e)
	{}
	public void mouseExited(MouseEvent _e)
	{}
	
	
	
	

	public boolean kill(Entity _killer)
	{
		Input.removeMouseMotionListener(this);
		Input.removeMouseListener(this);
		return super.kill(_killer);
	}
}
