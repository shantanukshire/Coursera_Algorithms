package com.coursera.primsmst.pojo;

public class MinHeap {
	
	private static final int FRONT = 0;
	private int size;
	private int cap;
	private GraphNode[] heap;
	private int[] vtxPos;
	
	public MinHeap(int capacity) {
		this.cap = capacity;
		this.heap = new GraphNode[capacity];
		this.vtxPos = new int[capacity];
		size = -1;
	}
	
	private boolean isFull() {
		return size == cap;
	}
	
	public boolean isInHeap(GraphNode node) {
		int pos = vtxPos[node.getVertexId()];
		return pos>-1 && pos<=size;
	}
	
	public boolean isEmpty() {
		return size < 0;
	}
	
	public int size() {
		return size+1;
	}
	
	private int parent(int i) {
		return (i-1)/2;
	}
	
	private int leftChild(int i) {
		return (2*i)+1;
	}
	
	private int rightChild(int i) {
		return (2*i)+2;
	}
	
	public boolean isLeaf(int i) {
		return (i>=(size/2) && i<=size);
	}
	
	private void swap(int i, int j) {
		GraphNode temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		vtxPos[heap[i].getVertexId()] = i;
		vtxPos[heap[j].getVertexId()] = j;
	}
	
	public void insert(GraphNode node) {
		if(!isFull()) {
			heap[++size] = node;
			vtxPos[node.getVertexId()] = size;
			
			int current = size;
			while(heap[current].getDistance() < heap[parent(current)].getDistance()) {
				swap(current,parent(current));
				current = parent(current);
			}
		}
	}
	
	public GraphNode extractMin() {
		GraphNode popped = null;
		if(size > -1) {
			popped = heap[FRONT];
			vtxPos[popped.getVertexId()] = Integer.MAX_VALUE;
			
			heap[FRONT] = heap[size--];
			vtxPos[heap[FRONT].getVertexId()] = FRONT;
			
			minHeapify(FRONT);
		}	
		return popped;
	}
	
	public void delete(int vertexId) {
		decreaseKey(vertexId,Integer.MIN_VALUE);
		extractMin();
	}
	
	public void decreaseKey(int vertexId, int dist) {
		int pos = vtxPos[vertexId];
		heap[pos].setDistance(dist);
		int current = pos;
		
		while(heap[current].getDistance() < heap[parent(current)].getDistance()) {
			swap(current,parent(current));
			current = parent(current);
		}
	}
	
	private void minHeapify(int i) {
		int lowest = i;
		int left = leftChild(i);
		int right = rightChild(i);
		
		if(left <= size && heap[left].getDistance() < heap[lowest].getDistance())
			lowest = left;
		if(right <= size && heap[right].getDistance() < heap[lowest].getDistance())
			lowest = right;
		if(lowest != i) {
			swap(i,lowest);
			minHeapify(lowest);
		}
	}

}
