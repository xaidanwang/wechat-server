package com.github.aidan.java8.test.design.observer;

/**
 * @author wang yi fei
 * @date 2019/5/28 19:26
 */
public class DigitObserver implements Observer {

	@Override
	public void update(NumberGenerator generator) {

		System.out.println("DigitObserver: "+ generator.getNumber());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
