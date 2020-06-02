package com.github.aidan.java8.test.proxy;

/**
 * @author wang yi fei
 * @date 2019/10/31 11:04
 */
public class HelloServiceImpl implements HelloService{


	@Override
	public void sayHello(String name) {
		System.out.println("hello " + name);
	}


}
