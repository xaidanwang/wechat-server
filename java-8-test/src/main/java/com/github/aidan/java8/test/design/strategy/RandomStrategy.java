package com.github.aidan.java8.test.design.strategy;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/5/20 16:55
 */
public class RandomStrategy implements Strategy {

	private Random random;

	public RandomStrategy(Random random) {
		this.random = new Random();
	}

	@Override
	public Hand nextHand() {
		return Hand.getHand(random.nextInt(3));
	}

	@Override
	public void study(boolean win) {

	}
}
