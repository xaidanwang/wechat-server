package com.github.aidan.java8.test.producer.consumer.exchanger;

/**
 * @author wang yi fei
 * @date 2019/12/20 14:52
 */
public class Something {
	public static void method(long x) throws InterruptedException {
		if (x != 0){
			Object object = new Object();
			object.wait(x);
		}
	}
}
