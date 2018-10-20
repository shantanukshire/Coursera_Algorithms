package com.coursera.algo.pojo;

public class MinHeap {
	
	private final int FRONT = 0;
	private int capacity;
	private int size;
	private int[] heap;
	
	public MinHeap(int capacity) {
		this.capacity = capacity;
		size = -1;
		this.heap = new int[capacity];
	}
	
	public int peek() {
		if(isEmpty())
			return -1;
		else
			return heap[FRONT];
	}
	
	public int size() {
		return size+1;
	}
	
	public boolean isEmpty() {
		if(size < 0)
			return true;
		else
			return false;
	}
	
	private int parent(int i) {
		return (i-1)/2;
	}
	
	private int left(int i) {
		return (2*i)+1;
	}
	
	private int right(int i) {
		return (2*i)+2;
	}
	
	private boolean isLeaf(int pos) {
		return (pos>parent(size) && pos<=size);
	}
	
	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public void insert(int e) {		
		if(size+1 > capacity) {
			throw new StackOverflowError();
		}else {
			heap[++size] = e;
			int current = size;
			while(current!=FRONT && heap[current] < heap[parent(current)]) {
				swap(current,parent(current));
				current = parent(current);
			}
		}

	}
	
	private void minHeapify(int i) {
		if(!isLeaf(i)) {
			int l = left(i);
			int r = right(i);
			int smallest = i;
			
			if(heap[l] < heap[smallest])
				smallest = l;
			
			if(heap[r] < heap[smallest])
				smallest = r;
			
			if(smallest != i) {
				swap(i,smallest);
				minHeapify(smallest);
			}
				
		}
	}
	
	public int extractMin() {
		int popped = heap[FRONT];
		heap[FRONT] = heap[size--];
		minHeapify(FRONT);
		return popped;
	}

}
