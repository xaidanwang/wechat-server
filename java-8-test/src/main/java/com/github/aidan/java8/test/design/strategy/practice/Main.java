package com.github.aidan.java8.test.design.strategy.practice;

/**
 * @author wang yi fei
 * @date 2019/5/20 17:49
 */
public class Main {
	public static void main(String[] args) {
		String[] data = {"Dumpty", "Bowman", "Carroll", "Elfland", "Alice"};
		SortAndPrint sap = new SortAndPrint(data,new SelectionSorter());
		sap.execute();
	}
}
