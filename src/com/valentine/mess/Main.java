package com.valentine.mess;

import java.io.*;

import com.valentine.graph.*;

public class Main
{
	public static void main(String[] args)
	{
		Graph g = new Graph(new File("res/graph.json"));
		
		for (Vertex v : g)
		{
			System.err.print(v.getWeight() + " ");
			
			for (Edge e : v.getOuts())
				System.err.print("{" + e.getTo() + ":" + e.getWeight() + "} ");
			System.err.println();
		}
	}
}
