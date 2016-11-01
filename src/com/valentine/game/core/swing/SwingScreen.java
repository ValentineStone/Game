package com.valentine.game.core.swing;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.text.AttributedCharacterIterator;

import javax.swing.*;

import com.valentine.game.core.*;
import com.valentine.game.core.basic.*;

public abstract class SwingScreen implements Paintable
{
	private Graphics2D graphics;
	
	private Font font;
	
	private Paintable paintable;
	
	Window window;
	
	public Paintable getPaintable()
	{
		return paintable;
	}

	public void setPaintable(Paintable _paintable)
	{
		paintable = _paintable;
	}

	public void init()
	
	{
		System.err.println("[Screen]");
		
		try
		{
			GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.registerFont
			(
				Font.createFont(Font.TRUETYPE_FONT, new File("res/PressStart2P.ttf"))
			);
		}
		catch (FontFormatException _exception)
		{
			_exception.printStackTrace();
		}
		catch (IOException _exception)
		{
			_exception.printStackTrace();
		}
		
		font = new Font("Press Start 2P", Font.PLAIN, 12);
		
		
		
	}
	
	public void setGraphics(Graphics2D _graphics)
	{
		graphics = _graphics;
		
		graphics.setFont(font);
		
		/*
		graphics.setRenderingHint
		(
	        RenderingHints.KEY_TEXT_ANTIALIASING,
	        RenderingHints.VALUE_TEXT_ANTIALIAS_ON
	    );
		
		graphics.setRenderingHint
		(
	        RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON
	    );
		*/
	}
	
	
	
	
	
	public void localize(double _x, double _y)
	{
		translate(_x, _y);
	}
	
	public void delocalize(double _x, double _y)
	{
		translate(-_x, -_y);
	}
	

	public void clearRect(double _x, double _y, double _width, double _height)
	{
		graphics.clearRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public void clipRect(double _x, double _y, double _width, double _height)
	{
		graphics.clipRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public void copyArea(double _x, double _y, double _width, double _height, double _dx, double _dy)
	{
		graphics.copyArea((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_dx), (int)Math.round(_dy));
	}

	public void dispose()
	{	
		graphics.dispose();
	}

	public void drawArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{	
		graphics.drawArc((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_startAngle), (int)Math.round(_arcAngle));
	}

	public boolean drawImage(Image _img, double _x, double _y, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), _observer);
	}

	public boolean drawImage(Image _img, double _x, double _y, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), _bgcolor, _observer);
	}

	public boolean drawImage(Image _img, double _x, double _y, double _width, double _height, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), _observer);
	}

	public boolean drawImage(Image _img, double _x, double _y, double _width, double _height, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), _bgcolor, _observer);
	}

	public boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, ImageObserver _observer)
	{
		return graphics.drawImage(_img, (int)Math.round(_dx1), (int)Math.round(_dy1), (int)Math.round(_dx2), (int)Math.round(_dy2), (int)Math.round(_sx1), (int)Math.round(_sy1), (int)Math.round(_sx2), (int)Math.round(_sy2), _observer);
	}

	public boolean drawImage(Image _img, double _dx1, double _dy1, double _dx2, double _dy2, double _sx1, double _sy1, double _sx2, double _sy2, Color _bgcolor, ImageObserver _observer)
	{
		return graphics.drawImage( _img, (int)Math.round(_dx1), (int)Math.round(_dy1), (int)Math.round(_dx2), (int)Math.round(_dy2), (int)Math.round(_sx1), (int)Math.round(_sy1), (int)Math.round(_sx2), (int)Math.round(_sy2), _bgcolor, _observer);
	}

	public void drawLine(double _x1, double _y1, double _x2, double _y2)
	{
		graphics.drawLine((int)Math.round(_x1), (int)Math.round(_y1), (int)Math.round(_x2), (int)Math.round(_y2));
	}

	public void drawOval(double _x, double _y, double _width, double _height)
	{
		graphics.drawOval((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public void drawRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.drawRoundRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_arcWidth), (int)Math.round(_arcHeight));
	}
	
	public void drawRect(double _x, double _y, double _width, double _height)
	{
		graphics.drawRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public void drawString(String _str, double _x, double _y)
	{
		graphics.drawString(_str, (int)Math.round(_x), (int)Math.round(_y));
	}

	public void drawString(AttributedCharacterIterator _iterator, double _x, double _y)
	{
		graphics.drawString(_iterator, (int)Math.round(_x), (int)Math.round(_y));
	}

	public void fillArc(double _x, double _y, double _width, double _height, double _startAngle, double _arcAngle)
	{
		graphics.fillArc((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_startAngle), (int)Math.round(_arcAngle));
	}

	public void fillOval(double _x, double _y, double _width, double _height)
	{
		graphics.fillOval((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public void fillRect(double _x, double _y, double _width, double _height)
	{
		graphics.fillRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public void fillRoundRect(double _x, double _y, double _width, double _height, double _arcWidth, double _arcHeight)
	{
		graphics.fillRoundRect((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height), (int)Math.round(_arcWidth), (int)Math.round(_arcHeight));
	}

	public Shape getClip()
	{
		return graphics.getClip();
	}

	public Rectangle getClipBounds()
	{
		return graphics.getClipBounds();
	}

	public Color getColor()
	{
		return graphics.getColor();
	}

	public Font getFont()
	{
		return font;
	}

	public FontMetrics getFontMetrics(Font _f)
	{
		return graphics.getFontMetrics(_f);
	}

	public void setClip(Shape _clip)
	{
		graphics.setClip(_clip);
	}

	public void setClip(double _x, double _y, double _width, double _height)
	{
		graphics.setClip((int)Math.round(_x), (int)Math.round(_y), (int)Math.round(_width), (int)Math.round(_height));
	}

	public void setColor(Color _c)
	{
		graphics.setColor(_c);
	}

	public void setFont(Font _font)
	{
		graphics.setFont(_font);
	}
	
	public void resetFont()
	{
		graphics.setFont(font);
	}

	public void setPaintMode()
	{
		graphics.setPaintMode();
	}

	public void setXORMode(Color _c1)
	{
		graphics.setXORMode(_c1);
	}

	public void translate(double _x, double _y)
	{
		graphics.translate(Math.round(_x), Math.round(_y));
	}

	public Graphics2D getGraphics()
	{
		return graphics;
	}
	
	public void setStroke(Stroke _stroke)
	{
		graphics.setStroke(_stroke);
	}

	public void setStaticRedirect(Screen _screen)
	{}
	
	Dimension getCreenSize()
	{
		return window.getFullScreenSize();
	}
}





class Window
{
	private JFrame jframe;
	private Canvas canvas;
	
	private boolean isFullscreen;
	
	private Dimension defaultDimension;
	
	private Paintable paintable;
	
	private String title = "";
	
	public final static Dimension DEFAULT_DIMESION = new Dimension(1280, 720);
	
	public Window(Paintable _paintable, Dimension _defaultDimension, boolean _isFullscreen)
	{
		paintable = _paintable;
		defaultDimension = _defaultDimension;
		isFullscreen = _isFullscreen;
		
		createWindow();
	}
	
	public Window(Paintable _paintable, Dimension _defaultDimension)
	{
		this(_paintable, _defaultDimension, false);
	}
	
	public Window(Paintable _paintable)
	{
		this(_paintable, DEFAULT_DIMESION, true);
	}
	
	private void createWindow()
	{
		
		jframe = new JFrame();
		canvas = new Canvas()
		{
			private static final long serialVersionUID = -4962224423650843597L;
			
			BufferStrategy bufferStrategy = null;
			Graphics2D graphics2D;

			@Override
			public void repaint()
			{
				if (bufferStrategy == null)
				{
					createBufferStrategy(3);
					bufferStrategy = canvas.getBufferStrategy();
				}
				
				graphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
				
				System.err.println("Static call of Screen is no longer supported");
				//Screen.setGraphics(graphics2D);
				
				paintable.paint();
				
				bufferStrategy.show();
				
				graphics2D.dispose();
			}
		};
		
		jframe.setTitle(title);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.add(canvas);
		
		jframe.setFocusable(false);
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		canvas.addKeyListener(Input.instance());
		canvas.addComponentListener(Input.instance());
		canvas.addMouseListener(Input.instance());
		canvas.addMouseMotionListener(Input.instance());
		canvas.addMouseWheelListener(Input.instance());
		
		if (isFullscreen)
		{
			jframe.setUndecorated(true);
			jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
			GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.setFullScreenWindow(jframe);
		}
		else
		{
			canvas.setPreferredSize(defaultDimension);
			jframe.pack();
		}
		
		jframe.setVisible(true);
	}


	public Dimension getFullScreenSize()
	{
		DisplayMode displayMode =
			GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDisplayMode();
		
		return new Dimension(displayMode.getWidth(), displayMode.getHeight());
	}
	
	public void repaint()
	{
		canvas.repaint();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String _title)
	{
		title = _title;
	}
}
