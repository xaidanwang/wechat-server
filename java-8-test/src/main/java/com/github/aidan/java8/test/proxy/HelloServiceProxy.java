package com.github.aidan.java8.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wang yi fei
 * @date 2019/10/31 11:04
 */
public class HelloServiceProxy implements InvocationHandler {
	private Object target;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("################我是JDK的动态代理#######################");
		Object result = null;
		// 反射方法调用
		System.out.println("我准备说 hello");
		// 执行方法，相当于调用 Helleo ServiceImpl 类的 sayHello 方法
		result =  method.invoke(target,args);
		//  反射方法后调用
		System.out.println(" 我说过 Hello 了");
		return result;
	}
	public Object bind(Object target){
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
	}
}
