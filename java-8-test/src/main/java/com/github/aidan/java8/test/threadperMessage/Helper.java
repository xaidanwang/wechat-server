package com.github.aidan.java8.test.threadperMessage;

/**
 * @author wang yi fei
 * @date 2019/12/22 9:56
 */
public class Helper {
	public void handle(int count,char c){
		System.out.println(" handel(" + count + ", " + c + ") BEGIN");
		for (int i = 0; i < count; i++) {
			slowly();
			System.out.print(c);
		}
		System.out.println("");
		System.out.println(" handel(" + count + ", " + c + ") END");
	}
	private void slowly(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
