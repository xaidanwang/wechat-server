package com.github.aidan.java8.test.immutable;

/**
 * @author wang yi fei
 * @date 2019/11/28 16:15
 */
public final class Person {
	private final String name;
	private final String address;
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	@Override
	public String toString() {
		return "{\"Person\":{"
				+ "\"name\":\""
				+ name + '\"'
				+ ",\"address\":\""
				+ address + '\"'
				+ "}}";
	}
}
