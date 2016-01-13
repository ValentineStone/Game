package com.valentine.game.entity.ambient;

import java.awt.Color;

import com.valentine.game.core.*;
import com.valentine.game.entity.base.*;
import com.valentine.game.utils.MathExt;

public class StarrySkyMoving extends Entity
{
	private static Color[][] shadesOfColor;
	
	static
	{
		shadesOfColor = new Color[4][256];
		
		for (int i = 0; i < 256; i++)
		{
			shadesOfColor[0][i] = new Color(i,i,i);
			shadesOfColor[1][i] = new Color(i,i/2,i/2);
			shadesOfColor[2][i] = new Color(i/2,i,i/2);
			shadesOfColor[3][i] = new Color(i/2,i/2,i);
		}
	}
	
	private Star[] Stars;	
	
	public StarrySkyMoving(Container _container)
	{
		super(_container);
		
		Stars = new Star[(int)(getContainer().getWidth() + getContainer().getHeight()) / 2];
		
		for (int i = 0; i < Stars.length; i++) Stars[i] = new Star();	
	}

	
	
	
	
	
	public void paint()
	{
		for (int i = 0; i < Stars.length; i++)
		{
			Screen.setColor(shadesOfColor[Stars[i].type][Stars[i].brightness]);
			Screen.fillRect(Stars[i].x + Interpolation.make(Stars[i].dx), Stars[i].y, Stars[i].size, Stars[i].size);
		}
	}

	public void update()
	{
		for (int i = 0; i < Stars.length; i++)
		{
			Stars[i].tick();
		}
	}

	
	
	private class Star
	{
		public static final int sizeMax = 4;
		public static final int brightnessMax = 150;
		
		public int size;
		public int brightness;
		public int dBrightness = 5;
		public int type;
		
		public double x;
		public double y;
		
		public double dx = -10;
		
		
		public Star()
		{
			type = MathExt.randomIf(0.7) ? 0 : (int)MathExt.random(1,4);
			
			respawn();
			
			brightness = (int)(MathExt.random(1, brightnessMax));
			
			if (MathExt.randomIf()) dBrightness = -dBrightness;
			
			dx = MathExt.random(dx);
		}
		
		private void respawn()
		{
			size = (int)(MathExt.random(1, sizeMax));
			
			x = MathExt.random(getContainer().getWidth() - size  + 2 * dx);
			y = MathExt.random(getContainer().getHeight() - size);
		}
		
		public void tick()
		{
			brightness += dBrightness;
			
			x += dx;
		
			if (x <= 0)
			{
				respawn();
				x = MathExt.random(getContainer().getWidth(), getContainer().getWidth() + 2 * dx);
				brightness = (int)(MathExt.random(1, brightnessMax));
			}
			
			if (brightness <= 0)
			{
				respawn();
				
				dBrightness = -dBrightness;
				brightness = 0;
			}
			else if (brightness >= brightnessMax)
			{
				dBrightness = -dBrightness;
				brightness = brightnessMax;
			}
		}	
	}
	
	
	
}