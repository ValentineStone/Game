package com.valentine.graph;

import java.io.*;
import java.util.*;
import java.util.Map.*;

import com.google.gson.*;

public class Graph implements Iterable<Vertex>
{
	List<Vertex> vertices = new ArrayList<>();
	
	public Graph()
	{}

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
	
	public int vertexCount()
	{
		return vertices.size();
	}
	
	public boolean addVertex(double _weight)
	{
		return vertices.add(new Vertex(this, vertices.size(), _weight));
	}
	
	public void addVertices(int _count, double _weight)
	{
		for (int i = 0; i < _count; i++)
			addVertex(_weight);
	}

	public Iterator<Vertex> iterator()
	{
		return vertices.iterator();
	}
	
	public String toJson()
	{
		StringBuilder stringBuilder =
			new StringBuilder();
		
		stringBuilder.append("[");
		
		for (int i= 0; i < vertexCount(); i++)
			stringBuilder
				.append(i == 0 ? "\n\t" : ",\n\t")
				.append(get(i).toJson());
		
		stringBuilder.append("\n]");
		
		return stringBuilder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Graph(File _jsonFile)
	{
		Gson gson = new Gson();
		Reader reader;
		try
		{
			reader = new FileReader(_jsonFile);
			VertexJsonData[] data = gson.fromJson(reader, VertexJsonData[].class);
			
			for (int i = 0; i < data.length; i++)
				addVertex(data[i].w);
			
			for (int i = 0; i < data.length; i++)
				for (Entry<Integer, Double> es : data[i].e.entrySet())
					addEdge(i, es.getKey(), es.getValue());
		}
		catch (FileNotFoundException _e)
		{
			return;
		}
	}
	
	private static class VertexJsonData
	{
		double w;
		Map<Integer, Double> e;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Graph makeBadExample(int _power, boolean _connection)
	{
		Graph g = new Graph();
		
		if (_power <= 0)
			return g;
		
		g.addVertex(1);
		g.addVertex(1);
		
		if (_connection)
		{
			g.addEdge(0, 1, 1);
			g.addEdge(1, 0, 1);
		}
		
		if (_power == 1)
			return g;
		
		int index = 2;
		int power = 2;
		int count = 0;
		
		while (power <= _power)
		{
			count = (int) Math.pow(2, power);
			
			for (int i = 0; i < count; i++)
			{
				g.addVertex(1);
				g.addEdge(index + i, i % 2, 2);
				g.addEdge(i % 2, index + i, 1);
			}
			
			for (int from = 0; from < count; from++)
			for (int to   = 0; to   < count; to++  )
				if (from != to)
					g.addEdge(index + from, index + to, 1);
			
			index += count;
			power++;
		}
		
		return g;
	}
	
	public static Graph makeRealBadExample(int _count)
	{
		Graph g = new Graph();
		
		g.addVertex(1);
		
		g.addVertices(3 * _count, 1);
		
		for (int i = 1; i <= _count; i++)
		{
			g.addEdge(i, 0, 1);
			g.addEdge(0, i, 1);
			
			g.addEdge(i, _count + i, 1);
			g.addEdge(0, _count + i, 1);
			
			g.addEdge(i, 2 * _count + i, 1);
		}
		/*
		g.addVertex(1);
		g.addVertex(1);
		
		g.addEdge(1, 0, 1);
		g.addEdge(2, 0, 1);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 1);
		
		g.addVertex(1);
		g.addVertex(1);
		
		g.addEdge(1, 3, 1);
		g.addEdge(2, 4, 1);
		
		for (int i = 0; i < _count; i++)
		{
			g.addVertex(1);
			
			g.addEdge(1, 5+2*i, 1);
			g.addEdge(0, 5+2*i, 1);
			
			g.addVertex(1);
			
			g.addEdge(2, 6+2*i, 1);
			g.addEdge(0, 6+2*i, 1);
		}
		*/
		return g;
	}
}