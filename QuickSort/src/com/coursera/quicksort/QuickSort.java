package com.coursera.quicksort;

public class QuickSort {
	
	private static volatile int comparisons = 0;
	
	public static int leftPivot(int[] arr, int l, int r) {
		comparisons = 0;		
		sort_leftpivot(arr,l,r);
		return comparisons;
	}
	
	public static int rightPivot(int[] arr, int l, int r) {
		comparisons = 0;		
		sort_rightpivot(arr,l,r);
		return comparisons;
	}
	
	public static int medianPivot(int[] arr, int l, int r) {
		comparisons = 0;		
		sort_medianpivot(arr,l,r);
		return comparisons;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static int getMedianIdx(int arr[], int l, int m, int r) {
		int median_idx = -1;
		
		double avg = (arr[l]+arr[m]+arr[r])/3.0d;
		double diff_l = Math.abs((double)arr[l]-avg);
		double diff_m = Math.abs((double)arr[m]-avg);
		double diff_r = Math.abs((double)arr[r]-avg);
		
		if(Double.compare(diff_l, diff_r) <= 0) {
			if(Double.compare(diff_l, diff_m) <= 0)
				median_idx = l;
			else 
				median_idx = m;
		}else {
			if(Double.compare(diff_r, diff_m) <= 0)
				median_idx = r;
			else
				median_idx = m;
		}
		return median_idx;
	}
	
	private static int partition(int[] arr, int l, int r) {
		int pi = arr[l];
		int i = l;
		for(int j=l+1; j<=r; ++j) {
			if(arr[j] < pi) {
				++i;
				swap(arr,i,j);
			}
		}
		comparisons += r-l;
		swap(arr,l,i);
		return i;
	}
	
	private static void sort_leftpivot(int[] arr, int l, int r) {
		if(l < r) {
			int pi_idx = partition(arr,l,r);
			sort_leftpivot(arr,l,pi_idx-1);
			sort_leftpivot(arr,pi_idx+1,r);
		}		
	}
	
	private static void sort_rightpivot(int[] arr, int l, int r) {
		if(l < r) {
			swap(arr,l,r);
			int pi_idx = partition(arr,l,r);
			sort_rightpivot(arr,l,pi_idx-1);
			sort_rightpivot(arr,pi_idx+1,r);
		}		
	}
	
	private static void sort_medianpivot(int[] arr, int l, int r) {
		if(l < r) {
			int median_idx = getMedianIdx(arr, l,(l+r)/2,r);
			swap(arr,l,median_idx);
			int pi_idx = partition(arr,l,r);
			sort_medianpivot(arr,l,pi_idx-1);
			sort_medianpivot(arr,pi_idx+1,r);
		}		
	}
	
}
