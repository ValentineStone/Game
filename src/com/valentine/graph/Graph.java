package com.valentine.graph;

import java.io.*;
import java.util.*;
import java.util.Map.*;

import com.google.gson.*;

public class Graph implements Iterable<Vertex>
{
	public static void main(String[] _args)
	{
		Graph g = new Graph(new File("res/graph.json"));
		
		for (Vertex v : g)
		{
			System.err.print(v.weight + " ");
			
			for (Edge e : v.getOuts())
				System.err.print("{" + e.getTo() + ":" + e.getWeight() + "} ");
			System.err.println();
		}
	}
	
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
	
	public boolean addVertex(double _weight)
	{
		return vertices.add(new Vertex(this, _weight));
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
			GraphJsonData data = gson.fromJson(reader, GraphJsonData.class);
			
			for (int i = 0; i < data.v.size(); i++)
				addVertex(data.v.get(i).w);
			
			for (int i = 0; i < data.v.size(); i++)
				for (Entry<Integer, Double> es : data.v.get(i).e.entrySet())
					addEdge(i, es.getKey(), es.getValue());
		}
		catch (FileNotFoundException _e)
		{
			return;
		}
	}
	
	private static class GraphJsonData
	{
		int s;
		List<VertexJsonData> v;
		
		private static class VertexJsonData
		{
			double w;
			Map<Integer, Double> e;
			
			public String toString()
			{
				StringBuilder stringBuilder
					= new StringBuilder();
				
				stringBuilder
					.append("VertexJsonData(w:")
					.append(w)
					.append(")[");
				
				for (Entry<Integer, Double> ed : e.entrySet())
					stringBuilder
						.append("{to:")
						.append(ed.getKey())
						.append(", w:")
						.append(ed.getValue())
						.append('}');
				
				stringBuilder
					.append(']');
				
				return stringBuilder.toString();
			}
		}

		public String toString()
		{
			StringBuilder stringBuilder
				= new StringBuilder();
			
			stringBuilder
				.append("GraphJsonData($")
				.append(s)
				.append(")[\n");
			
			for (int i = 0; i < v.size(); i++)
				stringBuilder
					.append('\t')
					.append(i)
					.append(" : ")
					.append(v.get(i))
					.append('\n');
			
			return stringBuilder.toString();
		}
	}
}