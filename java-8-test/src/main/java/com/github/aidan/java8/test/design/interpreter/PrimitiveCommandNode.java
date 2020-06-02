package com.github.aidan.java8.test.design.interpreter;

/**
 * @author wang yi fei
 * @date 2019/6/14 14:28
 */
public class PrimitiveCommandNode extends Node {

	private String name;
	@Override
	public void parse(Context context) throws ParseException {
		name = context.currentToken();
		context.skipToken(name);
		if (!name.equals("go") && !name.equals("right") && !name.equals("left")){
			throw new ParseException(name + " is  undefined");
		}
	}
	public String toString(){
		return name;
	}
}
