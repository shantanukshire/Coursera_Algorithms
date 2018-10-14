package com.coursera.graphs.pojo;

public class GraphNode {

	int vertex;
	int dist;

	public GraphNode() {

	}

	public GraphNode(int vertex, int dist) {
		this.vertex = vertex;
		this.dist = dist;
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	@Override
	public int hashCode() {
		return this.getVertex()%23;
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		
		if(obj.getClass().isInstance(GraphNode.class)) {
			GraphNode diffObj = (GraphNode) obj;
			if(this.getVertex() == diffObj.getVertex() && this.getDist() == diffObj.getDist())
				ret = true;
		}
		
		return ret;
	}
	
	

}
