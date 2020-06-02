package com.github.aidan.java8.test.producer.consumer.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author wang yi fei
 * @date 2019/12/15 15:06
 */
public class Main {
	public static void main(String[] args) {
		Exchanger<char[]> exchanger = new Exchanger<>();
		char[] buffer1 = new char[10];
		char[] buffer2 = new char[10];
		new ProduceThread(exchanger,buffer1,314159).start();
		new ConsumerThread(exchanger,buffer2,265358).start();
	}
}
