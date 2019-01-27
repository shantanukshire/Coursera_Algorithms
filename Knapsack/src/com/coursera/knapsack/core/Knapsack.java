package com.coursera.knapsack.core;

import java.util.Stack;

public class Knapsack {
	
	private static Stack<Integer> stack;
	
	private static int max(int a, int b) {
		return a > b ? a : b;
	}
	
	private static void reconstruct_knapsack(int[][] dp, int n, int W, 
			int[] value, int[] wt, Stack<Integer> stack) {
		
		if(n <= 0 || W <= 0) {
			return;
		}
		
		if(dp[n][W] > dp[n-1][W]) {
			stack.push(n-1);
			reconstruct_knapsack(dp, n-1, W - wt[n-1], value, wt, stack);
		}else {
			reconstruct_knapsack(dp, n-1, W, value, wt, stack);
		}
		
	}
	
	private static void reconstruct_util(int[][] dp, int n, int W, 
			int[] values, int[] weights) {
		System.out.println("\n---- Solution ----");
		stack = new Stack<Integer>();
		reconstruct_knapsack(dp, n, W, values, weights, stack);		
		while(!stack.isEmpty()) {
			System.out.println("item no. = " + stack.pop());
		}
	}
	
	public static int knapsack_recur(int[][] dp, int W, int n, int[] value, int[] wt) {
		if(dp[n][W] != -1) {
			return dp[n][W];
		}
		
		int ret;
		
		if(n <= 0 || W <= 0)
			ret = 0;
		else if(wt[n-1] > W) {
			ret = knapsack_recur(dp, W, n-1, value, wt);
		}else {
			ret = max(knapsack_recur(dp, W, n-1, value, wt), 
					value[n-1] + knapsack_recur(dp, W-wt[n-1], n-1, value, wt));
		}
		
		dp[n][W] = ret;
		
		return ret;
	}
	
	public static int knapsack_iter(int W, int n, int[] value, int[] wt) {
		
		int[][] dp = new int[n+1][W+1];
		
		for(int i=0; i<=n; ++i) {
			for(int j=0; j<=W; ++j) {
				if(i==0 || j==0) {
					dp[i][j] = 0;
				}else if(wt[i-1] > j){
					dp[i][j] = dp[i - 1][j];
				}else {
					dp[i][j] = max(dp[i- 1][j], value[i-1] + dp[i - 1][j - wt[i-1]]);
				}
			}
		}
		
//		reconstruct_util(dp, n, W, value, wt);
		
		return dp[n][W];
		
	}
	
	public static int knapsack_iter_minspace(int W, int n, int[] value, int[] wt) {
		
		int[][] dp = new int[2][W+1];
		
		int row = 1;
		for(int i=0; i<=n; ++i) {
			row = 1 - row;
			for(int j=0; j<=W; ++j) {
				if(i==0 || j==0) {
					dp[row][j] = 0;
				}else if(wt[i-1] > j){
					dp[row][j] = dp[1 - row][j];
				}else {
					dp[row][j] = max(dp[1 - row][j], value[i-1] + dp[1 - row][j - wt[i-1]]);
				}
			}
		}
		
//		reconstruct_util(dp, n, W, value, wt);
		
		return dp[row][W];
		
	}
	
	public static int solve_recur(int W, int size, int[] values, int[] weights) {
		int[][] dp = new int[size+1][W+1];
		
		for(int i=0; i<size+1; ++i) {
			for(int j=0; j<W+1; ++j) {
				dp[i][j] = -1;
			}
		}
		
		knapsack_recur(dp, W, size, values, weights);
//		reconstruct_util(dp, size, W, values, weights);
		
		return dp[size][W];
	}
	
	public static int solve_iter(int W, int size, int[] values, int[] weights) {
		return knapsack_iter(W, size, values, weights);
	}
	
	public static int solve_iter_minspace(int W, int size, int[] values, int[] weights) {
		return knapsack_iter_minspace(W, size, values, weights);
	}

}
