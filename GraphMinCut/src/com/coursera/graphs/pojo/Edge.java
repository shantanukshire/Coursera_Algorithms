package com.coursera.graphs.pojo;

public class Edge {

	int source;
	int dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	@Override
	public int hashCode() {
		int magicNumber = 29;
		return (this.source+this.dest)%magicNumber;
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		
		if(obj instanceof Edge) {
			Edge diffEdge = (Edge) obj;
			int diffSource = diffEdge.getSource();
			int diffDest = diffEdge.getDest();
			if((this.source == diffSource && this.dest == diffDest)||
					(this.source == diffDest && this.dest == diffSource))
				ret = true;
		}
		
		return ret;
	}
	
	

}
