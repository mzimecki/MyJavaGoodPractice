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
		latch.countDown();
	}

	public void run() {
		initialize();
	}

	public static void main(String[] a) {
		final int quorum = 1 + (int) (MAX_THREADS / 2);
		final CountDownLatch cdl = new CountDownLatch(quorum);

		final Set<Latch> nodes = new HashSet<>();
		try {
			for (int i = 0; i < MAX_THREADS; i++) {
				Latch local = new Latch("localhost:" + (9000 + i), cdl);
				nodes.add(local);
				local.start();
			}
			cdl.await();
		} catch (InterruptedException e) {

		} finally {
		}
	}
}