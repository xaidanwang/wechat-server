package com.github.aidan.java8.test.design.singleton;

/**
 * @author wang yi fei
 * @date 2019/5/13 15:16
 */
public class Singleton {
	private static Singleton ourInstance = new Singleton();

	public static Singleton getInstance() {
		return ourInstance;
	}

	private Singleton() {
		System.out.println("生成了一个实例。");
	}
}
