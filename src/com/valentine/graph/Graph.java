package com.valentine.graph;

import java.util.*;

public class Graph
{
	public static void main(String[] _args)
	{
		
	}
	
	List<Vertex> vertices = new ArrayList<>();
	
	public Graph()
	{
	}

	public boolean addEdge(int _from, int _to, double _weight)
	{
		if (!contains(_from) || !contains(_to))
			return false;
		
		Edge edge = new Edge(this, _from, _to, _weight);
		get(_from).outs.add(edge);
		get(_to)  .ins .add(edge);
		
		return true;
	}
	
	public Vertex get(int _index)
	{
		return vertices.get(_index);
	}
	
	public boolean contains(int _index)
	{
		return _index < vertices.size();
	}
	
	public boolean addVertex(double _weight)
	{
		return vertices.add(new Vertex(this, _weight));
	}
}