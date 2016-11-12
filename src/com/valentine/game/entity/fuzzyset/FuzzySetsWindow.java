package com.valentine.game.entity.fuzzyset;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.creatures.*;
import com.valentine.game.entity.ui.*;

public class FuzzySetsWindow extends ContainerWindow
{

	public FuzzySetsWindow(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container, _x, _y, _width, _height);
		
		final GButton btn = new GButton(this, "")
		{
			int state = 0;
			String states = "\\|/-"; // "|\\-/";
			
			public void update()
			{
				super.update();
				state++;
				setText(states.substring(state - 1, state));
				if (state > states.length() - 1) state = 0;
				
				setPositionCentered();
			}
		};
		
		btn.addListener
		(
			new Runnable()
			{
				Entity entity = new Collider(btn.getContainer());
				
				public void run()
				{
					entity = new Tracker(btn.getContainer(), entity);
				}
			}
		);
	}

}
