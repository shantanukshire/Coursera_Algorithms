package com.coursera.graphs.kargermincount;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.coursera.graphs.pojo.Edge;
import com.coursera.graphs.pojo.Graph;

public class Test {
	
	private static final int TOTAL_VERTICES = 200;
	private static final int TOTAL_ALGO_ITERATIONS = TOTAL_VERTICES*TOTAL_VERTICES;
	private static final String FILE_PATH = "./data/kargerMinCut.txt";


	public static void main(String args[]) throws IOException {

		int result = -1;
		int minCut = Integer.MAX_VALUE;
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		HashMap<Edge,Integer> edgeMap = new HashMap<Edge,Integer>();
		Path path = Paths.get(FILE_PATH);
		Scanner scn = new Scanner(path);
		scn.useDelimiter(System.getProperty("line.separator"));

		try {
			int i = 0;
			while (scn.hasNext()) {
				String line = new String(scn.next());
				String[] values = line.split("\\s+");
				if (values.length > 0) {
					int tailVtx = Integer.parseInt(values[0]) - 1;
					for (int vtx = 1; vtx < values.length; ++vtx) {
						int headVtx = Integer.parseInt(values[vtx]) - 1;
						Edge e = new Edge(tailVtx,headVtx);
						if(!edgeMap.containsKey(e)) {
							edgeList.add(e);
							edgeMap.put(e,1);
						}else {
							System.out.println("Edge already present " + e.getSource() + " " + e.getDest());
						}
					}
				}
				++i;
			}
			
			Graph graph = new Graph(TOTAL_VERTICES,edgeList);
			for (int algoItr = 0; algoItr < TOTAL_ALGO_ITERATIONS; ++algoItr) {
				int itrMinCut = KargerMinCut.calculateMinCut(graph);
				if(itrMinCut < minCut) {
					minCut = itrMinCut;
				}
				//System.out.println("Itr " + algoItr + " cut: " + itrMinCut);
			}

			System.out.println("Minimum Cut of Graph for " + TOTAL_ALGO_ITERATIONS + 
					" iterations: " + minCut);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			scn.close();
		}

	}

}
