package com.valentine.game.utils;

import java.awt.*;
import javax.swing.*;

public class Interface {
	private static JFrame jframe;
	private static JPanel jpanel;
	
	private static boolean isFullscreen = true;
	
	private static final Dimension defaultDimensions = new Dimension(1280, 720);
	private static final Color defaultColor = new Color(0, 0, 20);
	
	public static void init() {
		
		jframe = new JFrame();
		jpanel = new JPanel() {
			private static final long serialVersionUID = 124515635493300032L;

			public void paintComponent(Graphics _graphics) {	
				super.paintComponent(_graphics);
				Painter.paint(_graphics);
			}
		};
		
		jframe.setTitle("Dead Space");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		jframe.setBackground(defaultColor);
		jpanel.setBackground(defaultColor);
		
		jframe.add(jpanel);
		
		jframe.addKeyListener(InputHandler.instance());
		
		jpanel.addComponentListener(InputHandler.instance());
		jpanel.addMouseListener(InputHandler.instance());
		jpanel.addMouseMotionListener(InputHandler.instance());
		
		if (isFullscreen) {
			jframe.setUndecorated(true);
			jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jframe);
		}
		else {
			jpanel.setPreferredSize(defaultDimensions);
			jframe.pack();
		}
		
		jframe.setVisible(true);
		
		System.err.println("[Interface]");
	}


	public static Dimension getDimension() {
		return jpanel.getSize();
	}
	
	public static void repaint() {
		jframe.repaint();
	}
}
