package com.github.aidan.java8.test.design.strategy;

/**
 * @author wang yi fei
 * @date 2019/5/20 13:47
 */
public interface Strategy {
	Hand nextHand();
	void study(boolean win);
}
