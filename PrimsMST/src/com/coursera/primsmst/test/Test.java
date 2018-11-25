package com.coursera.primsmst.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.coursera.primsmst.pojo.Graph;
import com.coursera.primsmst.pojo.PrimsMST;

public class Test {
	
	private static int TOTAL_VERTICES;
	private static int TOTAL_EDGES;
	private static final String FILE_PATH = "./data/edges.txt";
	
	public static void main (String args[]) throws IOException {
		Graph graph = null;
		int[][] adjMatrix = null;
		
		Path path = Paths.get(FILE_PATH);
		Scanner sc = new Scanner(path);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		if(sc.hasNext()) {
			String[] vtxEdgeString = sc.next().split("\\s+");
			TOTAL_VERTICES = Integer.parseInt(vtxEdgeString[0]);
			TOTAL_EDGES = Integer.parseInt(vtxEdgeString[1]);
			graph = new Graph(TOTAL_VERTICES,TOTAL_EDGES);
			
			adjMatrix = new int[TOTAL_VERTICES][TOTAL_VERTICES];
			for(int i=0; i<TOTAL_VERTICES; ++i) {
				for(int j=0; j<TOTAL_VERTICES; ++j) {
					adjMatrix[i][j] = 0;
				}
			}
		}
		
		for(int i=0; i<TOTAL_EDGES; ++i) {
			String[] edgeSplit = sc.next().split("\\s+");
			
			int u = Integer.parseInt(edgeSplit[0]) - 1;
			int v = Integer.parseInt(edgeSplit[1]) - 1;
			int dist = Integer.parseInt(edgeSplit[2]);
			
			adjMatrix[u][v] = dist;
			adjMatrix[v][u] = dist;
			graph.addEdge(u, v, dist);
			graph.addEdge(v, u, dist);
		}

		System.out.println("MST Cost = " + PrimsMST.getMSTCost(graph,adjMatrix));

	}

}
