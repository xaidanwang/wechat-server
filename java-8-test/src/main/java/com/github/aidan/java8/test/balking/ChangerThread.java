package com.github.aidan.java8.test.balking;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/12/2 20:06
 */
public class ChangerThread extends Thread {

	private final Data data;
	private final Random random = new Random();

	public ChangerThread(String name, Data data) {
		super(name);
		this.data = data;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; true; i++){
				data.change("No." + i);
				Thread.sleep(random.nextInt(1000));
				data.save();
			}
		}catch (Exception e){

		}
	}
}
