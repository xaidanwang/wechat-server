package com.github.aidan.java8.test.design.observer;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/5/28 19:23
 */
public class RandomNumberGenerator extends NumberGenerator {

	private int number;
	private Random random = new Random();
	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void execute() {

		for (int i =0; i< 20;i++){
			number = random.nextInt(50);
			notifyObservers();
		}
	}
}
