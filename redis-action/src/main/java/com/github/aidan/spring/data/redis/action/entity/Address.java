package com.github.aidan.spring.data.redis.action.entity;

/**
 * @author wang yi fei
 * @date 2019/7/23 17:45
 */
public class Address {
	private String id;
	private String name;

	public Address(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Address() {
	}


	@Override
	public String toString() {
		return "{\"Address\":{"
				+ "\"id\":\""
				+ id + '\"'
				+ ",\"name\":\""
				+ name + '\"'
				+ "}}";

	}
}
