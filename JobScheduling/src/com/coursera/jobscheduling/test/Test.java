package com.coursera.jobscheduling.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.coursera.jobscheduling.pojo.DifferenceSchedule;
import com.coursera.jobscheduling.pojo.Job;
import com.coursera.jobscheduling.pojo.OptimalSchedule;
import com.coursera.jobscheduling.pojo.WeightedCompletion;

public class Test {
	
    private static int TOTAL_SIZE = 0;
    private static final String FILE_PATH = "./data/jobs.txt";
	
	public static void main (String args[]) throws IOException {
		
		Job[] inputJobs;
		Job[] outputJobs;
		
		Path path = Paths.get(FILE_PATH); 
		Scanner sc = new Scanner(path);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		if(sc.hasNext()) {
			TOTAL_SIZE = Integer.parseInt(sc.next());
		}
		
		if(TOTAL_SIZE > 0) {
			inputJobs = new Job[TOTAL_SIZE];
			
			for(int i=0 ;i<TOTAL_SIZE; ++i) {
				String line = sc.next();
				String[] splitLine = line.split("\\s+");
				double weight = Double.parseDouble(splitLine[0]);
				double length = Double.parseDouble(splitLine[1]);
				inputJobs[i] = new Job(weight,length);
			}
			
			outputJobs =  DifferenceSchedule.getSchedule(inputJobs, TOTAL_SIZE);
			System.out.println("Difference Schedule Sum(Weighted-Completion) = " + WeightedCompletion.getSum(outputJobs));
			
			outputJobs =  OptimalSchedule.getSchedule(inputJobs, TOTAL_SIZE);
			System.out.println("Optimal Schedule Sum(Weighted-Completion) = " + WeightedCompletion.getSum(outputJobs));
		}

	}

}
