package com.valentine.game.core;

import java.awt.event.*;
import java.util.*;

import com.valentine.game.core.interfaces.*;

public class Input implements Updatable, MouseListener, MouseMotionListener, KeyListener, ComponentListener, MouseWheelListener
{

	private static Input input = new Input();

	private static ArrayList<MouseListener> mouseListeners = new ArrayList<>();

	private static ArrayList<MouseMotionListener> mouseMotionListeners = new ArrayList<>();

	private static ArrayList<KeyListener> keyListeners = new ArrayList<>();

	private static ArrayList<ComponentListener> componentListeners = new ArrayList<>();

	private static ArrayList<MouseWheelListener> mouseWheelListeners = new ArrayList<>();

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

	public void keyPressed(KeyEvent _keyEvent)
	{
		for (int i = 0; i < keyListeners.size(); i++)
		{
			keyListeners.get(i).keyPressed(_keyEvent);
		}
	}

	public void keyReleased(KeyEvent _keyEvent)
	{
		for (int i = 0; i < keyListeners.size(); i++)
		{
			keyListeners.get(i).keyReleased(_keyEvent);
		}
	}

	public void keyTyped(KeyEvent _keyEvent)
	{
		for (int i = 0; i < keyListeners.size(); i++)
		{
			keyListeners.get(i).keyTyped(_keyEvent);
		}
	}

	public void mouseClicked(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
		{
			mouseListeners.get(i).mouseClicked(_mouseEvent);
		}
	}

	public void mouseEntered(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
		{
			mouseListeners.get(i).mouseEntered(_mouseEvent);
		}
	}

	public void mouseExited(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
		{
			mouseListeners.get(i).mouseExited(_mouseEvent);
		}
	}

	public void mousePressed(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
		{
			mouseListeners.get(i).mousePressed(_mouseEvent);
		}
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseListeners.size(); i++)
		{
			mouseListeners.get(i).mouseReleased(_mouseEvent);
		}
	}

	public void mouseDragged(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseMotionListeners.size(); i++)
		{
			mouseMotionListeners.get(i).mouseDragged(_mouseEvent);
		}
	}

	public void mouseMoved(MouseEvent _mouseEvent)
	{
		for (int i = 0; i < mouseMotionListeners.size(); i++)
		{
			mouseMotionListeners.get(i).mouseMoved(_mouseEvent);
		}
	}

	public void componentHidden(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
		{
			componentListeners.get(i).componentHidden(_componentEvent);
		}
	}

	public void componentMoved(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
		{
			componentListeners.get(i).componentMoved(_componentEvent);
		}
	}

	public void componentResized(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
		{
			componentListeners.get(i).componentResized(_componentEvent);
		}
	}

	public void componentShown(ComponentEvent _componentEvent)
	{
		for (int i = 0; i < componentListeners.size(); i++)
		{
			componentListeners.get(i).componentShown(_componentEvent);
		}
	}

	public void mouseWheelMoved(MouseWheelEvent _mouseWheelEvent)
	{
		for (MouseWheelListener mouseWheelListener : mouseWheelListeners)
		{
			mouseWheelListener.mouseWheelMoved(_mouseWheelEvent);
		}
	}

	public void update()
	{
		// placeholder
	}
}
