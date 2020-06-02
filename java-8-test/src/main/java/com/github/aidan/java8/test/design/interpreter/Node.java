package com.github.aidan.java8.test.design.interpreter;

/**
 * @author wang yi fei
 * @date 2019/6/14 14:09
 */
public abstract class Node {
	public abstract void parse(Context context)throws ParseException;
}
