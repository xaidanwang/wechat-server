package com.github.aidan.java8.test.design.command;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author wang yi fei
 * @date 2019/6/13 14:47
 */
public class MacroCommand implements Command {

	private Stack commands = new Stack();
	@Override
	public void execute() {
		Iterator it = commands.iterator();
		while (it.hasNext()){
			((Command)it.next()).execute();
		}
	}

	//  添加命令
	public void append(Command command){
		if (command != this){
			commands.push(command);
		}
	}

	//   删除最后一条命令
	public void undo(){
		if (!commands.empty()){
			commands.pop();
		}
	}

	//  输出所有命令
	public void clear(){
		commands.clear();
	}
}
