package com.github.aidan.java8.test.readwriter;

/**
 * @author wang yi fei
 * @date 2019/12/20 15:26
 */
public class Main {

	public static void main(String[] args) {

		Main main = new Main();
		try {
			main.test();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println(10000000);
		}
	}

	private void test() throws InterruptedException {
		wait();
	}
}
