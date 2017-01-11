package com.valentine.graph;

import java.util.*;

public class Vertex
{
	Graph graph;
	
	int index;
	
	double weight;
	
	List<Edge> ins  = new ArrayList<>();
	List<Edge> outs = new ArrayList<>();
	
	
	Vertex(Graph _graph, int _index, double _weight)
	{
		graph  = _graph;
		index  = _index;
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

	public int getIndex()
	{
		return index;
	}
	
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder
			.append("Vertex(#")
			.append(index)
			.append(", w:")
			.append(getWeight())
			.append(")[");
		
		for (Edge e : outs)
			stringBuilder
				.append('{')
				.append(e.getTo())
				.append(':')
				.append(e.getWeight())
				.append('}');
		
		stringBuilder
			.append(']');
		
		return stringBuilder.toString();
	}
}