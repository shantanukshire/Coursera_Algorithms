package com.coursera.jobscheduling.pojo;

public class Job {
	
	private double weight;
	private double length;
	
	public Job() {
		
	}
	
	public Job(double w, double l) {
		this.weight = w;
		this.length = l;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
	

}
