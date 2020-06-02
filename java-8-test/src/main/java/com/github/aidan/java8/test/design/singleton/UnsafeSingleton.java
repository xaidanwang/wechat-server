package com.github.aidan.java8.test.design.singleton;

/**
 * @author wang yi fei
 * @date 2019/5/13 15:51
 */
public class UnsafeSingleton {
	private static  UnsafeSingleton singleton = null;

	private UnsafeSingleton(){

	}

	public synchronized static UnsafeSingleton getInstance(){
		if (singleton == null){
			singleton = new UnsafeSingleton();
		}
		return singleton;
	}
}
