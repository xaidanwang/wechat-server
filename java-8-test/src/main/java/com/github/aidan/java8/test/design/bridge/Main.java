package com.github.aidan.java8.test.design.bridge;

/**
 * @author wang yi fei
 * @date 2019/5/18 15:28
 */
public class Main {
	public static void main(String[] args) {
		Display d1 = new CountDisplay(new StringDisplayImpl("Hello, China."));
		Display d2 = new CountDisplay(new StringDisplayImpl("Hello, World."));
		CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello, Universe."));
		d1.display();
		d2.display();
		d3.display();
		d3.multiDisplay(5);
		RandomDisplay d4 = new RandomDisplay(new StringDisplayImpl("Hello, China."));
		d4.randomDisplay(4);


		CountDisplay d5 = new CountDisplay(new FileDisplayImpl("text.txt"));
		d5.multiDisplay(1);
	}
}
