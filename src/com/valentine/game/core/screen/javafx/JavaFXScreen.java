package com.valentine.game.core.screen.javafx;

import java.awt.*;
import java.awt.image.*;
import java.text.*;

import com.valentine.game.core.screen.Screen;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.stage.*;

public class JavaFXScreen implements Screen
{
	Application app;
	private GraphicsContext gc;
	
	public JavaFXScreen(Dimension _dimension)
	{
		app = new Application()
		{
			public void start(Stage _primaryStage) throws Exception
			{
				_primaryStage.setTitle("Drawing Operations Test");
		        Group root = new Group();
		        Canvas canvas = new Canvas(300, 250);
		        GraphicsContext gc = canvas.getGraphicsContext2D();
		        JavaFXScreen.this.gc = gc;
		        root.getChildren().add(canvas);
		        _primaryStage.setScene(new Scene(root));
		        _primaryStage.show();
			}
		}; 
	}
	

	
	private void setGraphicsContext(GraphicsContext _gc)
	{
		gc = _gc;
	}
	
	
	
	public void open()
	{}

	public void flush()
	{}

	public void localize(double _x, double _y)
	{}

	public void delocalize(double _x, double _y)
	{}

	public void clearRect(double _x, double _y, double _width, double _height)
	{}

	public void clipRect(double _x, double _y, double _width, double _height)
	{}

	public void copyArea(double _x, double _y, double _width, double _height, double _dx, double _dy)
	{}

	public void drawArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{}

	public void drawDot(double _x, double _y)
	{}

	public boolean drawImage(Image _img, double _x, double _y, ImageObserver _observer)
	{
		return false;
	}

	public boolean drawImage(Image _img, double _x, double _y, Color _bgcolor, ImageObserver _observer)
	{
		return false;
	}

	public boolean drawImage(Image _img, double _x, double _y, double _width, double _height, ImageObserver _observer)
	{
		return false;
	}

	public boolean drawImage(Image _img, double _x, double _y, double _width, double _height, Color _bgcolor, ImageObserver _observer)
	{
		return false;
	}

	public boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, ImageObserver _observer)
	{
		return false;
	}

	public boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, Color _bgcolor, ImageObserver _observer)
	{
		return false;
	}

	public void drawLine(double _x1, double _y1, double _x2, double _y2)
	{}

	public void drawOval(double _x, double _y, double _width, double _height)
	{}

	public void drawRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{}

	public void drawRect(double _x, double _y, double _width, double _height)
	{}

	public void drawString(String _str, double _x, double _y)
	{}

	public void drawString(AttributedCharacterIterator _iterator, double _x, double _y)
	{}

	public void fillArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{}

	public void fillOval(double _x, double _y, double _width, double _height)
	{}

	public void fillRect(double _x, double _y, double _width, double _height)
	{}

	public void fillRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{}

	public Shape getClip()
	{
		return null;
	}

	public Rectangle getClipBounds()
	{
		return null;
	}

	public Color getColor()
	{
		return null;
	}

	public Font getFont()
	{
		return null;
	}

	public FontMetrics getFontMetrics(Font _f)
	{
		return null;
	}

	public void setClip(Shape _clip)
	{}

	public void setClip(double _x, double _y, double _width, double _height)
	{}

	public void setColor(Color _c)
	{}

	public void setFont(Font _font)
	{}

	public void resetFont()
	{}

	public void setPaintMode()
	{}

	public void setXORMode(Color _c1)
	{}

	public void translate(double _x, double _y)
	{}

	public Graphics2D getGraphics()
	{
		return null;
	}

	public void setStroke(Stroke _stroke)
	{}

	public void resetStroke()
	{}

	public Dimension getScreenSize()
	{
		return null;
	}

}
