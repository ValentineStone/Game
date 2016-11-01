package com.valentine.game.core;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class WindowOld
{
	private static JFrame jframe;
	private static Canvas canvas;
	
	private static boolean isFullscreen = true;
	
	public static final Dimension DEFAULT_DIMESION = new Dimension(1280, 720);
	
	public static void init()
	{
		
		jframe = new JFrame();
		canvas = new Canvas()
		{
			private static final long serialVersionUID = 124515635493300032L;
			
			BufferStrategy bufferStrategy = null;
			Graphics2D graphics2D;

			@Override
			public void repaint()
			{
				if (bufferStrategy == null)
				{
					System.err.println("[Window]: Creating tripple-buffer strategy.");
					createBufferStrategy(3);
					bufferStrategy = canvas.getBufferStrategy();
				}
				
				graphics2D = (Graphics2D)bufferStrategy.getDrawGraphics();
				
				System.err.println("Static call of Screen is no longer supported");
				//Screen.setGraphics(graphics2D);
				
				Game.instance().paint(null);
				
				bufferStrategy.show();
				
				graphics2D.dispose();
			}
		};
		
		jframe.setTitle("Dead Space");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.add(canvas);
		
		jframe.setFocusable(false);
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		canvas.addKeyListener(Input.instance());
		canvas.addComponentListener(Input.instance());
		canvas.addMouseListener(Input.instance());
		canvas.addMouseMotionListener(Input.instance());
		canvas.addMouseWheelListener(Input.instance());
		
		if (isFullscreen) {
			jframe.setUndecorated(true);
			jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
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