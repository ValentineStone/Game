package com.valentine.game.entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.Interpolation;
import com.valentine.game.utils.MathExt;
import com.valentine.game.utils.Screen;

public class ContainerLiving extends Container {
	
	EntityLiving spirit = new EntityLiving()
	{
		public void paint() {}
		public void update() {}
		protected void reset() {}
		
	};
	
	private static final double VELOCITY_MAX = 3;
	private static final double ACCELERATION = 1;
	private static final double FRICTION = 1;
	
	protected List<Entity> entities;	
	
	
	
	public ContainerLiving(double _x, double _y, double _width, double _height)
	{
		setX(_x);
		setY(_y);
		setWidth(_width);
		setHeight(_height);
		
		getContainer().add(spirit);
		
		spirit.setX(_x);
		spirit.setY(_y);
		spirit.setWidth(_width);
		spirit.setHeight(_height);
		
		spirit.setVelocityMax(VELOCITY_MAX);
		spirit.setAcceleration(ACCELERATION);
		spirit.setFriction(FRICTION);
		
		spirit.setRotationRandom();
		
		entities = new ArrayList<Entity>();
		
		setDrawColor(ColorExt.randomColor(20, 255));
		setFillColor(new Color(getDrawColor().getRed(), getDrawColor().getGreen(), getDrawColor().getBlue(), 50));
	}
	
	public void update()
	{
		spirit.accelerate();
		spirit.move();
		
		if (spirit.keepContained())
		{
			spirit.setVelocity(spirit.getVelocity()/2);
		}
		
		Entity entity;
		
		for (int i = 0; i < getContainer().size(); i++)
		{
			entity = getContainer().get(i);
			
			if (entity instanceof ContainerLiving)
				if (entity.getId() < getId())
					collide((ContainerLiving)entity);
		}
		
		for (int i = 0; i < size(); i++)
		{
			if (entities.get(i).isUpdatable()) entities.get(i).update();
		}
	}
	
	public void paint()
	{		
		Screen.setColor(getFillColor());
		Screen.fillRect(getX(), getY(), getWidth(), getHeight());
		
		Screen.localize(getX(), getY());
		Screen.setClip(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < size(); i++)
		{
			if (entities.get(i).isPaintable()) entities.get(i).paint();
		}
		
		Screen.setClip(null);
		Screen.delocalize(getX(), getY());
		
		Screen.setColor(getDrawColor());
		Screen.drawRect(getX(), getY(), getWidth()-1, getHeight()-1);
	}
	
	
	private boolean isColliding(ContainerLiving _containerLiving)
	{
		if
		(
				(Math.abs(getCenterX() - _containerLiving.getCenterX()) < (getWidth() + _containerLiving.getWidth())/2)
				&&
				(Math.abs(getCenterY() - _containerLiving.getCenterY()) < (getHeight() + _containerLiving.getHeight())/2)
		) return true;
		return false;
	}
	
	public boolean collide(ContainerLiving _containerLiving)
	{
		
		if (isColliding(_containerLiving))
		{
			
			if (_containerLiving.getX() - getX() > 0)
			{
				setRotation(MathExt.rotationFlip(MathExt.rotationMake(_containerLiving.getX() - getX(), _containerLiving.getY() - getY())));
				_containerLiving.setRotation(MathExt.rotationFlip(getRotation()));
			}
			else
			{
				_containerLiving.setRotation(MathExt.rotationFlip(MathExt.rotationMake(_containerLiving.getX() - getX(), _containerLiving.getY() - getY())));
				setRotation(MathExt.rotationFlip(getRotation()));
			}
						
			setDrawColor(ColorExt.randomColor(10,255));
			setFillColor(new Color(getDrawColor().getRed(), getDrawColor().getGreen(), getDrawColor().getBlue(), 20));
			_containerLiving.setDrawColor(ColorExt.randomColor(10,255));
			_containerLiving.setFillColor(new Color(_containerLiving.getDrawColor().getRed(), _containerLiving.getDrawColor().getGreen(), _containerLiving.getDrawColor().getBlue(), 20));
			
			accelerate();
			move();
			_containerLiving.accelerate();
			_containerLiving.move();
			
			return true;
		}

		return false;
	}
	
	
	protected void reset() {}	
	
	
	public int size()
	{
		return entities.size();
	}
	
	public Entity get(int _index)
	{
		return entities.get(_index);
	}
	
	public Container add(Entity _entity)
	{
		entities.add(_entity);
		_entity.setContainer(this);
		_entity.setPaintable(true);
		_entity.setUpdatable(true);
		_entity.reset();
		
		return this;
	}
	
	public boolean remove(Entity _entity)
	{
		return entities.remove(_entity);
	}
}
