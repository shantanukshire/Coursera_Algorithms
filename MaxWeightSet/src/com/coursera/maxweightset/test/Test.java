package com.coursera.maxweightset.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.coursera.maxweightset.core.MaximumWeightSet;

public class Test {
	
	private static final String FILE_PATH = "./data/mwis.txt";
	
	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get(FILE_PATH);
		Scanner sc = new Scanner(path);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		int totalVertices = Integer.parseInt(sc.nextLine());
		
		int[] weights = new int[totalVertices];
		
		for(int i=0; i<totalVertices; ++i) {
			weights[i] = Integer.parseInt(sc.nextLine());
		}
		
		MaximumWeightSet.solve(weights, totalVertices);
	}

}
