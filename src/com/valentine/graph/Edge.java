package com.valentine.graph;

public class Edge
{
	Graph graph;
	
	int from;
	int to;
	double weight;
	
	Edge(Graph _graph, int _from, int _to, double _weight)
	{
		graph  = _graph;
		from   = _from;
		to     = _to;
		weight = _weight;
	}
	
	public Vertex getTo()
	{
		return graph.vertices.get(to);
	}
	
	public Vertex getFrom()
	{
		return graph.vertices.get(from);
	}
	
	public boolean isLoop()
	{
		return from == to;
	}
	
	public double getWeight()
	{
		return weight;
	}
}