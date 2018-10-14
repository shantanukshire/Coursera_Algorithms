package com.coursera.graphs.dijkstras;

import java.util.HashMap;
import java.util.Set;

import com.coursera.graphs.pojo.Graph;
import com.coursera.graphs.pojo.GraphNode;
import com.coursera.graphs.pojo.Heap;

public class Dijkstras {
	
	private static final int MAX_DIST = Integer.MAX_VALUE;
	
	public static int[] run(Graph graph, int src) {
		int vertices = graph.getVertices();
		HashMap<Integer,Set<GraphNode>> edgeList = graph.getAdjacencyList();
		
		int[] dist = new int[vertices];
		Heap heap = new Heap(vertices);
		
		for(int i=0; i<vertices; ++i) {
			GraphNode graphNode = new GraphNode(i,MAX_DIST);
			heap.setGraphNode(graphNode, i);
			heap.setPos(i, i);
			dist[i] = MAX_DIST;
		}
		dist[src] = 0;
		heap.decreaseKey(src, 0);
		
		while(!heap.isEmpty()) {
			GraphNode node = heap.extractMin();
			int v = node.getVertex();			
			Set<GraphNode> connectedNodes = edgeList.get(v);
			for(GraphNode u : connectedNodes) {
				if(heap.isInHeap(u.getVertex()) &&
						dist[u.getVertex()] > (dist[v]+u.getDist())) {
					dist[u.getVertex()] = dist[v] + u.getDist();
					heap.decreaseKey(u.getVertex(),dist[u.getVertex()]);
				}
			}
		}
		
		return dist;
	}

}
