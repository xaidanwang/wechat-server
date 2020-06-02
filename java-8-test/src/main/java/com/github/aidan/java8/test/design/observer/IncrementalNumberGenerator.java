package com.github.aidan.java8.test.design.observer;

/**
 * @author wang yi fei
 * @date 2019/5/30 11:06
 */
public class IncrementalNumberGenerator extends NumberGenerator{
	private int number;
	private int endNumber;
	private int step;

	public IncrementalNumberGenerator(int number, int endNumber, int step) {
		this.number = number;
		this.endNumber = endNumber;
		this.step = step;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void execute() {
		for (int i = number;i < endNumber;i += step){
			number = i;
			notifyObservers();
		}
	}
}
