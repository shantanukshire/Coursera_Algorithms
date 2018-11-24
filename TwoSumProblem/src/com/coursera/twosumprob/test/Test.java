package com.coursera.twosumprob.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	
	private static final int TOTAL_NUMBERS = 1000000;
	private static final long INTVAL_START = -10000;
	private static final long INTVAL_END = 10000;
	private static final String FILE_PATH = "./data/algo1-programming_prob-2sum.txt";
	
	public static void main(String args[]) throws IOException {
		
		Long[] inputs = new Long[TOTAL_NUMBERS];
		HashMap<Long,Integer> numberMap = new HashMap<Long,Integer>();
		Path path = Paths.get(FILE_PATH);
		Scanner scn = new Scanner(path);
		scn.useDelimiter(System.getProperty("line.separator"));
		int counter = 0;
		
		try {
			while(scn.hasNext()) {
				Long l = new Long(scn.next());
				inputs[counter] = l;
				numberMap.put(l, 1);
				++counter;
			}
			
			System.out.println("Output: " + 
			Solution.solve(inputs, numberMap, INTVAL_START, INTVAL_END));
			
		}finally {
			scn.close();
		}
	}

}
