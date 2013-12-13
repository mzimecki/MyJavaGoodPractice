package com.zimek.concurrent.blocks.transferqueue;

import java.util.concurrent.TransferQueue;

public abstract class WorkingThread extends Thread {
	protected final TransferQueue<WorkUnit<WorkToBeDone>> workingQueue;
	protected final int pauseTime;
	private boolean shutdown = false;

	public WorkingThread(TransferQueue<WorkUnit<WorkToBeDone>> aWorkingQueue, int aPause) {
		this.workingQueue = aWorkingQueue;
		this.pauseTime = aPause;
	}

	public synchronized void shutdown() {
		shutdown = true;
	}

	@Override
	public void run() {
		while (!shutdown) {
			doAction();
			try {
				Thread.sleep(pauseTime);
			} catch (InterruptedException e) {
				shutdown();
			}
		}
	}

	public abstract void doAction();
}
