package com.github.aidan.java8.test.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import java.lang.reflect.Method;

/**
 * @author wang yi fei
 * @date 2019/10/31 13:56
 */
public class HelloServiceCglib implements MethodInterceptor {

	private Object target;

	public Object getInstance(Object target){
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		// 回调方法
		enhancer.setCallback(this);
		//  创建代理对象
		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, net.sf.cglib.proxy.MethodProxy methodProxy) throws Throwable {
		System.out.println("################我是GLIB的动态代理#######################");
		// 反射方法前调用
		System.out.println("我准备说 hello");
		Object returnObj = methodProxy.invokeSuper(o,objects);
		// 反射方法后调用
		System.out.println("我说过Hello 了");
		return returnObj;
	}
}
