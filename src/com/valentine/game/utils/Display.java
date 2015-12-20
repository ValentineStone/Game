package com.valentine.game.utils;

import java.awt.*;
import javax.swing.*;

public class Display
{
	private static JFrame jframe;
	private static Canvas canvas;
	
	private static boolean isFullscreen = true;
	
	private static final Dimension defaultDimensions = new Dimension(1280, 720);
	private static final Color defaultColor = new Color(0, 0, 20);
	
	public static void init()
	{
		
		jframe = new JFrame();
		canvas = new Canvas();
		
		jframe.setTitle("Dead Space");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		jframe.setBackground(defaultColor);
		canvas.setBackground(defaultColor);
		
		jframe.add(canvas);
		
		jframe.addKeyListener(InputHandler.instance());
		
		canvas.addComponentListener(InputHandler.instance());
		canvas.addMouseListener(InputHandler.instance());
		canvas.addMouseMotionListener(InputHandler.instance());
		
		if (isFullscreen) {
			jframe.setUndecorated(true);
			jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jframe);
		}
		else {
			canvas.setPreferredSize(defaultDimensions);
			jframe.pack();
		}
		
		jframe.setVisible(true);
		
		System.err.println("[Interface]");
	}


	public static Dimension getDimension()
	{
		return canvas.getSize();
	}
	
	public static Canvas getCanvas()
	{
		return canvas;
	}
}
