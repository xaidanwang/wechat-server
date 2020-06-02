package com.github.aidan.java8.test.readwriter;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/12/20 15:27
 */
public class WriterThread extends Thread {
	private static final Random randow = new Random();
	private final Data data;
	private final String filler;
	private int index = 0;

	public WriterThread(Data data, String filler) {
		this.data = data;
		this.filler = filler;
	}

	@Override
	public void run(){
		try {
			while (true) {
				char c = nextchar();
				data.write(c);
				Thread.sleep(randow.nextInt(3000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private char nextchar(){
		char c = filler.charAt(index);
		index++;
		if (index >= filler.length()){
			index = 0;
		}
		return c;
	}
}
