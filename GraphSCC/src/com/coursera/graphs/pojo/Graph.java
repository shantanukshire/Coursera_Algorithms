package com.coursera.graphs.pojo;

import java.util.HashMap;
import java.util.Set;

public class Graph {

	private int vertices;
	private int edges;
	private HashMap<Integer, Set<Integer>> adjacencyList;

	private Graph() {

	}

	public Graph(int vertices, int edges, HashMap<Integer, Set<Integer>> adjacencyList) {
		super();
		this.vertices = vertices;
		this.edges = edges;
		this.adjacencyList = adjacencyList;
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

	public HashMap<Integer, Set<Integer>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(HashMap<Integer, Set<Integer>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

}
