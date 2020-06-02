package com.github.aidan.java8.test.threadperMessage;

/**
 * @author wang yi fei
 * @date 2019/12/22 9:56
 */
public class Host {
	private final Helper helper = new Helper();
	public void request(int count, char c){
		System.out.println(" request(" + count + ", " + c + ") BEGIN");
		new Thread(){
			@Override
			public void run() {
				helper.handle(count, c);
			}
		}.start();
		System.out.println(" request(" + count + ", " + c + ") END");
	}
}
