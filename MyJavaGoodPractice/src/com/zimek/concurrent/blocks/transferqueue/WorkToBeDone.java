package com.zimek.concurrent.blocks.transferqueue;

public class WorkToBeDone {
	private String name;
	
	public WorkToBeDone(String aName) {
		this.name = aName;
	}
	
	public void doWork() {
		System.out.println("Doing work: " + name);
	}
	
	public String getName() {
		return this.name;
	}
}
