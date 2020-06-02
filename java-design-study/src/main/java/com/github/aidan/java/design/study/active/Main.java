package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 10:51
 */
public class Main {

	public static void main(String[] args) {
		ActiceObject acticeObject = ActiceObjectFactory.createActiceObject();
		new MakerClientThread("Alice",acticeObject).start();
		new MakerClientThread("Bobby",acticeObject).start();
		new DisplayClientThread("Chris",acticeObject).start();
	}
}
