package com.github.aidan.java8.test.design.singleton;

/**
 * @author wang yi fei
 * @date 2020/2/25 16:37
 */
public class TestPerson {
	private static TestPerson ourInstance = new TestPerson();

	public static TestPerson getInstance() {
		return ourInstance;
	}

	private TestPerson() {
	}
}
