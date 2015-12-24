package com.valentine.game.entity;

public abstract class Entity
{
	private static int idGlobal = 0;
	
	private Container container;
	
	private boolean paintable = false;
	private boolean updatable = false;
	private boolean active = false;
	private boolean dead = false;
	
	private int id;
	
	private double x;
	private double y;
	private double rotation;
	
	private double velocity;
	private double velocityMax;
	private double acceleration;
	private double friction;
	
	private double width;
	private double height;
	
	public Entity
	(
		Container _container,
		double _x,
		double _y,
		double _rotation,
		double _velocity,
		double _velocityMax,
		double _acceleration,
		double _friction,
		double _width,
		double _height,
		boolean _paintable,
		boolean _updatable,
		boolean _active
	)
	{
		id = idGlobal++;
		
		container = _container;
		x = _x;
		y = _y;
		rotation = _rotation;
		velocity = _velocity;
		velocityMax = _velocityMax;
		acceleration = _acceleration;
		friction = _friction;
		width = _width;
		height = _height;
		paintable = _paintable;
		updatable = _updatable;
		active = _active;
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
	
	protected boolean keepContained()
	{
		boolean impact = false;
		if (getX() + getWidth() > getContainer().getWidth())
		{
			setX(getContainer().getWidth() - getWidth());
			rotationFlipY();
			impact = true;
		}
		if (getX() < 0)
		{
			setX(0);
			rotationFlipY();
			impact = true;
		}
		if (getY() + getHeight() > getContainer().getHeight())
		{
			setY(getContainer().getHeight() - getHeight());
			rotationFlipX();
			impact = true;
		}
		if (getY() < 0)
		{
			setY(0);
			rotationFlipX();
			impact = true;
		}
		return impact;
	}
	
	protected void move()
	{	
		setX(getX() + getVelocityX());
		setY(getY() + getVelocityY());
	}
	
	protected void accelerate()
	{
		if (active)
		{
			if (velocity < velocityMax)
			{
				velocity += velocityMax * acceleration;
				if (velocity > velocityMax) velocity = velocityMax;
			}
			
		}
		else
		{
			if (velocity > 0)
			{
				velocity -= velocityMax * friction;
				if (velocity < 0) velocity = 0;
			}
		}
	}
	
	protected boolean isTouchingEdge()
	{
		if (
			(getX() + getWidth() >= getContainer().getWidth()) ||
			(getX() <= 0) ||
			(getY() + getHeight() >= getContainer().getHeight()) ||
			(getY() <= 0)
			)
			return true;
		
		return false;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
		x = Math.random() * (container.getWidth() - width);
		y = Math.random() * (container.getHeight() - height);
	}
	
	public void setPositionCentered()
	{
		x = container.getWidth()/2 - width/2;
		y = container.getHeight()/2 - height/2;;
	}
	
	public double getTrueX()
	{
		if (container == null)
			return getX();
		else
			return x + container.getTrueX();
	}
	
	public double getTrueY()
	{
		if (container == null)
			return getY();
		else
			return y + container.getTrueY();
	}
	
	public double getTrueCenterX()
	{
		return getTrueX() + width/2;
	}
	
	public double getTrueCenterY()
	{
		return getTrueY() + height/2;
	}
	
	
	
	
	
	
	
	public double getRotation()
	{
		return rotation;
	}

	public void setRotation(double _rotation)
	{
		rotation = _rotation;
		rotationNormalize();
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
		return Math.PI - _rotation;
	}
	
	public double rotationFlip(double _rotation)
	{
		return _rotation + Math.PI;
	}
	
	public void rotationNormalize()
	{
		rotation = rotationNormalize(rotation);
	}
	
	public double rotationNormalize(double _rotation)
	{
		if (_rotation > 2 * Math.PI)
		{
			//System.err.print(">2PI " + _rotation + "\t");
			_rotation -= (int)(_rotation/(2 * Math.PI)) * 2 * Math.PI;
			//System.err.println(_rotation);
		}
		else if (_rotation < 0)
		{
			//System.err.print("<0   " + _rotation + "\t");
			_rotation -= ((int)(_rotation/(2 * Math.PI) - 1)) * 2 * Math.PI;
			//System.err.println(_rotation);
		}
		return _rotation;
	}
	
	public double rotationMake(double _x, double _y)
	{
		double lenght = Math.pow(_x*_x + _y*_y, 0.5);
		_x /= lenght;
		_y /= lenght;
		
		//System.err.println(_x + " " + _y);
		
		if (_x > 0)
		{
			//System.err.println("_x >  0: " + rotationNormalize(Math.asin(_y)));
			return rotationNormalize(Math.asin(_y));
		}
		else
		{
			//System.err.println("_x <= 0: " + rotationNormalize(rotationFlipX(Math.asin(_y))));
			return rotationNormalize(rotationFlipX(Math.asin(_y)));
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

	public double getVelocityMax() {
		return velocityMax;
	}

	public void setVelocityMax(double velocityMax) {
		this.velocityMax = velocityMax;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getFriction() {
		return friction;
	}

	public void setFriction(double friction) {
		this.friction = friction;
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
