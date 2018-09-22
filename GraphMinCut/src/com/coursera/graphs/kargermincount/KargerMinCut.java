package com.coursera.graphs.kargermincount;

import java.util.ArrayList;
import java.util.Random;

import com.coursera.graphs.pojo.Edge;
import com.coursera.graphs.pojo.Graph;

public class KargerMinCut {

	private static class Subset {
		private int parent;
		private int rank;

		public Subset() {

		}

		public Subset(int parent, int rank) {
			this.parent = parent;
			this.rank = rank;
		}

		public int getParent() {
			return parent;
		}

		public void setParent(int parent) {
			this.parent = parent;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

	}

	public static int calculateMinCut(Graph graph) {
		
		int minCut = 0;
		int vertices = graph.getVertices();
		ArrayList<Edge> edgeList = graph.getEdgeList();
		int edges = edgeList.size();
		
		Subset[] subsetArr = new Subset[vertices];
		for(int i=0; i<vertices; ++i) {
			subsetArr[i] = new Subset(i,0);
		}

		while (vertices > 2) {

			// Select Random Edge
			int edgeNo = getRandomValue(1, edges) - 1;
			Edge e = edgeList.get(edgeNo);

			// Get Sets
			int subsetOne = findParent(subsetArr,e.getSource());
			int subsetTwo = findParent(subsetArr,e.getDest());
			
			if(subsetOne == subsetTwo)
				continue;
			else {
				//Combine edge vertices
				union(subsetArr,subsetOne,subsetTwo);
				--vertices;
			}
		}
		
		for(int j=0; j<edges; ++j) {
			Edge e = edgeList.get(j);
			int subsetOne = findParent(subsetArr,e.getSource());
			int subsetTwo = findParent(subsetArr,e.getDest());
			
			if(subsetOne != subsetTwo){
				minCut++;
			}
		}
		
		return minCut;

	}

	public static int getRandomValue(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}
	
	public static int findParent(Subset[] subsetArr, int vtx) {
		if(subsetArr[vtx].getParent() != vtx) {
			int parent = findParent(subsetArr,subsetArr[vtx].getParent());
			subsetArr[vtx].setParent(parent);
		}
		return subsetArr[vtx].getParent();
	}
	
	public static void union(Subset[] subsetArr, int u, int v) {
		int uRoot = findParent(subsetArr,u);
		int vRoot = findParent(subsetArr,v);
		
		if(subsetArr[uRoot].getRank() > subsetArr[vRoot].getRank()) {
			subsetArr[vRoot].setParent(uRoot);
		}else if(subsetArr[uRoot].getRank() < subsetArr[vRoot].getRank()) {
			subsetArr[uRoot].setParent(vRoot);
		}else {
			subsetArr[vRoot].setParent(uRoot);
			int rank = subsetArr[uRoot].getRank();
			subsetArr[uRoot].setRank(++rank);
		}
	}
}
