package com.coursera.optimalbst.core;

public class OptimalBST {
	
	private static int min(int a, int b) {
		return a < b ? a : b;
	}
	
	private static int getWeight(int[][] dp, int i, int j, int size) {
		if(i == j || i < j)
			return dp[i][j];
		else
			return 0;
	}

	public static int solve(int[] keys, int[] wt, int N) {
		int[][] dp = new int[N][N];
		
		for(int s=0; s<N; ++s) {
			for(int i=0; i<N; ++i) {
				int j = i+s;
				if(j < N) {
					if(i == j) {
						dp[i][j] = wt[i];
					}else {
						int weight = 0;
						dp[i][j] = Integer.MAX_VALUE;
						
						for(int r = i; r <= j; ++r) {
							weight += wt[r];
						}
						
						for(int r=i; r <= j; ++r) {
							dp[i][j] = min(dp[i][j], weight + getWeight(dp,i,r-1,N) + getWeight(dp,r+1,j,N));
						}
					}
				}
			}
		}
		
		return dp[0][N-1];
	}
	
}
