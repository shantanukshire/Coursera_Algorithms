package com.coursera.clustering.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import com.coursera.clustering.core.SingleLinkClustering;
import com.coursera.clustering.pojo.Edge;

public class Test { 
	
	private static final int K = 4;
//	private static final String FILE_PATH = "./data/simple.txt";
//	private static final String FILE_PATH = "./data/clustering1.txt";
	private static final String FILE_PATH = "./data/clustering_big.txt";
	
	public static void main (String ...args) throws IOException {
		
		Path path = Paths.get(FILE_PATH);
		Scanner sc = new Scanner(path);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		if(FILE_PATH.contains("clustering1")) {
			int totalNodes = Integer.parseInt(sc.next());
			ArrayList<Edge> edges = new ArrayList<Edge>();
			
			while(sc.hasNext()){
				String[] values = sc.next().split("\\s+");
				
				int src = Integer.parseInt(values[0]) - 1;
				int dest = Integer.parseInt(values[1]) - 1;
				int weight = Integer.parseInt(values[2]);
				
				Edge edge = new Edge(src, dest, weight);
				
				edges.add(edge);
			}
			
			System.out.println("Max-Spacing: " + 
							SingleLinkClustering.solve(K, totalNodes, edges.toArray(new Edge[0])));
		}else {
			String[] temp = sc.next().split("\\s+");
			int nodes = Integer.parseInt(temp[0]);
			int bits = Integer.parseInt(temp[1]);
			String[] values = new String[nodes];
			
			for(int i=0; i<nodes; ++i) {
				values[i] = sc.next().replaceAll(" ", "");
			}
			
			System.out.println("Total Groups with at least 3 Max-spacing = " + 
							SingleLinkClustering.solve_bits(nodes, bits, values));
			
		}
		
	}

}
