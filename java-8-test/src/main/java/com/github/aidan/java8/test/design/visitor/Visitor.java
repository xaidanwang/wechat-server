package com.github.aidan.java8.test.design.visitor;

/**
 * @author wang yi fei
 * @date 2019/5/24 14:25
 */
public abstract class Visitor {
	public abstract void visit(File file);
	public abstract void visit(Director director);
}
