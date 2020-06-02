package com.github.aidan.java8.test.producer.consumer;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/12/13 16:34
 */
public class EaterThread extends Thread {
	private final Random random;
	private final Table table;

	public EaterThread(String name, Table table, long seed) {
		super(name);
		this.random = new Random(seed);
		this.table = table;
	}

	@Override
	public void run() {

		try {
			while (true){
				String cake = table.take();
				Thread.sleep(random.nextInt(10000));
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
