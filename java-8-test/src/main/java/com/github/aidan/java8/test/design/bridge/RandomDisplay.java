package com.github.aidan.java8.test.design.bridge;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/5/18 16:32
 */
public class RandomDisplay extends Display {

	private Random random = new Random();
	public RandomDisplay(DisplayImpl impl) {
		super(impl);
	}

	public void randomDisplay(int times){

		int time = random.nextInt(times);
		open();
		for (int i = 0;i<time;i++){
			print();
		}
		close();
	}
}
