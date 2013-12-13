package com.zimek.concurrent.blocks.transferqueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * TransferQueue (java7) BlockingQueue (java < 7) and WorkUnit pattern example.
 * wt1 and wt2 transfer work units to the queue. wt3 and wt4 wait for work, get it
 * from the queue and perform it.
 */
public class Main {
	public static void main(String [] args) {
		final TransferQueue<WorkUnit<WorkToBeDone>> workingQueue = new LinkedTransferQueue<>();
		
		WorkingThread wt1 = new WorkingThread(workingQueue, 100) {
			
			@Override
			public void doAction() {
				boolean served = false;
				WorkUnit<WorkToBeDone> workUnit = new WorkUnit<WorkToBeDone>(new WorkToBeDone("work 1"));
				System.out.println("Serving work " + workUnit.getWork().getName() + " unit to the queue");
				served = workingQueue.tryTransfer(workUnit); //tryTransfer(workUnit, 100, TimeUnit.MILLISECONDS);
				if (!served) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!Couldn't serve " + workUnit.getWork().getName());
					shutdown();
				}
			}
		};
		
		WorkingThread wt2 = new WorkingThread(workingQueue, 100) {
			
			@Override
			public void doAction() {
				boolean served = false;
				WorkUnit<WorkToBeDone> workUnit = new WorkUnit<WorkToBeDone>(new WorkToBeDone("work 2"));
				System.out.println("Serving work " + workUnit.getWork().getName() + " unit to the queue");
				served = workingQueue.tryTransfer(workUnit); //tryTransfer(workUnit, 100, TimeUnit.MILLISECONDS);
				if (!served) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!Couldn't serve " + workUnit.getWork().getName());
					shutdown();
				}
			}
		};
		
		WorkingThread wt3 = new WorkingThread(workingQueue, 10) {
			
			@Override
			public void doAction() {
				WorkUnit<WorkToBeDone> workUnit = null;
				try {
					workUnit = workingQueue.take();
					workUnit.getWork().doWork("wt3");
					//shutdown();
				} catch (InterruptedException e) {
					return;
				}
				
			}
		};
		
		WorkingThread wt4 = new WorkingThread(workingQueue, 10) {
			
			@Override
			public void doAction() {
				WorkUnit<WorkToBeDone> workUnit = null;
				try {
					workUnit = workingQueue.take();
					workUnit.getWork().doWork("wt4");
					//shutdown();
				} catch (InterruptedException e) {
					return;
				}
				
			}
		};
		
		wt1.start();
		wt2.start();
		wt3.start();
		wt4.start();
	}
	
	//TODO: Involve Executors? do experiments
	
	
}
