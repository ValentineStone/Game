package com.valentine.game.core;

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
	
	public static final Dimension DEFAULT_DIMESION = new Dimension(1900, 1000);
	public static final Color DEFAULT_COLOR = new Color(0, 0, 20);
	
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
				
				if (bufferStrategy == null)
				{
					System.err.println("[Window]: Creating tripple-buffer strategy.");
					createBufferStrategy(3);
					bufferStrategy = canvas.getBufferStrategy();
				}
				
				graphics2D = (Graphics2D)bufferStrategy.getDrawGraphics();
				
				Screen.setGraphics(graphics2D);
				
				Game.instance().paint();
				
				bufferStrategy.show();
				
				graphics2D.dispose();
			}
		};
		
		jframe.setTitle("Dead Space");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		jframe.setBackground(DEFAULT_COLOR);
		canvas.setBackground(DEFAULT_COLOR);
		
		jframe.add(canvas);
		
		jframe.setFocusable(false);
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		canvas.addKeyListener(Input.instance());
		canvas.addComponentListener(Input.instance());
		canvas.addMouseListener(Input.instance());
		canvas.addMouseMotionListener(Input.instance());
		
		if (isFullscreen) {
			jframe.setUndecorated(true);
			jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jframe);
		}
		else {
			canvas.setPreferredSize(DEFAULT_DIMESION);
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
