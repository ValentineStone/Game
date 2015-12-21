package com.valentine.game.entity;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class Player extends Entity implements KeyListener {
	Image image;
	public double up_dx;
	public double down_dx;
	public double up_dy;
	public double down_dy;
	Container container;
	
	int colliderID = 41;
	
	public Player(Container _container)
	{	
		container = _container;
		width = 64;
		height = 64;
		x = container.getWidth()/2 - width/2;
		y = container.getHeight()/2 - height/2;
		velocity = 10;
		
		image = new ImageIcon("player.png").getImage();
			
	}

	public void update()
	{
		x += (down_dx - up_dx);
		y += (down_dy - up_dy);
		
		if (x + width > container.getWidth())
		{
			x = container.getWidth() - width;
		}
		if (x < 0)
		{
			x = 0;
		}
		if (y + height > container.getHeight())
		{
			y = container.getHeight() - height;
		}
		if (y < 0)
		{
			y = 0;
		}
	}

	public void paint() {
		Screen.drawImage(image, x - width/2. + Interpolation.make(down_dx - up_dx), y - height/2. + Interpolation.make(down_dy - up_dy), null);
	}
	
	public void takeAction(KeyEvent _keyEvent) {
		switch (_keyEvent.getID()) {
			case KeyEvent.KEY_PRESSED: {
				switch (_keyEvent.getKeyCode()) {
					case KeyEvent.VK_DOWN: down_dy = velocity; break;
					case KeyEvent.VK_UP: up_dy = velocity; break;
					case KeyEvent.VK_LEFT: up_dx = velocity; break;
					case KeyEvent.VK_RIGHT: down_dx = velocity; break;
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
				container.add(new Collider(container, x+width/2, y+height/2));
			}
			else if (_keyEvent.getKeyCode() == KeyEvent.VK_D)
			{
				for (Entity entity : container)
				{
					if (entity instanceof Collider)
					{
						if (entity.hit(x, y))
						{
							container.remove(entity);
							break;
						}
					}
				}
			}
		}
				
	}

	public void keyPressed(KeyEvent _keyEvent) { takeAction(_keyEvent);}

	public void keyReleased(KeyEvent _keyEvent) { takeAction(_keyEvent);}

	public void keyTyped(KeyEvent _keyEvent) {}

}
