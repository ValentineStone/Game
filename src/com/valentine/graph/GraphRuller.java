package com.valentine.graph;

import java.util.*;

public class GraphRuller
{
	public static double[][] measureDistances(Graph _graph)
	{
		double[][] distances = new double[_graph.vertexCount()][_graph.vertexCount()];
		
		for (int i = 0; i < distances.length; i++)
			Arrays.fill(distances[i], Double.POSITIVE_INFINITY);
		
		for (Vertex v  : _graph)
		for (Edge eOut : v.getOuts())
			distances[v.getIndex()][eOut.to().getIndex()] = eOut.getWeight();
		
		for (int i = 0; i < distances.length; i++)
			distances[i][i] = 0;
		
		for (int v = 0; v < _graph.vertexCount(); v++)
		for (int vIn = 0; vIn < _graph.vertexCount(); vIn++)
		for (int vOut = 0; vOut < _graph.vertexCount(); vOut++)
			distances[vIn][vOut] = 
				Math.min
				(
						distances[vIn][vOut],
						distances[vIn][v]
							+ distances[v][vOut]
				);
		
		/*
		for (int from = 0; from < _graph.vertexCount(); from++)
		for (int to   = 0; to   < _graph.vertexCount(); to++  )
			System.err.println(from + " -> " + to + " : " + distances[from][to]);
		*/
		
		return distances;
	}
	
	public static List<Vertex> greedyCover(Graph _graph, double _maxDist)
	{
		List<Vertex> vertices = new ArrayList<>();
		
		double[][] distances = measureDistances(_graph);
		
		boolean[][] coverage = new boolean[distances.length][distances.length];
		boolean[]   covered  = new boolean[distances.length];
		int[]       covering = new int[distances.length];
		
		for (int from = 0; from < _graph.vertexCount(); from++)
		for (int to   = 0; to   < _graph.vertexCount(); to++  )
			if (distances[from][to] <= _maxDist)
			{
				coverage[from][to] = true;
				covering[from]++;
			}
		
		int maxCovering = 0;
		int maxCoveringIndex = -1;
		
		do
		{
			if (maxCoveringIndex != -1)
				coverage[maxCoveringIndex] = new boolean[0];
			
			maxCovering      = 0;
			maxCoveringIndex = -1;
			
			for (int i = 0; i < coverage.length; i++)
			{
				covering[i] = _usefulCoveringCount(covered, coverage[i]);
				if (maxCovering < covering[i])
				{
					maxCovering      = covering[i];
					maxCoveringIndex = i;
				}
			}
			
			vertices.add(_graph.get(maxCoveringIndex));
		}
		while (_addAndCheck(covered, coverage[maxCoveringIndex]));
		
		for (Vertex v : vertices)
			System.err.println(v);
		
		return vertices;
	}
	
	private static int _usefulCoveringCount(boolean[] _covered, boolean[] _covering)
	{
		int count = 0;
		for (int i = 0; i < _covering.length; i++)
			if (_covering[i] && !_covered[i]) count++;
		return count;
	}
	
	private static boolean _addAndCheck(boolean[] _covered, boolean[] _covering)
	{
		boolean isAllCovered = true;
		for (int i = 0; i < _covering.length; i++)
		{
			if (_covering[i])
				_covered[i] = true;
			
			isAllCovered &= _covered[i];
		}
		return !isAllCovered;
	}
	
	@Deprecated
	public static List<Vertex> greedyCoverDirty(Graph _graph, double _maxDist)
	{
		List<Vertex> vertices = new ArrayList<>();
		
		double[][] distances = measureDistances(_graph);
		
		boolean[][] coverage = new boolean[distances.length][distances.length];
		boolean[]   covered  = new boolean[distances.length];
		int[]       covering = new int[distances.length];
		
		int maxCovering      = 0;
		int maxCoveringIndex = 0;
		
		int currentMaxCovering      = 0;
		int currentMaxCoveringIndex = 0;
		
		for (int from = 0; from < _graph.vertexCount(); from++)
		{
			for (int to   = 0; to   < _graph.vertexCount(); to++  )
				if (distances[from][to] <= _maxDist)
				{
					coverage[from][to] = true;
					covering[from]++;
				}
			if (maxCovering < covering[from])
			{
				maxCovering      = covering[from];
				maxCoveringIndex = from;
			}
		}
		
		currentMaxCovering      = maxCovering;
		currentMaxCoveringIndex = maxCoveringIndex;

		_addAndCount(covered, coverage[currentMaxCoveringIndex]);
		vertices.add(_graph.get(currentMaxCoveringIndex));
		
		while (!_isAllTrue(covered))
		{
			maxCovering = 0;
			
			for (int from = 0; from < _graph.vertexCount(); from++)
			{
				boolean isTheCoverer = false;
				for (Vertex v : vertices)
					if (v.getIndex() == from)
					{
						isTheCoverer = true;
						continue;
					}
				if (isTheCoverer) continue;
				
				covering[from] -= _sub(coverage[from], coverage[currentMaxCoveringIndex]);
				
				for (int i = 0; i < coverage.length; i++)
					if (maxCovering < covering[from])
					{
						maxCovering      = covering[from];
						maxCoveringIndex = from;
					}
			}
			
			currentMaxCovering      = maxCovering;
			currentMaxCoveringIndex = maxCoveringIndex;
			
			_addAndCount(covered, coverage[currentMaxCoveringIndex]);
			vertices.add(_graph.get(currentMaxCoveringIndex));
		}
		
		for (Vertex v : vertices)
			System.err.println(v);
		
		return vertices;
	}
	
	private static int _sub(boolean[] _from, boolean[] _what)
	{
		int count = 0;
		for (int i = 0; i < _from.length; i++)
			if (_what[i])
			{
				if (_from[i]) count++;
				_from[i] = false;
			}
		return count;
	}
	
	private static int _addAndCount(boolean[] _to, boolean[] _what)
	{
		int count = 0;
		for (int i = 0; i < _to.length; i++)
			if (_what[i])
			{
				if (!_to[i]) count++;
				_to[i] = true;
			}
		return count;
	}
	
	private static boolean _isAllTrue(boolean[] _what)
	{
		boolean isAllTrue = true;
		for (boolean b : _what)
		{
			isAllTrue &= b;
			if (!isAllTrue) break;
		}
		return isAllTrue;
	}
	
	private static String _toString(boolean[] _arr)
	{
		StringBuilder stringBuilder = new StringBuilder(_arr[0] ? '+' : '-');
		
		for (int i = 1; i < _arr.length; i++)
			stringBuilder
				.append(_arr[i] ? " +" : " -");
		
		return stringBuilder.toString();
	}
}
