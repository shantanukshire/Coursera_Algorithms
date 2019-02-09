package com.coursera.optimalbst.test;

import com.coursera.optimalbst.core.OptimalBST;

public class Test {

	
	public static void main(String args[]) {
		int N = 7;
		int keys[] = {1, 2, 3, 4, 5, 6, 7};
		int wt[] = {20, 5, 17, 10, 20, 3, 25};
		
		System.out.println("Optimal BST Search Time: " + OptimalBST.solve(keys, wt, N));
		
	}
	
}
