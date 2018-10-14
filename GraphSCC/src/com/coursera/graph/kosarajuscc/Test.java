package com.coursera.graph.kosarajuscc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.coursera.graphs.pojo.Graph;

public class Test {
	
	private static final int TOTAL_VERTICES = 875714;
	private static final String FILE_PATH = "./data/SCC.txt";
	
	public static void main (String[] args) throws IOException {		
		
		int edges = 0;
		HashMap<Integer,Set<Integer>> adjList = new HashMap<Integer,Set<Integer>>();
		HashMap<Integer,Set<Integer>> adjList_rev = new HashMap<Integer,Set<Integer>>();
		HashMap<Integer,Set<Integer>> leaderMap = new HashMap<Integer,Set<Integer>>();
		int[] finish_times = new int[TOTAL_VERTICES];
		Path path = Paths.get(FILE_PATH);
		Scanner scn = new Scanner(path);
		scn.useDelimiter(System.getProperty("line.separator"));

		try {
			while (scn.hasNext()) {
				String line = new String(scn.next());
				String[] values = line.split("\\s+");
				if (values.length > 0) {
					int tail = Integer.parseInt(values[0]) - 1;
					int head = Integer.parseInt(values[1]) - 1;
					
					//Normal Graph
					Set<Integer> adjSet = null;
					if(!adjList.containsKey(tail)) {
						adjSet = new HashSet<Integer>();
					}else {
						adjSet = adjList.get(tail);
					}
					adjSet.add(head);
					adjList.put(tail, adjSet);
					
					//Reverse Graph
					Set<Integer> adjSet_rev = null;
					if(!adjList_rev.containsKey(head)) {
						adjSet_rev = new HashSet<Integer>();
					}else {
						adjSet_rev = adjList_rev.get(head);
					}
					adjSet_rev.add(tail);
					adjList_rev.put(head, adjSet_rev);
					
					edges++;
				}
			}
			
			
			
			Graph graph_rev = new Graph(TOTAL_VERTICES,edges,adjList_rev);			
			Graph graph = new Graph(TOTAL_VERTICES,edges,adjList);
			
			for(int i=0; i<TOTAL_VERTICES; ++i) {
				finish_times[i] = i;
			}
			
			finish_times = KosarajuScc.DFS_loop(graph_rev,finish_times);
			KosarajuScc.DFS_loop(graph,finish_times);
			
			int[] leader = KosarajuScc.getLeader();

			for(int i=0; i<leader.length; ++i) {
				int l = leader[i];
				Set<Integer> vertexList = null;
				
				if(!leaderMap.containsKey(l)) {
					vertexList = new HashSet<Integer>();
				}else {
					vertexList = leaderMap.get(l);
				}
				
				vertexList.add(i);
				leaderMap.put(l, vertexList);
			}
			
			Set<Integer> leaderKeys = leaderMap.keySet();
			System.out.println("Total distinct SCCs: " + leaderKeys.size());
			Iterator<Integer> keyItr = leaderKeys.iterator();
			while(keyItr.hasNext()) {
				int l = keyItr.next();
				Set<Integer> vtxList = leaderMap.get(l);
				if(vtxList.size() > 200) {
					System.out.print("Leader - " + l + "(" + vtxList.size() +")");
					System.out.println();
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			scn.close();
		}
	}

}
