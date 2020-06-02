package com.github.aidan.java8.test.guarded.suspension;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/11/30 15:28
 */
public class ClientThread extends Thread {
	private final Random random;
	private final RequestQueue requestQueue;

	public ClientThread(String name, Random random, RequestQueue requestQueue) {
		super(name);
		this.random = new Random();
		this.requestQueue = requestQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10000; i++){
			Request request = new Request("No."+i);
			System.out.println(Thread.currentThread().getName() + " requests " + request);
			requestQueue.putRequest(request);

			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
