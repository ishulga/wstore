package com.shulga.persistance.infinispan;

public class Counter {
	private static Long count = Long.valueOf(0);

	public static Long next() {
		return count++;
	}
	
	public static Long get() {
		return count;
	}
	
	public static String nextStr() {
		count++;
		return count.toString();
	}
	
	public static String getStr() {
		return count.toString();
	}
}
