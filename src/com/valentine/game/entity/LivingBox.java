package com.valentine.game.entity;

import java.awt.Color;

import com.valentine.game.utils.*;

public class LivingBox extends Box implements Entity
{
	private double dx = (Math.random() > 0.5 ? 1 : -1) * (Math.random() * 6 + 2);
	private double dy = (Math.random() > 0.5 ? 1 : -1) * (Math.random() * 6 + 2);
	
	private Color fillColor = new Color(0,0,0,0);
	private Color borderColor = new Color(0,0,0,0);
	
	private Box box = Game.instance();
	
	public LivingBox(Box _box, double _x, double _y, double _width, double _height, Color _borderColor, Color _fillColor)
	{
		super(_x, _y, _width, _height);
		setBox(_box);
		setBorderColor(_borderColor);
		setFillColor(_fillColor);
	}
	
	public LivingBox(Box _box, double _x, double _y, double _width, double _height, Color _borderColor)
	{
		super(_x, _y, _width, _height);
		setBox(_box);
		setBorderColor(_borderColor);
	}
	
	public LivingBox(Box _box, double _x, double _y, double _width, double _height)
	{
		super(_x, _y, _width, _height);
		setBox(_box);
	}
	
	public void paint()
	{
		double DX = Interpolation.make(getX(),getDx());
		double DY = Interpolation.make(getY(),getDy());
		
		Screen.localize(
						DX,
						DY
						);
		
		Screen.setColor(getFillColor());
		
		Screen.fillRect(
						0,
						0,
						getWidth(),
						getHeight()
						);
		
		Screen.setColor(getBorderColor());
		
		Screen.drawRect(
						0,
						0,
						getWidth(),
						getHeight()
						);
		
		super.paint();
		
		Screen.delocalize(
						  DX,
						  DY
						  );
	}
	
	public void update()
	{
		super.update();
		
		x += dx;
		y += dy;
		
		if (x+width > box.getWidth()) {
			x = box.getWidth() - width;
			dx = -dx;
		}
		
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		
		if (y+height > box.getHeight()) {
			y = box.getHeight() - height;
			dy = -dy;
		}
		
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
	}
	
	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color _fillColor) {
		fillColor = _fillColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color _borderColor) {
		borderColor = _borderColor;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}
	
	
	
	

}
