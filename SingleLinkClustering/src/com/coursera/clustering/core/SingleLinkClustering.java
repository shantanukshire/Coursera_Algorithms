package com.coursera.clustering.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.coursera.clustering.pojo.DisjointSet;
import com.coursera.clustering.pojo.Edge;

public class SingleLinkClustering {
	
	private static ArrayList<String> getOneDistanceBits(String str){
		ArrayList<String> ret = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		
		for(int i=0; i<str.length(); ++i) {
			if(str.charAt(i) == '0') {
				sb.setCharAt(i, '1');
				ret.add(sb.toString());
				sb.setCharAt(i, '0');
			}else if(str.charAt(i) == '1') {
				sb.setCharAt(i, '0');
				ret.add(sb.toString());
				sb.setCharAt(i, '1');
			}
		}
		
		return ret;
	}
	
	private static ArrayList<String> getTwoDistanceBits(String str){
		ArrayList<String> ret = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		
		for(int i=0; i<str.length()-1; ++i) {
			boolean isOne = true;
			if(str.charAt(i) == '0') {
				sb.setCharAt(i, '1');
				isOne = false;
			}else if(str.charAt(i) == '1') {
				sb.setCharAt(i, '0');
			}
			
			for(int j=i+1; j<str.length(); ++j) {
				if(str.charAt(j) == '0') {
					sb.setCharAt(j, '1');
					ret.add(sb.toString());
					
					sb.setCharAt(j, '0');
					
				}else if(str.charAt(j) == '1') {
					sb.setCharAt(j, '0');
					ret.add(sb.toString());
					
					sb.setCharAt(j, '1');
				}
			}
			
			if(isOne) sb.setCharAt(i, '1');
			else sb.setCharAt(i, '0');

		}
		
		return ret;
	}
	
	private static void swap(Edge[] edges, int x, int y) {
		Edge temp = edges[x];
		edges[x] = edges[y];
		edges[y] = temp;
	}
	
	private static int partition(Edge[] edges, int l, int r) {
		Edge pivot = edges[l];
		int i = l;
		int j = l+1;
		
		while(j <= r) {
			if(edges[j].weight < pivot.weight) {
				++i;
				swap(edges, j, i);
			}
			++j;
		}
		
		swap(edges, l, i);
		return i;
	}
	
	private static void quickSort(Edge[] edges, int l, int r) {
		if(l < r) {
			int pi = partition(edges, l, r);
			quickSort(edges, l, pi - 1);
			quickSort(edges, pi + 1, r);
		}
	}
	
	private static void quickSortUtil(Edge[] edges) {
		quickSort(edges, 0, edges.length - 1);
	}
	
    public static HashSet<Integer> getTwoSetBitNums(int n, int bits) {
    	HashSet<Integer> twoBitSet = new HashSet<Integer>();
        int x = 1; 
        while (n > 0) 
        { 
        	boolean temp = false;
            int y = 0; 
            while (y < x) 
            { 
            	int number = ((1 << x) + (1 << y));
            	if(number > n)
            		temp = true;
            	twoBitSet.add(number); 
                y++; 
            } 
            if(temp) break;
            x++; 
        } 
        
        for(int i=0; i<bits; ++i) {
        	int number = 1 << i;
        	System.out.println(number);
        	twoBitSet.add(number);
        }
        
        return twoBitSet;
    }
    

	private static String getMaxBitNumber(int bits) {
		StringBuilder strBuilder = new StringBuilder();
		for(int i=0; i<bits; ++i) {
			strBuilder.append("1");
		}
		return strBuilder.toString();
	}
	
	public static int solve(int K, int nodes, Edge[] edges) {
		int maxSpacing = Integer.MAX_VALUE;
		DisjointSet set = new DisjointSet(nodes);
		
		//Sort Edges w.r.t. weight
		quickSortUtil(edges);
		
		//Loop over every edge and perform Find & Union operations
		for(Edge edge : edges) {
			
			//Total groups should be equal to K condition
			if(set.getTotalGroups() == K)
				break;
			
			int src = edge.src;
			int dest = edge.dest;
			
			if(set.find(src) != set.find(dest)) {
				set.Union(src, dest);
			}
		}
		
		//Calculate Max-Spacing
		for(Edge edge : edges) {
			int src = edge.src;
			int dest = edge.dest;
			int weight = edge.weight;
			
			if(set.find(src) != set.find(dest) && weight < maxSpacing) {
				maxSpacing = weight;
			}
		}
				
		return maxSpacing;
	}
	
	public static int solve_bits(int nodes, int bits, String[] values) {
		ArrayList<Integer> vtxList;
		ArrayList<String> vtxStringList;
		HashMap<String , ArrayList<Integer>> inputMap = new HashMap<String, ArrayList<Integer>>();
		DisjointSet set = new DisjointSet(nodes);
		
		for(int i=0; i<values.length; ++i) {
			ArrayList<Integer> arr;
			if(inputMap.containsKey(values[i])) {
				arr = inputMap.get(values[i]);
			}else {
				arr = new ArrayList<Integer>();
			}
			arr.add(i);
			inputMap.put(values[i], arr);
		}
		
		for(int i=0; i<values.length; ++i) {
			vtxList = inputMap.get(values[i]);
			if(vtxList != null && !vtxList.isEmpty()) {
				for(Integer v : vtxList) {
					if(v != i) {
						if(set.find(i) != set.find(v)) {
							set.Union(i, v);
						}
					}
				}
			}
		}
		
		for(int i=0; i<values.length; ++i) {
			vtxStringList = getOneDistanceBits(values[i]);
			
			if(vtxStringList != null && !vtxStringList.isEmpty()) {
				
				for(String vtxStr : vtxStringList) {
					
					if(inputMap.containsKey(vtxStr)) {
						vtxList = inputMap.get(vtxStr);
						if(vtxList != null && !vtxList.isEmpty()) {
							for(Integer v : vtxList) {
								if(set.find(i) != set.find(v)) {
									set.Union(i, v);
								}
							}
						}
					}

				}
			}
		}
		
		for(int i=0; i<values.length; ++i) {
			vtxStringList = getTwoDistanceBits(values[i]);
			
			if(vtxStringList != null && !vtxStringList.isEmpty()) {
				
				for(String vtxStr : vtxStringList) {
					
					if(inputMap.containsKey(vtxStr)) {
						vtxList = inputMap.get(vtxStr);
						if(vtxList != null && !vtxList.isEmpty()) {
							for(Integer v : vtxList) {
								if(set.find(i) != set.find(v)) {
									set.Union(i, v);
								}
							}
						}
					}

				}
			}
		}
		
		
//		String maxBitNum = getMaxBitNumber(bits);
//		HashSet<Integer> twoSetBitSet = getTwoSetBitNums(Integer.parseInt(maxBitNum, 2), bits);
//		DisjointSet set = new DisjointSet(nodes);
//		
//		for(int i=0; i<values.length - 1; ++i) {
//			for(int j=i+1; j<values.length; ++j) {
//				int src_num = Integer.parseInt(values[i], 2);
//				int dest_num = Integer.parseInt(values[j], 2);
//				
//				if(twoSetBitSet.contains(src_num ^ dest_num)) {
////					System.out.println("Common set : " + i + "  " + j);
//					set.Union(i, j);
//				}
//			}
//		}
		
		return set.getTotalGroups();
	}


}
