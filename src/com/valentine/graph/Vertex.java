package com.valentine.graph;

import java.util.*;

public class Vertex
{
	Graph graph;
	
	double weight;
	
	List<Edge> ins  = new ArrayList<>();
	List<Edge> outs = new ArrayList<>();
	
	
	Vertex(Graph _graph, double _weight)
	{
		graph  = _graph;
		weight = _weight;
	}
	
	public Iterable<Edge> getIns()
	{
		return ins;
	}
	
	public Iterable<Edge> getOuts()
	{
		return outs;
	}
	
	public double getWeight()
	{
		return weight;
	}
}