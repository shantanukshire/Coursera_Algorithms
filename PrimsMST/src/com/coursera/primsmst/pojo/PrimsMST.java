package com.coursera.primsmst.pojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PrimsMST {
	
	public static int[] primsMST(Graph graph, int vertices) {
		int[] parent = new int[vertices];
		int[] key = new int[vertices];
		boolean[] mstSet = new boolean[vertices];
		MinHeap heap = new MinHeap(vertices);
		HashMap<Integer,Set<GraphNode>> adjList = graph.getAdjacencyList();
		
		//Initialize Heap & other arrays
		for(int i=0; i<vertices; ++i) {
			GraphNode node = new GraphNode(i,Integer.MAX_VALUE);
			mstSet[i] = false;
			parent[i] = -1;
			key[i] = Integer.MAX_VALUE;
			heap.insert(node);
		}
		
		//Initialize Source Vertex
		heap.decreaseKey(0, 0);
		key[0] = 0;
		
		//Prims Algorithm
		while(!heap.isEmpty()) {
			GraphNode uNode = heap.extractMin();
			int u = uNode.getVertexId();
			mstSet[u] = true;
			
			Set<GraphNode> connectedNodes = adjList.get(u);
			if(connectedNodes!= null && !connectedNodes.isEmpty()) {
				for(GraphNode vNode : connectedNodes) {
					int v = vNode.getVertexId();
					int new_weight = vNode.getDistance();
					if(!mstSet[v] &&
							key[v] > new_weight) {
						key[v] = new_weight;
						parent[v] = u;
						heap.delete(v);
						heap.insert(vNode);
					}
				}
			}
		}
		
		return parent;
	}
	
	public static int getMSTCost(Graph graph, int[][] adjMatrix) {
		int min_cost = 0;
		int[] parent;
		
		if(graph != null) {
			parent = primsMST(graph,graph.getVertices());
			for(int i=1; i<graph.getVertices(); ++i) {
				min_cost += adjMatrix[parent[i]][i];
			}
		}
		
		return min_cost;
	}

}
