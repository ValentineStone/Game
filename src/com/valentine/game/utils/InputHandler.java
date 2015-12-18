package com.valentine.game.utils;

import java.awt.event.*;
import java.util.ArrayList;

import com.valentine.game.listener.InputListener;

public class InputHandler implements InputListener, ComponentListener
{
	
	private static InputHandler inputHandler;
	
	private static ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	private static ArrayList<ComponentListener> componentListeners = new ArrayList<ComponentListener>();
	
	public static void init()
	{
		inputHandler = new InputHandler();
		
		System.err.println("[InputHandler]");
	}
	
	public static InputHandler instance()
	{
		return inputHandler;
	}
	
	
	public static void addInputListener(InputListener _inputListener)
	{
		inputListeners.add(_inputListener);
	}
	
	public static void addComponentListener(ComponentListener _componentListener)
	{
		componentListeners.add(_componentListener);
	}
	
	

	public void keyPressed(KeyEvent _keyEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.keyPressed(_keyEvent);
	}

	public void keyReleased(KeyEvent _keyEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.keyReleased(_keyEvent);
	}

	public void keyTyped(KeyEvent _keyEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.keyTyped(_keyEvent);
	}

	public void mouseClicked(MouseEvent _mouseEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.mouseClicked(_mouseEvent);
	}

	public void mouseEntered(MouseEvent _mouseEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.mouseEntered(_mouseEvent);
	}
	
	public void mouseExited(MouseEvent _mouseEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.mouseExited(_mouseEvent);
	}

	public void mousePressed(MouseEvent _mouseEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.mousePressed(_mouseEvent);
	}

	public void mouseReleased(MouseEvent _mouseEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.mouseReleased(_mouseEvent);
	}
	
	public void mouseDragged(MouseEvent _mouseEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.mouseDragged(_mouseEvent);
	}

	public void mouseMoved(MouseEvent _mouseEvent)
	{
		for (InputListener inputListener : inputListeners)
			inputListener.mouseMoved(_mouseEvent);
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
