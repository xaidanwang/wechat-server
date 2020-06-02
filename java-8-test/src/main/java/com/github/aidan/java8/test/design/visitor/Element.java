package com.github.aidan.java8.test.design.visitor;

/**
 * @author wang yi fei
 * @date 2019/5/24 14:50
 */
public interface Element {
	public abstract void accept(Visitor visitor);
}
