package com.coursera.primsmst.pojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph {

	private int vertices;
	private int edges;
	private HashMap<Integer, Set<GraphNode>> adjacencyList;

	public Graph(int vertices, int edges) {
		this.vertices = vertices;
		this.edges = edges;
		adjacencyList = new HashMap<Integer, Set<GraphNode>>();
	}

	public void addEdge(int u, int v, int weight) {
		if (adjacencyList != null) {
			GraphNode graphNode = new GraphNode(v, weight);
			Set<GraphNode> nodeSet = null;

			if (adjacencyList.get(u) != null) {
				nodeSet = adjacencyList.get(u);
			} else {
				nodeSet = new HashSet<GraphNode>();
			}
			nodeSet.add(graphNode);

			adjacencyList.put(u, nodeSet);

		}
	}

	public int getVertices() {
		return vertices;
	}

	public void setVertices(int vertices) {
		this.vertices = vertices;
	}

	public int getEdges() {
		return edges;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}

	public HashMap<Integer, Set<GraphNode>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(HashMap<Integer, Set<GraphNode>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

}
