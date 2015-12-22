package com.valentine.game.entity;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.Screen;

public class Player extends Entity implements KeyListener {

	Image image;
	
	private final double VELOCITY_MAX = 12;
	private final double ACCELERATION = 1;
	private final double FRICTION = 0.1;
	
	
	private boolean MOVING, MOVING_SOUTH, MOVING_NORTH, MOVING_WEST, MOVING_EAST;
	
	public Player(Container _container)
	{	
		super(_container, 0, 0, 0, 0, 64, 64, true, true);
		setPositionCentered();
		
		MOVING =
		MOVING_SOUTH = 
		MOVING_NORTH =
		MOVING_WEST = 
		MOVING_EAST = false;
		
		image = new ImageIcon("player.png").getImage();			
	}

	public void update()
	{
		super.update();
		
		movementFlagsToRotation();
		
		accelerate();
		move();
		keepContained();
	}

	public void paint()
	{
		super.paint();
		
		if (isMoving())
			Screen.drawImage(image, getX() + Interpolation.make(getVelocityX()), getY() + Interpolation.make(getVelocityY()), null);
		else
			Screen.drawImage(image, getX(), getY(), null);
	}

	
	private void movementFlagsToRotation()
	{
		MOVING = true;
		
		if (MOVING_SOUTH)
		{
			if (MOVING_NORTH)
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						MOVING = false;
					}
					else
					{
						setRotation(Entity.DIRECTION.WEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation(Entity.DIRECTION.EAST.getRotation());
					}
					else
					{
						MOVING = false;
					}
				}
			}
			else
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						setRotation(Entity.DIRECTION.SOUTH.getRotation());
					}
					else
					{
						setRotation(Entity.DIRECTION.SOUTHWEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation(Entity.DIRECTION.SOUTHEAST.getRotation());
					}
					else
					{
						setRotation(Entity.DIRECTION.SOUTH.getRotation());
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
						setRotation(Entity.DIRECTION.NORTH.getRotation());
					}
					else
					{
						setRotation(Entity.DIRECTION.NORTHWEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation( Entity.DIRECTION.NORTHEAST.getRotation());
					}
					else
					{
						setRotation(Entity.DIRECTION.NORTH.getRotation());
					}
				}
			}
			else
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						MOVING = false;
					}
					else
					{
						setRotation(Entity.DIRECTION.WEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation(Entity.DIRECTION.EAST.getRotation());
					}
					else
					{
						MOVING = false;
					}
				}
			}
		}
	}
	
	public boolean isMoving()
	{
		if (MOVING_SOUTH || MOVING_NORTH || MOVING_WEST || MOVING_EAST) return true;
		return false;
	}
	
	private void accelerate()
	{
		if (MOVING)
		{
			if (getVelocity() < VELOCITY_MAX)
			{
				setVelocity(getVelocity() + VELOCITY_MAX * ACCELERATION);
				if (getVelocity() > VELOCITY_MAX) setVelocity(VELOCITY_MAX);
			}
			
		}
		else
		{
			if (getVelocity() > 0)
			{
				setVelocity(getVelocity() - VELOCITY_MAX * FRICTION);
				if (getVelocity() < 0) setVelocity(0);
			}
		}
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
				getContainer().add(new Collider(getContainer(), getX()+getWidth()/2, getY()+getHeight()/2));
				break;
			}
			case KeyEvent.VK_D:
			{
				for (int i = getContainer().size()-1; i >= 0; i--)
				{
					Entity entity = getContainer().get(i);
					if (entity instanceof Collider)
					{
						if (entity.hit(getX(), getY()))
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

	public void keyTyped(KeyEvent _keyEvent) {}
}
