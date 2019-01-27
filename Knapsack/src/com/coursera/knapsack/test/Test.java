package com.coursera.knapsack.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.coursera.knapsack.core.Knapsack;

public class Test {
	
//	private static final String FILE_PATH = "./data/knapsack_small.txt";
//	private static final String FILE_PATH = "./data/knapsack1.txt";
	private static final String FILE_PATH = "./data/knapsack_big.txt";
	
	public static void main (String args[]) throws IOException {
		
		int W=0, total_items=0;
		int[] values;
		int[] weights;
		String[] temp;
		
		Path path = Paths.get(FILE_PATH);
		Scanner sc = new Scanner(path);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		if(sc.hasNext()) {
			temp = sc.next().split("\\s+");
			W = Integer.parseInt(temp[0]);
			total_items = Integer.parseInt(temp[1]); 
			System.out.println("W=" + W + " items=" + total_items);
		}
		
		values = new int[total_items];
		weights = new int[total_items];
		for(int i=0; i<total_items; ++i) {
			temp = sc.next().split("\\s+");
			values[i] = Integer.parseInt(temp[0]);
			weights[i] = Integer.parseInt(temp[1]);
		}
		
//		System.out.println("Maximum Value=" + Knapsack.solve_recur(W, total_items, values, weights));
//		System.out.println("Maximum Value=" + Knapsack.solve_iter(W, total_items, values, weights));
		System.out.println("Maximum Value=" + Knapsack.solve_iter_minspace(W, total_items, values, weights));

	}

}
