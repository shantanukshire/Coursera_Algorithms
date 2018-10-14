package com.coursera.graph.kosarajuscc;

import java.util.Set;
import java.util.Stack;

import com.coursera.graphs.pojo.Graph;

public class KosarajuScc {
//	
//	private static boolean[] explored;
//	private static int[] leader;
//	private static int[] finish_temp;
//	private static int[] finish_time;
//	private static int source = -1;
//	private static int t_fin = -1;
//	
//	public static int[] getLeader() {
//		return leader;
//	}
//	
//	private static void DFS(Graph graph, int u) {
//		explored[u] = true;
//		leader[u] = source;
//		
//		Stack<Integer> s = new Stack<Integer>();
//		s.push(u);
//		
//		--t_fin;
//		finish_temp[u] = t_fin;
//		
//		while(!s.isEmpty()) {
//			int vtx = s.peek();
//			s.pop();
//			
//			if(!explored[vtx])
//				explored[vtx] = true;
//			
//			Set<Integer> adjVertices = graph.getAdjacencyList().get(u);
//			if(adjVertices != null && !adjVertices.isEmpty()) {
//				for(int v : adjVertices) {
//					if(!explored[v]) {
//						s.push(v);
//						leader[v] = source;
//						
//						--t_fin;
//						finish_temp[v] = t_fin;
//					}
//				}
//			}
//			
//		}
//	}
//	
//	public static int[] DFS_loop(Graph graph, int[] finish_times) {
//		int vertices = graph.getVertices();
//		t_fin = vertices;
//		explored = new boolean[vertices];
//		leader = new int[vertices];
//		finish_temp = new int[vertices];
//		finish_time = new int[vertices];
//		
//		for(int i=0; i<vertices; ++i) {
//			explored[i] = false;
//			finish_temp[i] = -1;
//			leader[i] = -1;
//		}
//		
//		for(int j=vertices-1; j>=0; --j) {
//			int v = finish_times[j];
//			if(!explored[v]) {
//				System.out.println("Starting.." + v);
//				source = v;
//				DFS(graph,v);
//			}
//		}
//		
//		for(int i=0; i<vertices; ++i) {
//			finish_time[finish_temp[i]] = i;
//		}
//		
//		return finish_time;
//	}
//	
	
	
	private static boolean[] explored;
	private static int[] leader;
	private static int[] finish_temp;
	private static int[] finish_time;
	private static int source = -1;
	private static int t_fin = -1;
	
	public static int[] getLeader() {
		return leader;
	}
	
	private static void DFS(Graph graph, int u) {
		explored[u] = true;
		leader[u] = source;
		
		Set<Integer> adjVertices = graph.getAdjacencyList().get(u);
		if(adjVertices != null && !adjVertices.isEmpty()) {
			for(int v : adjVertices) {
				if(!explored[v]) {
					DFS(graph,v);
				}
			}
		}
		
		++t_fin;
		finish_temp[u] = t_fin;
	}
	
	public static int[] DFS_loop(Graph graph, int[] finish_times) {
		int vertices = graph.getVertices();
		t_fin = -1;
		explored = new boolean[vertices];
		leader = new int[vertices];
		finish_temp = new int[vertices];
		finish_time = new int[vertices];
		
		for(int i=0; i<vertices; ++i) {
			explored[i] = false;
			finish_temp[i] = -1;
			leader[i] = -1;
		}
		
		for(int j=vertices-1; j>=0; --j) {
			int v = finish_times[j];
			if(!explored[v]) {
				source = v;
				DFS(graph,v);
			}
		}
		
		for(int i=0; i<vertices; ++i) {
			finish_time[finish_temp[i]] = i;
		}
		
		return finish_time;
	}
	

}
