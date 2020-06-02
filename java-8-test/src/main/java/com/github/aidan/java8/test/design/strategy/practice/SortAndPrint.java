package com.github.aidan.java8.test.design.strategy.practice;

/**
 * @author wang yi fei
 * @date 2019/5/20 17:47
 */
public class SortAndPrint {
	Comparable[] data;
	Sorter sorter;

	public SortAndPrint(Comparable[] data, Sorter sorter) {
		this.data = data;
		this.sorter = sorter;
	}
	public void execute(){
		print();
		sorter.sort(data);
		print();
	}

	public void print(){
		for (int i=0;i< data.length; i++){
			System.out.print(data[i] + ", ");
		}
		System.out.println("");
	}
}
