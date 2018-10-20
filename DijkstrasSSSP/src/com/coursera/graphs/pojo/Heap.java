package com.coursera.graphs.pojo;

public class Heap {
	
	private GraphNode[] heap;
	private int[] pos;
	private int size;
	private int capacity;
	private final int FRONT = 0;
	
	public Heap(int capacity) {
		this.capacity = capacity;
		this.size = -1;
		this.heap = new GraphNode[capacity];
		this.pos = new int[capacity];
	}
	
	public boolean isEmpty() {
		if(size < 0)
			return true;
		else
			return false;
	}
	
	public void setGraphNode(GraphNode graphNode, int i) {
		size++;
		heap[i] = graphNode;
	}
	
	public void setPos(int v,int idx) {
		pos[v] = idx;
	}
	
	public int getPos(int v) {
		return pos[v];
	}
	
	private int parent(int i) {
		return (i-1)/2;
	}
	
	private int leftChild(int i) {
		return (2*i) + 1;
	}

	private int rightChild(int i) {
		return (2*i) + 2;
	}

	private boolean isLeaf(int pos) {
		return (pos>=(size/2) && pos<=size);
	}
	
	public boolean isInHeap(int vtx) {
		if(pos[vtx] <= size) 
			return true;
		return false;
	}
	
	private void swap(int fpos, int spos) {
		GraphNode temp = heap[fpos];
		heap[fpos] = heap[spos];
		heap[spos] = temp;
	}
	
	public void insert(GraphNode e) {
		heap[++size] = e;
		int current = size;
		pos[e.getVertex()] = current;
		while(current != FRONT && (heap[current].getDist() < heap[parent(current)].getDist())) {
			swap(current,parent(current));
			setPos(heap[current].getVertex(),current);
			setPos(heap[parent(current)].getVertex(),parent(current));
			
			current = parent(current);
		}
	}
	
	public GraphNode extractMin() {
		//Remove top node
		GraphNode popped = heap[FRONT];
		setPos(popped.getVertex(),size);
		
		//Replace top node with last node and update position
		heap[FRONT] = heap[size--];
		setPos(heap[FRONT].getVertex(), FRONT);
		
		
		minHeapify(FRONT);
		return popped;
	}
	
	public void decreaseKey(int vtx, int value) {
		int postn = pos[vtx];
		heap[postn].setDist(value);
		
		int current = postn;
		while(current != FRONT && (heap[current].getDist() < heap[parent(current)].getDist())) {
			swap(current,parent(current));
			setPos(heap[current].getVertex(),current);
			setPos(heap[parent(current)].getVertex(),parent(current));
			
			current = parent(current);
		}
		
	}
	
	private void minHeapify(int postn){
		if(!isLeaf(postn)) {
			int left = leftChild(postn);
			int right = rightChild(postn);
			int smallest = postn;
			
			if(heap[smallest].getDist() > heap[left].getDist()) {
				smallest = left;
			}
			
			if(heap[smallest].getDist() > heap[right].getDist()) {
				smallest = right;
			}
			
			if(smallest != postn) {
				swap(postn,smallest);
				setPos(heap[postn].getVertex(),postn);
				setPos(heap[smallest].getVertex(),smallest);
				
				minHeapify(smallest);
			}
		}
	}
	
}
