package com.valentine.game.gameworld.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.valentine.game.GamePainter;
import com.valentine.game.gameworld.OldEntity;

public class Player implements OldEntity {
	Image image;
	public double x;
	public double y;
	public double up_dx;
	public double down_dx;
	public double up_dy;
	public double down_dy;
	public double speed = 4;
	
	{
		image = new ImageIcon("fire.gif").getImage();
			
	}

	public void update() {
		x += (down_dx - up_dx);
		y += (down_dy - up_dy);
	}

	public void paint(Graphics _graphics) {
		_graphics.drawImage(image, (int)(x + (down_dx - up_dx) * GamePainter.getInterpolation() + .5), (int)(y + (down_dy - up_dy) * GamePainter.getInterpolation() + .5), null);
	}
	
	public void takeAction(KeyEvent _arg0) {
		switch (_arg0.getID()) {
			case KeyEvent.KEY_PRESSED: {
				switch (_arg0.getKeyCode()) {
					case KeyEvent.VK_DOWN: down_dy = speed; break;
					case KeyEvent.VK_UP: up_dy = speed; break;
					case KeyEvent.VK_LEFT: up_dx = speed; break;
					case KeyEvent.VK_RIGHT: down_dx = speed; break;
				}
			} break;
			case KeyEvent.KEY_RELEASED: {
				switch (_arg0.getKeyCode()) {
					case KeyEvent.VK_DOWN: down_dy = 0; break;
					case KeyEvent.VK_UP: up_dy = 0; break;
					case KeyEvent.VK_LEFT: up_dx = 0; break;
					case KeyEvent.VK_RIGHT: down_dx = 0; break;
				}
			} break;
		}
	}

}
