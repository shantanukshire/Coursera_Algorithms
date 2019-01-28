package com.coursera.huffman.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.coursera.huffman.core.HuffmanCoding;

public class Test {
	
//	private static final String FILE_PATH = "./data/simple.txt";
	private static final String FILE_PATH = "./data/huffman.txt";
	
	public static void main(String[] args) throws IOException {
		
		int totalSymbols = 0;
		int[] weights;
		
		Path path = Paths.get(FILE_PATH);
		Scanner sc = new Scanner(path);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		totalSymbols = Integer.parseInt(sc.nextLine());
		weights = new int[totalSymbols];
		
		for(int i=0; i<totalSymbols; ++i) {
			weights[i] = Integer.parseInt(sc.nextLine());
		}
		
		int[] depths = HuffmanCoding.solve(weights, totalSymbols);
		
		System.out.println("Min Length of codeword = "  + depths[0]);
		System.out.println("Max Length of codeword = "  + depths[1]);
	}

}
