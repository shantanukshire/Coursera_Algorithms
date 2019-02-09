package com.coursera.clustering.pojo;

public class DisjointSet {
	
	private int groups;
	private int[] parent;
	
	public DisjointSet(int items) {
		this.groups = items;
		this.parent = new int[items];
		for(int i=0; i<parent.length; ++i) {
			parent[i] = i;
		}
	}
	
	public int getTotalGroups() {
		return groups;
	}
	
	public int find(int x) {
		while(parent[x] != x) {
			x = parent[x];
		}
		return x;	
	}
	
	public void Union(int x, int y) {
		int xPar = find(x);
		int yPar = find(y);
		
		parent[xPar] = yPar;
		--groups;
	}
	
	public void printParent() {
		System.out.print("Parent: ");
		for(int i=0; i<parent.length; ++i) {
			System.out.print(parent[i] + " ");
		}
		System.out.println("");
	}

}
