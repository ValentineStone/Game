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

	public Iterator<Vertex> iterator()
	{
		return vertices.iterator();
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
}