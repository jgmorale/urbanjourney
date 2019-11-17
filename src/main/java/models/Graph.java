package models;
import java.util.List;
import java.util.ArrayList;

// class to represent a graph object
public class Graph {
	static class Edge{
		int src, dest, weight;
		
		Edge(int src, int dest, int weight){
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	};
	
	// data structure for adjacency list node
	static class Node {
		int value, weight;
		
		Node(int value, int weight){
			this.value = value;
			this.weight = weight;
		}
	};
	
	// A list of lists to represent adjacency list
	List<List<Node>> adj = new ArrayList<List<Node>>();
	
	// Constructor to construct graph
	public Graph(List<Edge> edges) {
		// allocate memory for adjacency list
		for(int i = 0; i < edges.size(); i++) {
			adj.add(i, new ArrayList<Node>());
		}
		
		// add edges to the undirected graph
		for(Edge e: edges) {
			// allocate new node in adjacency List from src to dest
			adj.get(e.src).add(new Node(e.dest,e.weight));
		}
	}
	
	// print adjacency list representation of graph
	private static void printGraph(Graph graph) {
		int src = 0;
		int n = graph.adj.size();
		
		while(src < n) {
			// print current vertex and all its neighboring vertices
			for(Node edge: graph.adj.get(src)) {
				System.out.println(src + " --> " + edge.value + " (" + edge.weight +  ")\t");
			}
			
			System.out.println();
			src++;
		}
	}
	
}
