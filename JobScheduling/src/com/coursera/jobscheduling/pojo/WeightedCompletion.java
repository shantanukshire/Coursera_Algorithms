package com.coursera.jobscheduling.pojo;

import java.math.BigDecimal;

public class WeightedCompletion {
	
	public static BigDecimal getSum(Job[] jobs) {
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal completion_time = BigDecimal.ZERO;
		
		for(int j=0; j<jobs.length; ++j) {
			Job job = jobs[j];
			completion_time = completion_time.add(new BigDecimal(job.getLength()));			
			ret = ret.add(completion_time.multiply(new BigDecimal(job.getWeight())));
		}
		
		return ret;
	}

}
