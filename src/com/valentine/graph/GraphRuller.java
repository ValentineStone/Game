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
		
		boolean[][] coverage    = new boolean[distances.length][distances.length];
		boolean[]   covered     = new boolean[distances.length];
		double[]    covering    = new double[distances.length];
		double[]    importances = new double[distances.length];
		
		for (int i = 0; i < importances.length; i++)
			importances[i] = _graph.get(i).getWeight();
		
		for (int from = 0; from < _graph.vertexCount(); from++)
		for (int to   = 0; to   < _graph.vertexCount(); to++  )
			if (distances[from][to] <= _maxDist)
			{
				coverage[from][to] = true;
				covering[from]++;
			}
		
		double maxCovering = 0;
		int maxCoveringIndex = -1;
		
		do
		{
			if (maxCoveringIndex != -1)
				coverage[maxCoveringIndex] = new boolean[0];
			
			maxCovering      = 0;
			maxCoveringIndex = -1;
			
			for (int i = 0; i < coverage.length; i++)
			{
				covering[i] = _usefulCoveringCount(covered, coverage[i], importances);
				if (maxCovering < covering[i])
				{
					maxCovering      = covering[i];
					maxCoveringIndex = i;
				}
			}
			
			System.err.println(Arrays.toString(covering));
			
			vertices.add(_graph.get(maxCoveringIndex));
		}
		while (_addAndCheck(covered, coverage[maxCoveringIndex]));
		
		return vertices;
	}
	
	private static int _usefulCoveringCount(boolean[] _covered, boolean[] _covering, double[] _importances)
	{
		int count = 0;
		for (int i = 0; i < _covering.length; i++)
			if (_covering[i] && !_covered[i]) count += _importances[i];
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Vertex> greedyCoverModifyed(Graph _graph, double _maxDist)
	{
		List<Vertex> vertices = new ArrayList<>();
		
		double[][] distances = measureDistances(_graph);
		
		boolean[][] coverage   = new boolean[distances.length][distances.length];
		boolean[]   covered    = new boolean[distances.length];
		double[]    covering   = new double[distances.length];
		int[]       coveredBy  = new int[distances.length];
		double[]    coverValue = new double[distances.length];
		
		for (int from = 0; from < _graph.vertexCount(); from++)
		for (int to   = 0; to   < _graph.vertexCount(); to++  )
			if (distances[from][to] <= _maxDist)
			{
				coverage[from][to] = true;
				covering[from]++;
				coveredBy[to]++;
			}
		
		for (int i = 0; i < coveredBy.length; i++)
			coverValue[i] = _graph.get(i).getWeight() / coveredBy[i];
		
		double maxCovering = 0;
		int maxCoveringIndex = -1;
		
		do
		{
			if (maxCoveringIndex != -1)
				coverage[maxCoveringIndex] = new boolean[0];
			
			maxCovering      = 0;
			maxCoveringIndex = -1;
			
			for (int i = 0; i < coverage.length; i++)
			{
				covering[i] = _usefulCoveringValue(covered, coverage[i], coverValue);
				if (maxCovering < covering[i])
				{
					maxCovering      = covering[i];
					maxCoveringIndex = i;
				}
			}
			
			vertices.add(_graph.get(maxCoveringIndex));
		}
		while (_addAndCheck(covered, coverage[maxCoveringIndex]));
		
		return vertices;
	}
	
	private static double _usefulCoveringValue(boolean[] _covered, boolean[] _covering, double[] _coverValue)
	{
		double value = 0;
		for (int i = 0; i < _covering.length; i++)
			if (_covering[i] && !_covered[i]) value += _coverValue[i];
		return value;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	public static List<Vertex> greedyCoverPenetrating(Graph _graph, double _maxDist, int _penetrationPower)
	{
		List<Vertex> vertices = new ArrayList<>();
		
		double[][] distances = measureDistances(_graph);
		
		boolean[][] coverage   = new boolean[distances.length][distances.length];
		boolean[]   covered    = new boolean[distances.length];
		double[]    coverValue;
		
		for (int from = 0; from < _graph.vertexCount(); from++)
		for (int to   = 0; to   < _graph.vertexCount(); to++  )
			if (distances[from][to] <= _maxDist)
			{
				coverage[from][to] = true;
			}
		
		coverValue = _generateCoverValue(coverage, _penetrationPower > 1 ? _penetrationPower : 1, _graph);
		
		double maxCovering = 0;
		int maxCoveringIndex = -1;
		
		do
		{
			if (maxCoveringIndex != -1)
				coverage[maxCoveringIndex] = new boolean[0];
			
			maxCovering      = 0;
			maxCoveringIndex = -1;
			
			for (int i = 0; i < coverage.length; i++)
			{
				if (coverage[i].length == 0)
					continue;
				
				if (maxCovering < coverValue[i])
				{
					maxCovering      = coverValue[i];
					maxCoveringIndex = i;
				}
			}
			
			vertices.add(_graph.get(maxCoveringIndex));
		}
		while (_addAndCheck(covered, coverage[maxCoveringIndex]));
		
		return vertices;
	}
	
	private static double[] _generateCoverValue(boolean[][] coverage, int _times, Graph _graph)
	{
		double[] coverValueEstablished = new double[coverage.length];
		for (int i = 0; i < coverValueEstablished.length; i++)
			coverValueEstablished[i] = _graph.get(i).getWeight();
		
		double[] coverValueCurrent = new double[coverage.length];
		Arrays.fill(coverValueCurrent, 1);
		
		for (int time = 0; time < _times; time++)
		{
			for (int i = 0; i < coverValueEstablished.length; i++)
				coverValueCurrent[i] /= _usefulCoveringValue(coverage, coverValueEstablished, i);
			
			for (int i = 0; i < coverValueEstablished.length; i++)
				coverValueEstablished[i] = coverValueCurrent[i];
		}
		
		System.err.println(Arrays.toString(coverValueEstablished));
		
		return coverValueEstablished;
	}
	
	private static double _usefulCoveringValue(boolean[][] _coverage, double[] _coverValue, int _index)
	{
		double value = 0;
		for (int i = 0; i < _coverage.length; i++)
			if (_coverage[i][_index]) value += _coverValue[i];
		return value;
	}
}
