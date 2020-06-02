package com.github.aidan.java8.test.proxy;

import com.github.aidan.java8.test.proxy.cglib.HelloServiceCglib;

/**
 * @author wang yi fei
 * @date 2019/10/31 11:28
 */
public class HelloServiceMain {

	public static void main(String[] args) {
		HelloServiceProxy helloHandler = new HelloServiceProxy();
		HelloService proxy = (HelloService)helloHandler.bind(new HelloServiceImpl());
		proxy.sayHello("张三");

		HelloServiceCglib helloServiceCglib = new HelloServiceCglib();
		HelloService proxy1 = (HelloService)helloServiceCglib.getInstance(new HelloServiceImpl());
		System.out.println("=====================");
		proxy1.sayHello("小王");
	}
}
