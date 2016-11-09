package com.valentine.game.entity.creatures;

import java.awt.*;

import com.valentine.game.core.loop.*;
import com.valentine.game.core.screen.*;
import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.objects.*;
import com.valentine.game.utils.*;

public class PlayerSpacecraft extends Player implements Explodable
{
	private Color engineColor = ColorExt.randomColor(10, 255);
	private Color engineColorTransparent = ColorExt.randomColor(10, 255);
	private Color engineColorNew = ColorExt.randomColor(10, 255);
	private Color frameColor = ColorExt.randomColor(10, 255);
	private Color frameColorTransparent = ColorExt.randomColor(10, 255);
	private Color frameColorNew = ColorExt.randomColor(10, 255);
	private Color bodyColor = ColorExt.randomColor(10, 255);
	private Color bodyColorTransparent = ColorExt.randomColor(10, 255);
	private Color bodyColorNew = ColorExt.randomColor(10, 255);
	private Color cannonColor = ColorExt.randomColor(10, 255);
	private Color cannonColorTransparent = ColorExt.randomColor(10, 255);
	private Color cannonColorNew = ColorExt.randomColor(10, 255);

	public PlayerSpacecraft(Container _container)
	{
		super(_container);
	}

	public void paint(Screen _screen)
	{
		_screen.localize(getX(), getY());

		if (getAcceleration() != 0)
		{
			_screen.localize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		}

		_screen.setColor(frameColorTransparent);
		_screen.fillOval(16, 0, 32, 32);
		_screen.fillOval(16, 32, 32, 32);

		_screen.setColor(engineColorTransparent);
		_screen.fillRect(0, 0, 32, 16);
		_screen.fillRect(0, 48, 32, 16);

		_screen.setColor(bodyColorTransparent);
		_screen.fillRect(16, 16, 32, 32);

		_screen.setColor(cannonColorTransparent);
		_screen.fillRect(16, 24, 48, 16);

		_screen.setColor(getDrawColor());
		// Screen.setColor(frameColor);
		_screen.drawOval(16, 0, 32, 32);
		_screen.drawOval(16, 32, 32, 32);

		// Screen.setColor(engineColor);
		_screen.drawRect(0, 0, 32, 16);
		_screen.drawRect(0, 48, 32, 16);

		// Screen.setColor(bodyColor);
		_screen.drawRect(16, 16, 32, 32);

		// Screen.setColor(cannonColor);
		_screen.drawRect(16, 24, 48, 16);

		if (getAcceleration() != 0)
		{
			_screen.delocalize(Interpolation.make(getVelocityX()), Interpolation.make(getVelocityY()));
		}

		_screen.delocalize(getX(), getY());
	}

	public void update()
	{
		if (!breaks())
		{
			accelerate();
		}
		move();
		keepOffEdges();
		movementFlagsToRotation();

		if (isTouchingEdge())
		{
			setVelocity(0);
		}

		if (engineColor.equals(engineColorNew))
		{
			engineColorNew = ColorExt.randomColor(0, 255);
		}
		engineColor = ColorExt.fadeto(engineColor, engineColorNew);
		engineColorTransparent = ColorExt.makeTransparent(engineColor, 200);
		if (frameColor.equals(frameColorNew))
		{
			frameColorNew = ColorExt.randomColor(0, 255);
		}
		frameColor = ColorExt.fadeto(frameColor, frameColorNew);
		frameColorTransparent = ColorExt.makeTransparent(frameColor, 30);
		if (bodyColor.equals(bodyColorNew))
		{
			bodyColorNew = ColorExt.randomColor(0, 255);
		}
		bodyColor = ColorExt.fadeto(bodyColor, bodyColorNew);
		bodyColorTransparent = ColorExt.makeTransparent(bodyColor, 30);
		if (cannonColor.equals(cannonColorNew))
		{
			cannonColorNew = ColorExt.randomColor(0, 255);
		}
		cannonColor = ColorExt.fadeto(cannonColor, cannonColorNew);
		cannonColorTransparent = ColorExt.makeTransparent(cannonColor, 200);
	}

}
