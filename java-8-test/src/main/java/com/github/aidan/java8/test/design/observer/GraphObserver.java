package com.github.aidan.java8.test.design.observer;

/**
 * @author wang yi fei
 * @date 2019/5/28 19:27
 */
public class GraphObserver implements Observer {

	@Override
	public void update(NumberGenerator generator) {

		System.out.println("GraphObserver:");
		int count = generator.getNumber();
		for (int i = 0;i <count; i++){
			System.out.print("*");
		}
		System.out.println("");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
