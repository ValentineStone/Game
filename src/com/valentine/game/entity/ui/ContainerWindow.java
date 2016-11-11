package com.valentine.game.entity.ui;

import java.util.Iterator;
import java.util.List;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.geometry.*;
import com.valentine.game.utils.ColorExt;

public class ContainerWindow extends Container
{
	private Rect dragger;
	private DragHandler dragHandler;
	private Container innards;
	
	private enum Mode
	{
		CRAFTING,
		CRAFTED
	};
	
	private Mode mode = Mode.CRAFTING;

	public ContainerWindow(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container, _x, _y, _width, _height);

		dragger = new Rect(getContainer());
		dragger.setSize(getWidth() - 1, 30);
		dragger.setPosition(getX(), getY());

		dragHandler = new DragHandler(this, dragger);
		dragHandler.setUpdatable(false);
		
		innards = new Container(this, 0, 30, getWidth(), getHeight() - 30);
		innards.setDrawColor(ColorExt.TRANSPARENT);
		innards.setFillColor(ColorExt.TRANSPARENT);
		
		mode = Mode.CRAFTED;
	}

	public void update()
	{
		dragHandler.update();
		setPosition(dragger.getX(), dragger.getY());
		super.update();
	}

	public int size()
	{
		switch (mode)
		{
			case CRAFTING:
				return super.size();
			case CRAFTED:
			default:
				return innards.size();
		}
	}

	public void moveIn(Entity _entity)
	{
		switch (mode)
		{
			case CRAFTING:
				super.moveIn(_entity);
				break;
			case CRAFTED:
			default:
				innards.moveIn(_entity);
		}
	}

	public void remove(Entity _entity)
	{
		switch (mode)
		{
			case CRAFTING:
				super.remove(_entity);
				break;
			case CRAFTED:
			default:
				innards.remove(_entity);
		}
	}

	public Iterator<Entity> iterator()
	{
		switch (mode)
		{
			case CRAFTING:
				return super.iterator();
			case CRAFTED:
			default:
				return innards.iterator();
		}
	}

	public List<Entity> getItems()
	{
		switch (mode)
		{
			case CRAFTING:
				return super.getItems();
			case CRAFTED:
			default:
				return innards.getItems();
		}
	}

	public List<Entity> getAdditions()
	{
		switch (mode)
		{
			case CRAFTING:
				return super.getAdditions();
			case CRAFTED:
			default:
				return innards.getAdditions();
		}
	}

	public List<Entity> getDeletions()
	{
		switch (mode)
		{
			case CRAFTING:
				return super.getDeletions();
			case CRAFTED:
			default:
				return innards.getDeletions();
		}
	}
}
