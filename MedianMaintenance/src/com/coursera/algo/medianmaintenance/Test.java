package com.coursera.algo.medianmaintenance;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Test {
	
	private static final int TOTAL_NUMBERS = 10000;
	private static final String FILE_PATH = "./data/Median.txt";
	
	public static void main (String[] args) throws IOException {		
		
		int[] input = new int[TOTAL_NUMBERS];
		Path path = Paths.get(FILE_PATH);
		Scanner scn = new Scanner(path);
		scn.useDelimiter(System.getProperty("line.separator"));

		try {
			int counter = -1;
			while (scn.hasNext()) {
				String line = new String(scn.next());
				input[++counter] = Integer.parseInt(line);
			}
			
			int[] median = MedianMaintenance.run(input);
			int sum = 0;
			for(int i=0; i<median.length; ++i) {
				sum += median[i];
			}
			System.out.println("Output (Sum of medians)mod(10,000) = " + sum%10000);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			scn.close();
		}
	}

}
