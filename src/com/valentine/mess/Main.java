package com.valentine.mess;

import java.util.*;
import java.util.function.*;

import javax.swing.*;

import com.valentine.game.utils.*;
import com.valentine.graph.*;

public class Main
{
	public static void main(String[] args)
	{
		//File jsonFile = new File(JOptionPane.showInputDialog("File:?", "res/graph_BAD.json"));
		
		//Graph g = new Graph(jsonFile);
		
		Graph g = Graph.makeRealBadExample(2);
		
		/*
		g.addVertex(1);
		g.addVertex(1);
		g.addVertex(1);
		
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 1);
		g.addEdge(2, 0, 1);
		*/
		
		System.err.println("/-----------------------------------------------------------------------------\\");
		System.err.println("|  Graph ");// + jsonFile.getName() + " :");
		System.err.println("\\-----------------------------------------------------------------------------/");
		
		System.err.println(g.toJson());
		
		double distance = Double.valueOf(JOptionPane.showInputDialog("Distance", "1"));
		
		System.err.println("/-----------------------------------------------------------------------------\\");
		System.err.println("|  Greedy cover with distance " + distance + " :");
		System.err.println("\\-----------------------------------------------------------------------------/");
		
		SupplierRunnable<List<Vertex>> supp = new SupplierRunnable<List<Vertex>>(() -> GraphRuller.greedyCoverPenetrating(g, distance, 3)); // Length of maximum path in graph
		
		System.err.println("Took " + (ProcExt.measureExecutionTimeNanos(supp) / 1000000.) + " miliseconds:");
		
		for (Vertex v : supp.getLastResult())
			System.err.println(v);
		
		
	}
}

class SupplierRunnable<T> implements Runnable, Supplier<T>
{
	Supplier<T> supp;
	T lastResult = null;
	
	public SupplierRunnable(Supplier<T> _supp)
	{
		supp = _supp;
	}
	
	public void run()
	{
		lastResult = supp.get();
	}

	public T get()
	{
		return supp.get();
	}
	
	public T getLastResult()
	{
		return lastResult;
	}
}