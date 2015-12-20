package com.valentine.game.entity;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import com.valentine.game.listener.InputListener;
import com.valentine.game.utils.*;

public class Player implements Entity, InputListener {
	Image image;
	public double x;
	public double y;
	public double width = 64;
	public double height = 64;
	public double up_dx;
	public double down_dx;
	public double up_dy;
	public double down_dy;
	public double speed = 10;
	Box box;
	
	int colliderID = 41;
	
	public Player(Box _box)
	{	
		box = _box;
		
		x = box.getWidth()/2 - width/2;
		y = box.getHeight()/2 - height/2;
		
		image = new ImageIcon("player.png").getImage();
			
	}

	public void update()
	{
		x += (down_dx - up_dx);
		y += (down_dy - up_dy);
		
		if (x + width > box.getWidth())
		{
			x = box.getWidth() - width;
		}
		if (x < 0)
		{
			x = 0;
		}
		if (y + height > box.getHeight())
		{
			y = box.getHeight() - height;
		}
		if (y < 0)
		{
			y = 0;
		}
	}

	public void paint() {
		Canvas.drawImage(image, x + Interpolation.make(down_dx - up_dx), y + Interpolation.make(down_dy - up_dy), null);
	}
	
	public void takeAction(KeyEvent _keyEvent) {
		switch (_keyEvent.getID()) {
			case KeyEvent.KEY_PRESSED: {
				switch (_keyEvent.getKeyCode()) {
					case KeyEvent.VK_DOWN: down_dy = speed; break;
					case KeyEvent.VK_UP: up_dy = speed; break;
					case KeyEvent.VK_LEFT: up_dx = speed; break;
					case KeyEvent.VK_RIGHT: down_dx = speed; break;
				}
			} break;
			case KeyEvent.KEY_RELEASED: {
				switch (_keyEvent.getKeyCode()) {
					case KeyEvent.VK_DOWN: down_dy = 0; break;
					case KeyEvent.VK_UP: up_dy = 0; break;
					case KeyEvent.VK_LEFT: up_dx = 0; break;
					case KeyEvent.VK_RIGHT: down_dx = 0; break;
				}
			} break;
		}
		
		if (_keyEvent.getID() == KeyEvent.KEY_PRESSED)
		{
			if (_keyEvent.getKeyCode() == KeyEvent.VK_C)
			{
				box.add(new Collider(colliderID++,box, x+width/2, y+height/2));
			}
		}
				
	}

	public void keyPressed(KeyEvent _keyEvent) { takeAction(_keyEvent);}

	public void keyReleased(KeyEvent _keyEvent) { takeAction(_keyEvent);}

	public void keyTyped(KeyEvent _keyEvent) {}

	public void mouseClicked(MouseEvent _mouseEvent) {}

	public void mouseEntered(MouseEvent _mouseEvent) {}

	public void mouseExited(MouseEvent _mouseEvent) {}

	public void mousePressed(MouseEvent _mouseEvent) {}

	public void mouseReleased(MouseEvent _mouseEvent) {}

	public void mouseDragged(MouseEvent _mouseEvent) {}

	public void mouseMoved(MouseEvent _mouseEvent) {}

}
