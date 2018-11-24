package com.coursera.jobscheduling.pojo;

public class OptimalSchedule {
	
	private static Job[] outputJobs;
	
	private static void merge(int l, int m, int r, double[] scores, Job[] jobs) {
		int sizeLeft = m-l+1;
		int sizeRight = r-m;
		
		double[] leftArr = new double[sizeLeft];
		Job[] leftJobs = new Job[sizeLeft];
				
		double[] rightArr = new double[sizeRight];
		Job[] rightJobs = new Job[sizeRight];
		
		for(int i=0; i<sizeLeft; ++i) {
			leftArr[i] = scores[l+i];
			leftJobs[i] = jobs[l+i];
		}
		for(int i=0; i<sizeRight; ++i) {
			rightArr[i] = scores[m+1+i];
			rightJobs[i] = jobs[m+1+i];
		}
		
		int i=0,j=0,k=l;
		while(i<sizeLeft && j<sizeRight) {
			if(leftArr[i] >= rightArr[j]) {
				scores[k] = leftArr[i];
				jobs[k] = leftJobs[i];
				++i;
			}else if(leftArr[i] < rightArr[j]) {
				scores[k] = rightArr[j];
				jobs[k] = rightJobs[j];
				++j;
			}
//			}else {
//				if(leftJobs[i].getWeight() >= rightJobs[j].getWeight()) {
//					scores[k] = leftArr[i];
//					jobs[k] = leftJobs[i];
//					++i;
//				}else {
//					scores[k] = rightArr[j];
//					jobs[k] = rightJobs[j];
//					++j;
//				}
//			}
			++k;
		}
		
		while(i<sizeLeft) {
			scores[k] = leftArr[i];
			jobs[k] = leftJobs[i];
			++i;
			++k;
		}
		
		while(j<sizeRight) {
			scores[k] = rightArr[j];
			jobs[k] = rightJobs[j];
			++j;
			++k;
		}
		
	}
	
	private static void mergeSort(int l, int r, double[] scores, Job[] jobs) {
		if(l < r) {
			int m = (l+r)/2;
			mergeSort(l,m,scores,jobs);
			mergeSort(m+1,r,scores,jobs);
			merge(l,m,r,scores,jobs);
		}
	}
	
	private static void sort(double[] scores, Job[] jobs) {
		mergeSort(0,scores.length-1,scores,jobs);
	}
	
	public static Job[] getSchedule(Job[] jobs, int size) {
		outputJobs = new Job[size];
		double[] scores = new double[size];

		for(int i=0; i<size; ++i) {
			outputJobs[i] = new Job(jobs[i].getWeight(),jobs[i].getLength());
		}
				
		for(int i=0; i<size; ++i) { 
			scores[i] = jobs[i].getWeight()/jobs[i].getLength();
		}
		
		sort(scores,outputJobs);
		
		return outputJobs;
	}

}
