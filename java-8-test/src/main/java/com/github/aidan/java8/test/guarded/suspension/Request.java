package com.github.aidan.java8.test.guarded.suspension;

/**
 * @author wang yi fei
 * @date 2019/11/30 15:23
 */
public class Request {
	private final String name;

	public Request(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "{\"Request\":{"
				+ "\"name\":\""
				+ name + '\"'
				+ "}}";

	}
}
