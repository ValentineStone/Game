package com.valentine.game.utils;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

public class Input implements MouseListener, MouseMotionListener, KeyListener, ComponentListener
{
	
	
	private static Input input;
	
	
	private static Set<MouseListener> mouseListeners = new HashSet<MouseListener>();
	
	private static Set<MouseMotionListener> mouseMotionListeners = new HashSet<MouseMotionListener>();
	
	private static Set<KeyListener> keyListeners = new HashSet<KeyListener>();
	
	private static Set<ComponentListener> componentListeners = new HashSet<ComponentListener>();
	
	
	
	
	
	public static void init()
	{
		input = new Input();
		
		System.err.println("[Input]");
	}
	
	public static Input instance()
	{
		return input;
	}
	
	
	
	
	
	public static void addMouseListener(MouseListener _mouseListener)
	{
		mouseListeners.add(_mouseListener);
	}
	
	public static void addMouseMotionListener(MouseMotionListener _mouseMotionListener)
	{
		mouseMotionListeners.add(_mouseMotionListener);
	}
	
	public static void addKeyListener(KeyListener _keyListener)
	{
		keyListeners.add(_keyListener);
	}
	
	public static void addComponentListener(ComponentListener _componentListener)
	{
		componentListeners.add(_componentListener);
	}
	
	public static void removeComponentListener(ComponentListener _componentListener)
	{
		componentListeners.remove(_componentListener);
	}
	
	
	
	
	

	public void keyPressed(KeyEvent _keyEvent)
	{
		for (KeyListener keyListener : keyListeners)
			keyListener.keyPressed(_keyEvent);
	}

	public void keyReleased(KeyEvent _keyEvent)
	{
		for (KeyListener keyListener : keyListeners)
			keyListener.keyReleased(_keyEvent);
	}

	public void keyTyped(KeyEvent _keyEvent)
	{
		for (KeyListener keyListener : keyListeners)
			keyListener.keyTyped(_keyEvent);
	}
	
	
	
	
	

	public void mouseClicked(MouseEvent _mouseEvent)
	{
		for (MouseListener mouseListener : mouseListeners)
			mouseListener.mouseClicked(_mouseEvent);
	}

	public void mouseEntered(MouseEvent _mouseEvent)
	{
		for (MouseListener mouseListener : mouseListeners)
			mouseListener.mouseEntered(_mouseEvent);
	}
	
	public void mouseExited(MouseEvent _mouseEvent)
	{
		for (MouseListener mouseListener : mouseListeners)
			mouseListener.mouseExited(_mouseEvent);
	}

	public void mousePressed(MouseEvent _mouseEvent)
	{
		for (MouseListener mouseListener : mouseListeners)
			mouseListener.mousePressed(_mouseEvent);
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		for (MouseListener mouseListener : mouseListeners)
			mouseListener.mouseReleased(_mouseEvent);
	}
	
	
	
	
	
	public void mouseDragged(MouseEvent _mouseEvent)
	{
		for (MouseMotionListener mouseMotionListener : mouseMotionListeners)
			mouseMotionListener.mouseDragged(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent)
	{
		for (MouseMotionListener mouseMotionListener : mouseMotionListeners)
			mouseMotionListener.mouseMoved(_mouseEvent);
	}

	
	
	
	
	
	public void componentHidden(ComponentEvent _componentEvent)
	{
		for (ComponentListener componentListener : componentListeners)
			componentListener.componentHidden(_componentEvent);
	}

	public void componentMoved(ComponentEvent _componentEvent)
	{
		for (ComponentListener componentListener : componentListeners)
			componentListener.componentMoved(_componentEvent);
	}

	public void componentResized(ComponentEvent _componentEvent)
	{
		for (ComponentListener componentListener : componentListeners)
			componentListener.componentResized(_componentEvent);
	}

	public void componentShown(ComponentEvent _componentEvent)
	{
		for (ComponentListener componentListener : componentListeners)
			componentListener.componentShown(_componentEvent);
	}
}
