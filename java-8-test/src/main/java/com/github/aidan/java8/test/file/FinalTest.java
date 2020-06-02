package com.github.aidan.java8.test.file;

/**
 * @author wang yi fei
 * @date 2019/12/19 11:03
 */
public class FinalTest {

	public static void main(String[] args) {

		System.out.println("==================");

		try {
			while (true){
				System.out.println("8888888888888888888888");
				Thread.sleep(1000);
				System.out.println("8888888888888888888888");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("=====================");
		}
	}
}
