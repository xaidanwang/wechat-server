package com.github.aidan.java8.test.design.strategy;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/5/20 14:21
 */
public class WinStrategy implements Strategy{

	private Random random;
	private boolean won = false;
	private Hand prevHand;

	public WinStrategy(int seed) {
		this.random =  new Random();
	}

	@Override
	public Hand nextHand() {
		if (!won){
			 prevHand = Hand.getHand(random.nextInt(3));
		}
		return prevHand;
	}

	@Override
	public void study(boolean win) {
		won = win;
	}
}
