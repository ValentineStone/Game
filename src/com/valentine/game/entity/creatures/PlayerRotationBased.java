package com.valentine.game.entity.creatures;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import com.valentine.game.core.*;
import com.valentine.game.entity.*;
import com.valentine.game.entity.base.*;

public class PlayerRotationBased extends Entity implements KeyListener {

	Image image;
	
	public static final double VELOCITY_MAX = 10;
	
	private double acceleration = 0.5;
	
	private double rotationAcceleration = Math.PI / 4.0001;
	
	private boolean MOVING_SOUTH, MOVING_NORTH, MOVING_WEST, MOVING_EAST;
	
	public PlayerRotationBased(Container _container)
	{	
		super(_container, 0, 0, 0, 0, 64, 64, true, true);
		setPositionCentered();
		
		MOVING_SOUTH = 
		MOVING_NORTH =
		MOVING_WEST = 
		MOVING_EAST = false;
		
		image = new ImageIcon("player.png").getImage();
		
		Input.addKeyListener(new KeyListener()
		{
			public void keyReleased(KeyEvent _keyEvent) {}
			public void keyTyped(KeyEvent _keyEvent)
			{
				switch (_keyEvent.getKeyCode())
				{
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_UP:
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_RIGHT:
					{
						keyPressed(_keyEvent);
					}
				}
			}
			public void keyPressed(KeyEvent _keyEvent)
			{
				switch (_keyEvent.getKeyCode())
				{
					case KeyEvent.VK_DOWN:
					{
						rotation = com.valentine.game.entity.base.DIRECTION.NORTH;
						Input.removeKeyListener(this);
						break;
					}
					case KeyEvent.VK_UP:
					{
						rotation = com.valentine.game.entity.base.DIRECTION.SOUTH;
						Input.removeKeyListener(this);
						break;
					}
					case KeyEvent.VK_LEFT:
					{
						rotation = com.valentine.game.entity.base.DIRECTION.WEST;
						Input.removeKeyListener(this);
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						rotation = com.valentine.game.entity.base.DIRECTION.EAST;
						Input.removeKeyListener(this);
						break;
					}
				}
			}			
		});
			
	}

	public void update()
	{
		super.update();
		
		if (MOVING_SOUTH)
		{
			if (MOVING_NORTH)
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						accelerate(-1);
					}
					else
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.WEST);
						accelerate(1);
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.EAST);
						accelerate(1);
					}
					else
					{
						accelerate(-1);
					}
				}
			}
			else
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.SOUTH);
						accelerate(1);
					}
					else
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.SOUTHWEST);
						accelerate(1);
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.SOUTHEAST);
						accelerate(1);
					}
					else
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.SOUTH);
						accelerate(1);
					}
				}
			}
		}
		else
		{
			if (MOVING_NORTH)
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.NORTH);
						accelerate(1);
					}
					else
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.NORTHWEST);
						accelerate(1);
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.NORTHEAST);
						accelerate(1);
					}
					else
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.NORTH);
						accelerate(1);
					}
				}
			}
			else
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						accelerate(-1);
					}
					else
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.WEST);
						accelerate(1);
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						rotateTowards(com.valentine.game.entity.base.DIRECTION.EAST);
						accelerate(1);
					}
					else
					{
						accelerate(-1);
					}
				}
			}
		}
		
		move();
		
		keepContained();
	}

	public void paint()
	{
		super.paint();
		
		Screen.drawImage(image, x/* + Interpolation.make(getVelocityX())*/, y/* + Interpolation.make(getVelocityY())*/, null);
	}
	
	public void keepContained()
	{
		if (x + width > container.getWidth())
		{
			x = container.getWidth() - width;
			rotationFlipX();
		}
		if (x < 0)
		{
			x = 0;
			rotationFlipX();
		}
		if (y + height > container.getHeight())
		{
			y = container.getHeight() - height;
			rotationFlipY();
		}
		if (y < 0)
		{
			y = 0;
			rotationFlipY();
		}
	}
	
	public void rotationFlipX()
	{
		rotation = 2 * Math.PI - rotation;
	}
	
	public void rotationFlipY()
	{
		rotation = Math.PI - rotation;
	}
	
	public void move()
	{	
		x += Math.cos(rotation) * velocity;
		y += Math.sin(rotation) * velocity;
	}
	
	public void keepRotionNormal()
	{
		double rotation_old = rotation;
		if (rotation < 0) rotation = (int)(rotation/(2 * Math.PI) + 1) * 2 * Math.PI - rotation;
		if (rotation > 2 * Math.PI) rotation = rotation - (int)(rotation/(2 * Math.PI)) * 2 * Math.PI;
		if (rotation_old == rotation) System.err.println(rotation_old + " to " + rotation);
	}
	
	public boolean isMoving()
	{
		if (MOVING_SOUTH || MOVING_NORTH || MOVING_WEST || MOVING_EAST) return true;
		return false;
	}
	
	private void accelerate(int _direction)
	{
		if (_direction > 0)
		{
			if (velocity < VELOCITY_MAX)
			{
				velocity += acceleration;
				if (velocity > VELOCITY_MAX) velocity = VELOCITY_MAX;
			}
			
		}
		else
		{
			if (velocity > 0)
			{
				velocity -= acceleration * 2;
				if (velocity < 0) velocity = 0;
			}
		}
	}
	
	private void rotateTowards(double _rotation)
	{
		if (_rotation == rotation) return;
		
		if (Math.abs(_rotation - rotation) < rotationAcceleration) rotation = _rotation;
		
		else
		{
			if (_rotation > rotation)
			{
				if (_rotation - rotation < Math.PI)
				{
					rotation += rotationAcceleration;
				}
				else
				{
					rotation -= rotationAcceleration;
				}
			}
			else
			{
				if (rotation - _rotation <= Math.PI)
				{
					rotation -= rotationAcceleration;
				}
				else
				{
					rotation += rotationAcceleration;
				}
			}
		}
		
		keepRotionNormal();
		
		System.err.println(_rotation + " < " + rotation);
		System.err.println("NORTH:" + MOVING_NORTH + ", SOUTH:" + MOVING_SOUTH + ", WEST:" + MOVING_WEST + ", EAST:" + MOVING_EAST );
	}

	public void keyPressed(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_DOWN:
			{
				MOVING_NORTH = true;
				//System.err.println("begin NORTH");
				break;
			}
			case KeyEvent.VK_UP:
			{
				MOVING_SOUTH = true;
				//System.err.println("begin SOUTH");
				break;
			}
			case KeyEvent.VK_LEFT:
			{
				MOVING_WEST = true;
				//System.err.println("begin WEST");
				break;
			}
			case KeyEvent.VK_RIGHT:
			{
				MOVING_EAST = true;
				//System.err.println("begin EAST");
				break;
			}
			case KeyEvent.VK_C:
			{
				container.add(new Collider(container, x+width/2, y+height/2));
				break;
			}
			case KeyEvent.VK_D:
			{
				for (int i = container.size()-1; i >= 0; i--)
				{
					Entity entity = container.get(i);
					if (entity instanceof Collider)
					{
						if (entity.hit(x, y))
						{
							entity.kill();
							break;
						}
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_DOWN:
			{
				MOVING_NORTH = false;
				//System.err.println("end NORTH");
				break;
			}
			case KeyEvent.VK_UP:
			{
				MOVING_SOUTH = false;
				//System.err.println("end SOUTH");
				break;
			}
			case KeyEvent.VK_LEFT:
			{
				MOVING_WEST = false;
				//System.err.println("end WEST");
				break;
			}
			case KeyEvent.VK_RIGHT:
			{
				MOVING_EAST = false;
				//System.err.println("end EAST");
				break;
			}
		}
	}

	public void keyTyped(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
			{
				keyReleased(_keyEvent);
			}
		}
	}
}
