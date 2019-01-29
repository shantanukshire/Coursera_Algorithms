package com.coursera.maxweightset.core;

public class MaximumWeightSet {
	
	public static long max(long a, long b) {
		return a > b ? a : b;
	}
	
	public static void reconstruct(char[] c, long[] dp, int[] weights, int n) {
		
		if(n <= 0) {
			return;
		}
		
		if(dp[n] > dp[n-1]) {
			c[n-1] = '1';
			reconstruct(c, dp, weights, n-2);
		}else {
			reconstruct(c, dp, weights, n-1);
		}
		
	}
	
	public static void solve(int[] weights, int n) {
		long[] dp = new long[n+1];
		dp[0] = 0;
		dp[1] = weights[0];
		
		for(int i=2; i<n+1; ++i) {
			dp[i] = max(dp[i-1], dp[i-2] + weights[i-1]);
		}
		
		char[] c = new char[n];
		for(int i=0; i<n; ++i) {
			c[i] = '0';
		}
		
		
		
		reconstruct(c, dp, weights, n);
		System.out.println("Max Weight = " + dp[n]);
		
		int[] bits = {1, 2, 3, 4, 17, 117, 517, 997};
		for(int i=0; i<bits.length; ++i) {
			System.out.print(c[bits[i]-1]);
		}
	}

}
