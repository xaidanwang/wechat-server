package com.github.aidan.java8.test.balking;

/**
 * @author wang yi fei
 * @date 2019/12/2 20:31
 */
public class Main {
	public static void main(String[] args) {
		Data data = new Data("data.txt","(empty)");
		new ChangerThread("ChangerThread",data).start();
		new SaverThread("SaverThread",data).start();
	}
}
