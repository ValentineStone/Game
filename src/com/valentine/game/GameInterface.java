package com.valentine.game;

import java.awt.*;
import javax.swing.*;

public class GameInterface {
	private static JFrame jframe;
	private static JPanel jpanel;
	
	private static boolean isFullscreen = false;
	
	//private static final Dimension defaultDimensions = new Dimension(1280, 720);
	private static final Dimension defaultDimensions = new Dimension(700, 700);
	private static final Color defaultColor = new Color(0, 0, 20);
	
	public static void init() {
		
		jframe = new JFrame();
		jpanel = new JPanel() {
			private static final long serialVersionUID = 124515635493300032L;

			public void paintComponent(Graphics _graphics) {	
				super.paintComponent(_graphics);
				GamePainter.paint(_graphics);
			}
		};
		
		jframe.setTitle("Dead Space");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		jframe.setBackground(defaultColor);
		jpanel.setBackground(defaultColor);
		
		jframe.add(jpanel);
		
		jpanel.addComponentListener(GameInputHandler.instance());
		jpanel.addKeyListener(GameInputHandler.instance());
		jpanel.addMouseListener(GameInputHandler.instance());
		jpanel.addMouseMotionListener(GameInputHandler.instance());
		jpanel.addComponentListener(GameInputHandler.instance());
		
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
