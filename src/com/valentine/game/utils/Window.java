package com.valentine.game.utils;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window
{
	private static JFrame jframe;
	private static Canvas canvas;
	
	private static boolean isFullscreen = true;
	
	private static final Dimension defaultDimensions = new Dimension(1280, 720);
	private static final Color defaultColor = new Color(0, 0, 20);
	
	public static void init()
	{
		
		jframe = new JFrame();
		canvas = new Canvas()
		{
			private static final long serialVersionUID = 124515635493300032L;
			
			BufferStrategy bufferStrategy;
			Graphics2D graphics2D;

			public void repaint()
			{
				bufferStrategy = canvas.getBufferStrategy();
				
				graphics2D = (Graphics2D)bufferStrategy.getDrawGraphics();
				
				Screen.setGraphics(graphics2D);
				
				Game.instance().paint();
				
				bufferStrategy.show();
				
				graphics2D.dispose();
			}
		};
		
		canvas.createBufferStrategy(30);
		
		jframe.setTitle("Dead Space");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		jframe.setBackground(defaultColor);
		canvas.setBackground(defaultColor);
		
		jframe.add(canvas);
		
		jframe.addKeyListener(Input.instance());
		
		canvas.addComponentListener(Input.instance());
		canvas.addMouseListener(Input.instance());
		canvas.addMouseMotionListener(Input.instance());
		
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
		
		System.err.println("[Window]");
	}


	public static Dimension getDimension()
	{
		return canvas.getSize();
	}
	
	public static void repaint()
	{
		canvas.repaint();
	}
}
