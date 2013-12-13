package com.zimek.concurrent.blocks.latches;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Using count-down Latch a synchronization aid that allows one or more threads
 * to wait until a set of operations being performed in other threads completes.
 */
public class Latch extends Thread {
	private static int MAX_THREADS = 4;
	private final String ident;
	private final CountDownLatch latch;

	public Latch(String ident_, CountDownLatch cdl_) {
		ident = ident_;
		latch = cdl_;
	}

	public String getIdentifier() {
		return ident;
	}

	public void initialize() {
		try {
			//for eg. here will be quite long initialization
			//using sleep to emulate
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}

	public void run() {
		initialize();
	}

	public static void main(String[] a) {
		final int quorum = 1 + (int) (MAX_THREADS / 2);
		final CountDownLatch cdl = new CountDownLatch(quorum);

		final Set<Latch> nodes = new HashSet<>();
		System.out.println("Initializing threads...");
		try {
			for (int i = 0; i < MAX_THREADS; i++) {
				Latch local = new Latch("localhost:" + (9000 + i), cdl);
				nodes.add(local);
				local.start();
			}
			cdl.await();
			System.out.println("Latches counted down! Now I can move further!");
		} catch (InterruptedException e) {

		} finally {
		}
	}
}