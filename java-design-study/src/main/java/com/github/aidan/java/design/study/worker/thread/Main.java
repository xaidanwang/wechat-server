package com.github.aidan.java.design.study.worker.thread;

/**
 * @author wang yi fei
 * @date 2020/5/11 17:08
 */
public class Main {

	public static void main(String[] args) {
		Channel channel = new Channel(5);
		channel.startWorkers();
		new ClientThread("Alice",channel).start();
		new ClientThread("Bobby",channel).start();
		new ClientThread("Chris",channel).start();
	}
}
