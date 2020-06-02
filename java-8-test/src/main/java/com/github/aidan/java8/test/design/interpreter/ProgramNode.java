package com.github.aidan.java8.test.design.interpreter;

/**
 * @author wang yi fei
 * @date 2019/6/14 14:10
 */
public class ProgramNode extends Node {

	private Node commandListNode;
	@Override
	public void parse(Context context) throws ParseException {
		context.skipToken("program");
		commandListNode = new CommandListNode();
		commandListNode.parse(context);
	}

	@Override
	public String toString() {
		return "[program " + commandListNode + "]";
	}
}
