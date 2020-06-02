package com.github.aidan.java8.test.threadperMessage;

/**
 * @author wang yi fei
 * @date 2019/12/22 9:55
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host = new Host();
		host.request(10,'A');
		host.request(20,'B');
		host.request(30,'C');
		System.out.println("main END");
	}
}
