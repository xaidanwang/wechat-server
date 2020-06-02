package com.github.aidan.java8.test.design.interpreter;

import java.util.ArrayList;

/**
 * @author wang yi fei
 * @date 2019/6/14 14:13
 */
public class CommandListNode extends Node {

	private ArrayList list = new ArrayList();
	@Override
	public void parse(Context context) throws ParseException {
		while (true){
			if (context.currentToken() == null){
				throw new ParseException("Missing 'end'");
			}else if (context.currentToken().equals("end")){
				context.skipToken("end");
				break;
			}else {
				Node commandNode = new CommandNode();
				commandNode.parse(context);
				list.add(commandNode);
			}
		}
	}

	public String toString(){
		return list.toString();
	}
}
