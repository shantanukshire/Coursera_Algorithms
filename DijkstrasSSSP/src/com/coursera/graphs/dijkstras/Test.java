package com.coursera.graphs.dijkstras;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.coursera.graphs.pojo.Graph;
import com.coursera.graphs.pojo.GraphNode;

public class Test {
	
	private static final int TOTAL_VERTICES = 200;
	private static final int SRC_VTX = 0;
	private static final String FILE_PATH = "./data/dijkstraData.txt";
	private static final int[] displayMin = {6,36,58,81,98,114,132,164,187,196};
	
	public static void main (String[] args) throws IOException {		
		
		int edges = 0;
		HashMap<Integer,Set<GraphNode>> adjList = new HashMap<Integer,Set<GraphNode>>();
		Path path = Paths.get(FILE_PATH);
		Scanner scn = new Scanner(path);
		scn.useDelimiter(System.getProperty("line.separator"));

		try {
			while (scn.hasNext()) {
				String line = new String(scn.next());
				String[] values = line.split("\\s+");
				if (values.length > 0) {
					int v = Integer.parseInt(values[0]) - 1;
					for(int e=1; e<values.length; ++e) {
						String tuple = values[e];
						String[] nodeData = tuple.split(",");
						
						int u = Integer.parseInt(nodeData[0])-1;
						int w = Integer.parseInt(nodeData[1]);
						Set<GraphNode> adjSet = null;
						
						//Add u to v
						if(!adjList.containsKey(v)) {
							adjSet = new HashSet<GraphNode>();
						}else {
							adjSet = adjList.get(v);
						}
						adjSet.add(new GraphNode(u, w));
						adjList.put(v, adjSet);
						
						edges = edges + 1;
					}
				}
			}

			Graph graph = new Graph(TOTAL_VERTICES,edges,adjList);
			int[] dist = Dijkstras.run(graph, SRC_VTX);
			

			System.out.println("---- Output ----");
			System.out.println("Shortest distance from source: " + SRC_VTX);
			for(int vtx=0;vtx<displayMin.length;++vtx) {
				System.out.print(dist[displayMin[vtx]]);
				if(vtx != displayMin.length-1)
					System.out.print(",");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			scn.close();
		}
	}

}
