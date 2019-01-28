package com.coursera.huffman.core;

import com.coursera.huffman.pojo.MinHeap;
import com.coursera.huffman.pojo.Node;

public class HuffmanCoding {
	
	public static int max(int a, int b) {
		return a > b ? a : b;
	}
	
	public static int min(int a, int b) {
		return a < b ? a : b;
	}
	
	public static int getMaxDepth(Node root) {
		if(root == null)
			return 0;
		else
			return 1 + max(getMaxDepth(root.left),getMaxDepth(root.right));
	}
	
	public static int getMinDepth(Node root) {
		if(root == null)
			return 0;
		else
			return 1 + min(getMinDepth(root.left),getMinDepth(root.right));
	}
	
	public static int[] solve(int[] weights, int n) {
		
		int depths[] = new int[2];
		MinHeap minHeap = new MinHeap(n);
		Node root = null;
		
		for(int i=0; i<n; ++i) {
			Node node = new Node(weights[i]);
			minHeap.insert(node);
		}
		
		//Huffman Coding Algorithm
		while(minHeap.size() > 1) {
			Node leftNode = minHeap.extractMin();
			Node rightNode = minHeap.extractMin();
			
			
			Node newNode = new Node(leftNode.value + rightNode.value);
			newNode.left = leftNode;
			newNode.right = rightNode;
			
			minHeap.insert(newNode);
			
			root = newNode;
		}
		
		int minDepth = getMinDepth(root) - 1; //decrementing 1 for 0 level root
		int maxDepth = getMaxDepth(root) - 1; 
		depths[0] = minDepth;
		depths[1] = maxDepth;
		
		return depths;
			
	}

}
