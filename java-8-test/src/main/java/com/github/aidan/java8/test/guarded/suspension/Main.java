package com.github.aidan.java8.test.guarded.suspension;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/11/30 15:35
 */
public class Main {

	public static void main(String[] args) {
		RequestQueue requestQueue = new RequestQueue();
		new ClientThread("Alice",new Random(1000L),requestQueue).start();
		new ServerThread("Bobby",new Random(2000L),requestQueue).start();
	}
}
