package com.zimek.concurrent.blocks.transferqueue;

public class WorkToBeDone {
	private String name;
	
	public WorkToBeDone(String aName) {
		this.name = aName;
	}
	
	public void doWork(String workerName) {
		System.out.println("Doing work: " + name + " by " + workerName);
	}
	
	public String getName() {
		return this.name;
	}
}
