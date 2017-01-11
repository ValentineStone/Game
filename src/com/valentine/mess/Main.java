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
			System.err.println(v);
		}
		
		System.err.println("Greedy cover:");
		
		GraphRuller.greedyCover(g, 1);
	}
}
