package com.zimek.concurrent.blocks.transferqueue;

public class WorkUnit<T> {
	private final T workUnit;
	
	public WorkUnit(T aWorkUnit) {
		this.workUnit = aWorkUnit;
	}
	
	public T getWork() {
		return workUnit;
	}
}
