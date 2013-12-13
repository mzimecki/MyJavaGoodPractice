package com.zimek.concurrent.blocks.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * java.util.concurrent.atomic is a small toolkit of 
 * classes that support lock-free thread-safe programming on single variables.
 */
public class Atomic {
	
	/**
	 * Sample usage of AtomicLong
	 */
	private static class SequenceNumber {
		private final AtomicLong sequenceNumber = new AtomicLong(0);
		public long nextId() {
			return sequenceNumber.incrementAndGet();
		}
	}
	
	public static void main(String [] args) {
		System.out.println(new SequenceNumber().nextId());
	}
}
