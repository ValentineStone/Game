package com.valentine.game.entity;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.MathExt;
import com.valentine.game.utils.Screen;

public class Player extends EntityLiving implements KeyListener {
	
	//private List<Link> links = new ArrayList<Link>();

	private Image image;
	
	private static final double VELOCITY_MAX = 30;
	private static final double ACCELERATION = 0.03;
	private static final double FRICTION = 0.05;

	private static final double VELOCITY_PRECISION = 10;
	
	
	private boolean MOVING_SOUTH, MOVING_NORTH, MOVING_WEST, MOVING_EAST, BREAKS_ON;
	
	public Player(Container _container)
	{
		setVelocityMax(VELOCITY_MAX);
		setAcceleration(ACCELERATION);
		setFriction(FRICTION);
		setWidth(64);
		setHeight(64);
		
		MOVING_SOUTH = 
		MOVING_NORTH =
		MOVING_WEST = 
		MOVING_EAST = false;
		
		image = new ImageIcon("res/player.png").getImage();			
	}
	
	protected void reset()
	{
		setPositionCentered();
	}

	public void update()
	{
		if (!breaks()) accelerate();
		move();
		keepContained();
		movementFlagsToRotation();
	}

	public void paint()
	{
		if (isMoving() && !isTouchingEdge())
			Screen.drawImage(image, getX() + Interpolation.make(getVelocityX()), getY() + Interpolation.make(getVelocityY()), null);
		else
			Screen.drawImage(image, getX(), getY(), null);
	}

	
	private void movementFlagsToRotation()
	{
		setActive(true);
		
		if (MOVING_SOUTH)
		{
			if (MOVING_NORTH)
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						setActive(false);
					}
					else
					{
						setRotation(MathExt.DIRECTION.WEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation(MathExt.DIRECTION.EAST.getRotation());
					}
					else
					{
						setActive(false);
					}
				}
			}
			else
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						setRotation(MathExt.DIRECTION.SOUTH.getRotation());
					}
					else
					{
						setRotation(MathExt.DIRECTION.SOUTHWEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation(MathExt.DIRECTION.SOUTHEAST.getRotation());
					}
					else
					{
						setRotation(MathExt.DIRECTION.SOUTH.getRotation());
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
						setRotation(MathExt.DIRECTION.NORTH.getRotation());
					}
					else
					{
						setRotation(MathExt.DIRECTION.NORTHWEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation( MathExt.DIRECTION.NORTHEAST.getRotation());
					}
					else
					{
						setRotation(MathExt.DIRECTION.NORTH.getRotation());
					}
				}
			}
			else
			{
				if (MOVING_WEST)
				{
					if (MOVING_EAST)
					{
						setActive(false);
					}
					else
					{
						setRotation(MathExt.DIRECTION.WEST.getRotation());
					}
				}
				else
				{
					if (MOVING_EAST)
					{
						setRotation(MathExt.DIRECTION.EAST.getRotation());
					}
					else
					{
						setActive(false);
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
	
	protected boolean breaks()
	{
		if (BREAKS_ON && getVelocity() > VELOCITY_PRECISION)
		{
			setVelocity(VELOCITY_PRECISION);
			return true;
		}
		return false;
	}
	
	public void keyPressed(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
			{
				MOVING_NORTH = true;
				//System.err.println("begin NORTH");
				break;
			}
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
			{
				MOVING_SOUTH = true;
				//System.err.println("begin SOUTH");
				break;
			}
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
			{
				MOVING_WEST = true;
				//System.err.println("begin WEST");
				break;
			}
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
			{
				MOVING_EAST = true;
				//System.err.println("begin EAST");
				break;
			}
			case KeyEvent.VK_SPACE:
			{
				BREAKS_ON = true;
				//System.err.println("begin BREAKS_ON");
				break;
			}
			case KeyEvent.VK_C:
			{
				Collider newCollider = new Collider(getCenterX(), getCenterY());
				getContainer().add(newCollider);
				newCollider.setPosition(getCenterX(), getCenterY());
				getContainer().add(new Link(
											this,
											newCollider
											));
				break;
			}
			case KeyEvent.VK_R:
			{
				double r = MathExt.random(10,30);
				Rotor2 newRotor = new Rotor2(getCenterX() - r, getCenterY() - r, r);
				newRotor.setVelocityMax(7).setAcceleration(0.07);
				getContainer().add(newRotor);
				newRotor.setPosition(getCenterX() - r, getCenterY() - r);
				break;
			}
			/*
			case KeyEvent.VK_X:
			{
				for (int i = getContainer().size()-1; i >= 0; i--)
				{
					Entity entity = getContainer().get(i);
					if (entity instanceof Collider)
					{
						if (entity.hit(getCenterX(), getCenterY()))
						{
							entity.kill();
							break;
						}
					}
				}
			}
			*/
		}
	}

	public void keyReleased(KeyEvent _keyEvent)
	{
		switch (_keyEvent.getKeyCode())
		{
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
			{
				MOVING_NORTH = false;
				//System.err.println("end NORTH");
				break;
			}
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
			{
				MOVING_SOUTH = false;
				//System.err.println("end SOUTH");
				break;
			}
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
			{
				MOVING_WEST = false;
				//System.err.println("end WEST");
				break;
			}
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
			{
				MOVING_EAST = false;
				//System.err.println("end EAST");
				break;
			}
			case KeyEvent.VK_SPACE:
			{
				BREAKS_ON = false;
				//System.err.println("begin BREAKS_ON");
				break;
			}
		}
	}

	public void keyTyped(KeyEvent _keyEvent) {}
}
