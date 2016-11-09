package com.valentine.game.core;

import java.awt.event.*;
import java.util.ArrayList;

public class Input implements Updatable, MouseListener, MouseMotionListener, KeyListener, ComponentListener, MouseWheelListener
{
	
	
	private static Input input = new Input();
	
	
	private static ArrayList<MouseListener> mouseListeners = new ArrayList<MouseListener>();
	
	private static ArrayList<MouseMotionListener> mouseMotionListeners = new ArrayList<MouseMotionListener>();
	
	private static ArrayList<KeyListener> keyListeners = new ArrayList<KeyListener>();
	
	private static ArrayList<ComponentListener> componentListeners = new ArrayList<ComponentListener>();
	
	private static ArrayList<MouseWheelListener> mouseWheelListeners = new ArrayList<MouseWheelListener>();
	
	
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
	
	public static void addMouseWheelListener(MouseWheelListener _mouseWheelListener)
	{
		mouseWheelListeners.add(_mouseWheelListener);
	}
	
	
	
	
	public static void removeComponentListener(ComponentListener _componentListener)
	{
		componentListeners.remove(_componentListener);
	}
	
	public static void removeKeyListener(KeyListener _keyListener)
	{
		keyListeners.remove(_keyListener);
	}
	
	public static void removeMouseMotionListener(MouseMotionListener _mouseMotionListener)
	{
		componentListeners.remove(_mouseMotionListener);
	}
	
	public static void removeMouseListener(MouseListener _mouseListener)
	{
		keyListeners.remove(_mouseListener);
	}
	
	public static void removeMouseWheelListener(MouseWheelListener _mouseWheelListener)
	{
		mouseWheelListeners.remove(_mouseWheelListener);
	}
	
	
	
	

	@Override
	public void keyPressed(KeyEvent _keyEvent)
	{
		for (int i = 0; i < keyListeners.size(); i++)
			keyListeners.get(i).keyPressed(_keyEvent);
	}

	@Override
	public void keyReleased(KeyEvent _keyEvent)
	{
		for (int i = 0; i < keyListeners.size(); i++)
			keyListeners.get(i).keyReleased(_keyEvent);
	}

	@Override
	public void keyTyped(KeyEvent _keyEvent)
	{
		for (int i = 0; i < keyListeners.size(); i++)
			keyListeners.get(i).keyTyped(_keyEvent);
	}
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
		{
			mouseListeners.get(i).mouseClicked(_mouseEvent);
		}
	}

	@Override
	public void mouseEntered(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
			mouseListeners.get(i).mouseEntered(_mouseEvent);
	}
	
	@Override
	public void mouseExited(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
			mouseListeners.get(i).mouseExited(_mouseEvent);
	}

	@Override
	public void mousePressed(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
			mouseListeners.get(i).mousePressed(_mouseEvent);
	}

	@Override
	public void mouseReleased(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
			mouseListeners.get(i).mouseReleased(_mouseEvent);
	}
	
	
	
	
	
	@Override
	public void mouseDragged(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseMotionListeners.size(); i++)
			mouseMotionListeners.get(i).mouseDragged(_mouseEvent);
	}

	@Override
	public void mouseMoved(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseMotionListeners.size(); i++)
			mouseMotionListeners.get(i).mouseMoved(_mouseEvent);
	}

	
	
	
	
	
	@Override
	public void componentHidden(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
			componentListeners.get(i).componentHidden(_componentEvent);
	}

	@Override
	public void componentMoved(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
			componentListeners.get(i).componentMoved(_componentEvent);
	}

	@Override
	public void componentResized(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
			componentListeners.get(i).componentResized(_componentEvent);
	}

	@Override
	public void componentShown(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
			componentListeners.get(i).componentShown(_componentEvent);
	}

	
	
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent _mouseWheelEvent)
	{
		for (MouseWheelListener mouseWheelListener : mouseWheelListeners)
		{
			mouseWheelListener.mouseWheelMoved(_mouseWheelEvent);
		}
	}

	public void update()
	{
		//placeholder
	}
}
