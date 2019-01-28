package com.coursera.huffman.pojo;

public class MinHeap {
	
	private final int FRONT;
	private int capacity;
	private int size;
	private Node[] heap;
	
	public MinHeap(int cap) {
		this.capacity = cap;
		this.FRONT = 0;
		this.size = -1;
		heap = new Node[capacity];
	}
	
	public int size() {
		return this.size + 1;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	private boolean isFull() {
		return (size + 1) == capacity;
	}
	
	private int parent(int a) {
		return (a-1)/2;
	}
	
	private int leftChild(int a) {
		return (a*2) + 1;
	}
	
	private int rightChild(int a) {
		return (a*2) + 2;
	}
	
	private boolean isLeaf(int a) {
		return a > parent(size) && a <= size;
	}
	
	private void swap(int i, int j) {
		Node temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public void insert(Node node) {
		if(!isFull()) {
			heap[++size] = node;
			int current = size;
			while(heap[current].value < heap[parent(current)].value) {
				swap(current, parent(current));
				current = parent(current);
			}
		}
	}
	
	public Node extractMin() {
		Node popped = heap[FRONT];
		heap[FRONT] = heap[size--];
		minHeapify(FRONT);
		return popped;
	}
	
	private void minHeapify(int pos) {
		if(!isLeaf(pos)) {
			int smallest = pos;
			int left = leftChild(pos);
			int right = rightChild(pos);
			
			if(left <= size && heap[left].value < heap[smallest].value) {
				smallest = left;
			}
			
			if(right <= size && heap[right].value < heap[smallest].value) {
				smallest = right;
			}
			
			if(smallest != pos) {
				swap(smallest, pos);
				minHeapify(smallest);
			}
		}
	}

}
