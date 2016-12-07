package com.valentine.game.entity.landandwheather;

import java.util.*;

import com.valentine.game.utils.*;

public class Mesh3d
{
	private final List<Dot3d> dots = new ArrayList<>();
	private final List<Tri3d> tris = new ArrayList<>();
	
	public Mesh3d(List<Dot3d> _dots)
	{
		if (_dots != null)
		{
			dots.addAll(_dots);
			makeTris();
		}
	}
	
	public void makeTris()
	{
		int dotcount = dots.size();
		
		double[][] distances = new double[dotcount-1][];
		
		for (int i = 0; i < dotcount-1; i++)
		{	
			distances[i] = new double[dotcount - i];
			
			for (int j = i+1; j < dotcount; j++)
				distances[i][j-i] = MathExt.distanceMake(dots.get(i).x, dots.get(i).y, dots.get(j).x, dots.get(j).y);
		}
		
		System.err.println(dots.toString());
		
		for (double[] line : distances)
		{
			System.err.println(Arrays.toString(line));
		}
	}
	
	public static void main(String[] _args)
	{
		new Mesh3d(Arrays.asList(new Dot3d[]{new Dot3d(0,0,1), new Dot3d(0,1,1), new Dot3d(1,0,1), new Dot3d(1,1,1)}));
	}
	
	
	
	
}
