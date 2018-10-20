package com.coursera.algo.medianmaintenance;

import com.coursera.algo.pojo.MaxHeap;
import com.coursera.algo.pojo.MinHeap;

public class MedianMaintenance {
	
	private static int[] median;
	private static MaxHeap heap_l;
	private static MinHeap heap_h;
	
	public static int[] run(int[] arr) {		
		int arr_size = arr.length;
		median = new int[arr_size];
		heap_l = new MaxHeap(arr_size);
		heap_h = new MinHeap(arr_size);
		
		if(arr[0] <= arr[1]) {
			heap_l.insert(arr[0]);
			heap_h.insert(arr[1]);
		}else {
			heap_h.insert(arr[0]);
			heap_l.insert(arr[1]);
		}
		median[0] = arr[0];
		median[1] = heap_l.peek();
		
		int count = 2;
		for(int i=2; i<arr.length; ++i) {
			int num = arr[i];
			int m = -1;
			
			if(heap_l.isEmpty() || num <= heap_l.peek())
				heap_l.insert(num);
			else if(heap_h.isEmpty() || num > heap_l.peek())
				heap_h.insert(num);
			
			if(heap_l.size() > heap_h.size() 
					|| heap_h.size() > heap_l.size()+1)
				rebalanceHeaps();

			if((count+1)%2 == 0) {	//Even
				m = heap_l.peek();
			}else {					//Odd
				m = heap_h.peek();
			}
			
			if(count > 0) {
				median[count] = m;
			}
			
			++count;
		}
		
		return median;
	}

	private static void rebalanceHeaps() {
		int popped;
		if(heap_l.size() > heap_h.size()) {
			popped = heap_l.extractMax();
			heap_h.insert(popped);
		}else if(heap_h.size() > heap_l.size()+1) {
			popped = heap_h.extractMin();
			heap_l.insert(popped);
		}
	}

}
