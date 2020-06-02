package com.github.aidan.java8.test.design.prototype.framework;

/**
 * @author wang yi fei
 * @date 2019/5/13 16:38
 */
public interface Product extends Cloneable{
	public void use(String s);
	public Product createClone();
}
