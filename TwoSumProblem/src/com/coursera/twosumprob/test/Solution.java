package com.coursera.twosumprob.test;

import java.util.HashMap;
import java.util.Iterator;

public class Solution {
	
	public static boolean solveUtil(long t, Long[] nums, 
			HashMap<Long,Integer> lookup) {
		boolean ret = false;
		
		Iterator<Long> keyItr = lookup.keySet().iterator();
		while(keyItr.hasNext()) {
			long x = keyItr.next();
			long y = t - x;
			if(x != y && lookup.containsKey(y)) {
				ret = true;
				break;
			}
		}
		
//		for(Long x : nums) {
//			long y = t - x;
//			if(lookup.containsKey(y)) {
//				ret = true;
//				break;
//			}	
//		}
		
		return ret;
	}
	
	public static int solve(Long[] nums, HashMap<Long,Integer> lookup, 
			long target_start, long target_end) {
		int totalTargets = 0;
		
		for(long target = target_start; target <= target_end; ++target) {
			if(solveUtil(target,nums,lookup)) {
				System.out.println("Target achieved for : " + target);
				++totalTargets;
			}
		}
		
		return totalTargets;
	}

}
