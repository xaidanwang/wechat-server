package com.github.aidan.java8.test.guarded.suspension;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/11/30 15:32
 */
public class ServerThread extends Thread{
	private final Random random;
	private final RequestQueue requestQueue;

	public ServerThread(String name, Random random, RequestQueue requestQueue) {
		super(name);
		this.random = random;
		this.requestQueue = requestQueue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++){
			Request request = requestQueue.getRequest();
			System.out.println(Thread.currentThread().getName() + " handles "  + request);
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
