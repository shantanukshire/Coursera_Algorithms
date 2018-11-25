package com.coursera.primsmst.pojo;

public class GraphNode {

	private int vertexId;
	private int distance;

	public GraphNode() {

	}

	public GraphNode(int id, int dist) {
		this.vertexId = id;
		this.distance = dist;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getVertexId() {
		return vertexId;
	}

	public void setVertexId(int vertexId) {
		this.vertexId = vertexId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return vertexId % prime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphNode other = (GraphNode) obj;
		if (distance != other.distance)
			return false;
		if (vertexId != other.vertexId)
			return false;
		return true;
	}

}
