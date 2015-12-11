package com.valentine.game;

import java.awt.*;
import javax.swing.*;

public class GameInterface {
	JFrame jframe;
	private JPanel jpanel;
	boolean isFullscreen;
	final Dimension defaultDimensions;
	final Color defaultColor;
	
	{
		jframe = new JFrame();
		setMyJPanel(new JPanel(){
			private static final long serialVersionUID = 124515635493300032L;

			public void paintComponent(Graphics _graphics) {	
				super.paintComponent(_graphics);
				Game.myGamePainter.paint(_graphics);
			}
		});
		
		isFullscreen = true;
		defaultDimensions = new Dimension(1280, 720);
		defaultColor = new Color(0, 0, 20);
	}
	
	public GameInterface() {
		jframe.setTitle("Dead Space");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		jframe.setBackground(defaultColor);
		getMyJPanel().setBackground(defaultColor);
		
		jframe.add(getMyJPanel());
		
		jframe.addKeyListener(Game.myGameInputHandler);
		getMyJPanel().addMouseListener(Game.myGameInputHandler);
		getMyJPanel().addMouseMotionListener(Game.myGameInputHandler);
		jframe.addWindowListener(Game.myGameInputHandler);
		getMyJPanel().addComponentListener(Game.myGameInputHandler);
		
		if (isFullscreen) {
			jframe.setUndecorated(true);
			jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jframe);
		}
		else {
			getMyJPanel().setPreferredSize(defaultDimensions);
			jframe.pack();
		}
		
		jframe.setVisible(true);
		
		System.err.println("[Interface]");
	}

	public JPanel getMyJPanel() {
		return jpanel;
	}

	public void setMyJPanel(JPanel _myJPanel) {
		jpanel = _myJPanel;
	}
}
