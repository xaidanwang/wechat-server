package com.github.aidan.java8.test.design.singleton.practice;

/**
 * @author wang yi fei
 * @date 2019/5/13 15:38
 */
public class TicketMaker {
	private static TicketMaker ourInstance = new TicketMaker();
	private int ticket = 1000;
	public static TicketMaker getInstance() {
		return ourInstance;
	}

	private TicketMaker() {
	}

	// 不加 synchronized 多线程情况会出现相同编号
	public synchronized int getTicket() {
		return ticket++;
	}
}
