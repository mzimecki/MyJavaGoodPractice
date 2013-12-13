package com.zimek.concurrent.blocks.transferqueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class Main {
	public static void main(String [] args) {
		final TransferQueue<WorkUnit<WorkToBeDone>> workingQueue = new LinkedTransferQueue<>();
		
		WorkingThread wt1 = new WorkingThread(workingQueue, 10) {
			
			@Override
			public void doAction() {
				boolean served = false;
				WorkUnit<WorkToBeDone> workUnit = new WorkUnit<WorkToBeDone>(new WorkToBeDone("work 1"));
				try {
					System.out.println("Serving work " + workUnit.getWork().getName() + " unit to the queue");
					served = workingQueue.tryTransfer(workUnit, 100, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!served) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!Couldn't serve " + workUnit.getWork().getName());
					//shutdown();
				}
			}
		};
		
		WorkingThread wt2 = new WorkingThread(workingQueue, 10) {
			
			@Override
			public void doAction() {
				WorkUnit<WorkToBeDone> workUnit = null;
				try {
					workUnit = workingQueue.take();
					workUnit.getWork().doWork();
					//shutdown();
				} catch (InterruptedException e) {
					return;
				}
				
			}
		};
		
		wt1.start();
		wt2.start();
	}
	
	//TODO: Involve Executors? do experiments
	
	
}
