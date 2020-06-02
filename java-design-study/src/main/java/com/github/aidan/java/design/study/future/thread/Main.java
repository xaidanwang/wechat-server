package com.github.aidan.java.design.study.future.thread;

/**
 * @author wang yi fei
 * @date 2020/5/15 15:00
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host = new Host();
		Data data1 = host.request(10,'A');
		Data data2 = host.request(10,'B');
		Data data3 = host.request(10,'C');
		System.out.println("main otherJob BEGIN");
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("main otherJob End");
		System.out.println("data1 = " + data1.getContent());
		System.out.println("data2 = " + data2.getContent());
		System.out.println("data3 = " + data3.getContent());
		System.out.println("main END");




//		StaticFieldTest staticFieldTest = new StaticFieldTest();
//		StaticFieldTest staticFieldTest1 = new StaticFieldTest();

	}
}
