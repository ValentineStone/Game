package com.valentine.game.entity;

public abstract class Entity
{
	private static int idGlobal = 0;
	
	private Container container;
	
	private boolean paintable = false;
	private boolean updatable = false;
	private boolean dead = false;
	
	private int id;
	
	private double x;
	private double y;
	private double rotation;
	
	private double velocity;
	
	private double width;
	private double height;
	
	public Entity
	(
		Container _container,
		double _x,
		double _y,
		double _rotation,
		double _velocity,
		double _width,
		double _height,
		boolean _paintable,
		boolean _updatable
	)
	{
		id = idGlobal++;
		
		container = _container;
		x = _x;
		y = _y;
		rotation = _rotation;
		velocity = _velocity;
		width = _width;
		height = _height;
		paintable = _paintable;
		updatable = _updatable;
		dead = false;
	}
	
	public void paint()
	{
		if (paintable);
		else return;
	}
	
	public void update()
	{
		if (dead)
		{
			container.remove(this);
		}
		if (updatable);
		else return;
	}
	
	
	public boolean hit(double _x, double _y)
	{
		if (_x > x && _x < (x + width) && _y > y && _y < (y + height)) return true;
		return false;
	}
	
	protected void keepContained()
	{
		if (getX() + getWidth() > getContainer().getWidth())
		{
			setX(getContainer().getWidth() - getWidth());
			rotationFlipY();
		}
		if (getX() < 0)
		{
			setX(0);
			rotationFlipY();
		}
		if (getY() + getHeight() > getContainer().getHeight())
		{
			setY(getContainer().getHeight() - getHeight());
			rotationFlipX();
		}
		if (getY() < 0)
		{
			setY(0);
			rotationFlipX();
		}
	}
	
	protected void move()
	{	
		setX(getX() + getVelocityX());
		setY(getY() + getVelocityY());
	}
	
	
	
	
	public Container getContainer()
	{
		return container;
	}
	
	public void setContainer(Container _container)
	{
		container = _container;
	}
	
	
	
	

	public boolean isPaintable()
	{
		return paintable;
	}

	public void setPaintable(boolean _paintable)
	{
		paintable = _paintable;
	}

	public boolean isUpdatable()
	{
		return updatable;
	}

	public void setUpdatable(boolean _updatable)
	{
		updatable = _updatable;
	}
	
	

	public boolean isDead() {
		return dead;
	}
	
	public void kill()
	{
		dead = true;
		updatable = true;
		paintable = false;
	}

	public int getId()
	{
		return id;
	}
	
	
	
	
	
	
	
	public double getX()
	{
		return x;
	}

	public void setX(double _x)
	{
		x = _x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double _y)
	{
		y = _y;
	}
	
	public double getCenterX()
	{
		return x + width/2;
	}
	
	public double getCenterY()
	{
		return y + height/2;
	}
	
	public void setPosition(double _x, double _y)
	{
		x = _x;
		y = _y;
	}
	
	public void setPositionRandom()
	{
		x = Math.random() * container.getWidth();
		y = Math.random() * container.getHeight();
	}
	
	public void setPositionCentered()
	{
		x = container.getWidth()/2 - width/2;
		y = container.getHeight()/2 - height/2;;
	}
	
	
	
	
	
	
	
	public double getRotation()
	{
		return rotation;
	}

	public void setRotation(double _rotation)
	{
		rotation = _rotation;
		normalizeRotation();
	}
	
	public void setRotationRandom()
	{
		rotation = Math.random() * 2 * Math.PI;
	}
	
	public void rotationFlipX()
	{
		setRotation(rotationFlipX(rotation));
	}
	
	public void rotationFlipY()
	{
		setRotation(rotationFlipY(rotation));
	}
	
	public void rotationFlip()
	{
		setRotation(rotationFlip(rotation));
	}
	
	public double rotationFlipX(double _rotation)
	{
		return 2 * Math.PI - _rotation;
	}
	
	public double rotationFlipY(double _rotation)
	{
		return Math.PI - rotation;
	}
	
	public double rotationFlip(double _rotation)
	{
		return rotationFlipX(rotationFlipY(_rotation));
	}
	
	public void normalizeRotation()
	{
		normalizeRotation(rotation);
	}
	
	public double normalizeRotation(double _rotation)
	{
		if (_rotation > 2 * Math.PI) _rotation -= (int)(_rotation/(2 * Math.PI)) * 2 * Math.PI;
		else if (_rotation < 0)	_rotation -= (int)(_rotation/(2 * Math.PI) + 1) * 2 * Math.PI;
		return _rotation;
	}
	
	public double rotationMake(double _x, double _y)
	{
		double lenght = Math.pow(_x*_x + _y*_y, 0.5);
		_x /= lenght;
		_y /= lenght;
		
		if (_x > 0)
		{
			return normalizeRotation(Math.asin(_y));
		}
		else
		{
			return normalizeRotation(rotationFlipX(Math.asin(_y)));
		}
	}
	
	
	
	
	

	public double getVelocity()
	{
		return velocity;
	}
	
	public double getVelocityX()
	{
		return velocity * Math.cos(rotation);
	}
	
	public double getVelocityY()
	{
		return velocity * Math.sin(rotation);
	}

	public void setVelocity(double _velocity)
	{
		velocity = _velocity;
	}
	
	public void setVelocityRandom(double _min, double _max)
	{
		velocity = Math.random() * (_max - _min) + _min;
	}
	
	
	
	
	

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double _width)
	{
		width = _width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double _height)
	{
		height = _height;
	}
	
	
	
	

	public int hashCode()
	{
		return id;
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof Entity) if (((Entity)obj).id == id) return true;
		return false;
	}
	
	public String toString()
	{
		return (paintable ? "paintable " : "NOTpaintable ") + (updatable ? "updatable " : "NOTupdatable ") + "entity " + id + " in container " + container.toString();
	}
	
	public static enum DIRECTION
	{
		EAST 		( 0 ),
		NORTHEAST 	( Math.PI / 4 ),
		NORTH 		( Math.PI / 2 ),
		NORTHWEST 	( 3 * Math.PI / 4 ),
		WEST 		( Math.PI ),
		SOUTHWEST 	( 5 * Math.PI / 4 ),
		SOUTH 		( 3 * Math.PI / 2 ),
		SOUTHEAST 	( 7 * Math.PI / 4 );
		
		private final double rotation;
		
		DIRECTION(double _rotation)
		{
			rotation = _rotation;
		}
		
		public double getRotation()
		{
			return rotation;
		}
		
	}
	
	
}
