package com.github.aidan.java8.test.design.interpreter;

import java.awt.*;

/**
 * @author wang yi fei
 * @date 2019/6/14 14:24
 */
public class RepeatCommandNode extends Node{
	private int number;
	private Node commandListNode;

	@Override
	public void parse(Context context) throws ParseException {
		context.skipToken("repeat");
		number = context.currentNumber();
		context.nextToken();
		commandListNode = new CommandListNode();
		commandListNode.parse(context);
	}

	public String toString(){
		return "[repeat "+ number + " " + commandListNode + "]";
	}
}
