package com.valentine.game.entity.landandwheather;

import java.awt.*;

public interface SurfaceScan
{
	public Double valueAt(double _x, double _y);
	public Dimension size();
}
