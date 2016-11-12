package com.valentine.game.entity.fuzzyset;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.Collider;
import com.valentine.game.entity.ui.*;

public class FuzzySetsWindow extends ContainerWindow
{

	public FuzzySetsWindow(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container, _x, _y, _width, _height);
		
		GButton btn = new GButton(this, "Do smth!");
		
		btn.addListener
		(
			new Runnable()
			{
		
				
				public void run()
				{
					new Collider(btn.getContainer());
				}
			}
		);
	}

}
