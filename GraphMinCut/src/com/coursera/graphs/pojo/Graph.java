package com.coursera.graphs.pojo;

import java.util.ArrayList;

public class Graph {

	private int vertices;
	private ArrayList<Edge> edgeList;

	public Graph(int vertices, ArrayList<Edge> edges) {
		this.vertices = vertices;
		this.edgeList = edges;
	}

	public int getVertices() {
		return vertices;
	}

	public void setVertices(int vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}

}
