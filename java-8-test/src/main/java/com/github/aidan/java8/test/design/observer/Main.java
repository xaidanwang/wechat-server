package com.github.aidan.java8.test.design.observer;

/**
 * @author wang yi fei
 * @date 2019/5/28 19:29
 */
public class Main {

	public static void main(String[] args) {
/*		NumberGenerator generator = new RandomNumberGenerator();
		Observer observer1 = new DigitObserver();
		Observer observer2 = new GraphObserver();
		generator.addObserver(observer1);
		generator.addObserver(observer2);
		generator.execute();*/
		NumberGenerator generator = new IncrementalNumberGenerator(10,50,5);
		Observer observer1 = new DigitObserver();
		Observer observer2 = new GraphObserver();
		generator.addObserver(observer1);
		generator.addObserver(observer2);
		generator.execute();

	}
}
