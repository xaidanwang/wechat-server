package com.github.aidan.java8.test.design.bridge.practice;

import com.github.aidan.java8.test.design.bridge.DisplayImpl;

/**
 * @author wang yi fei
 * @date 2019/5/18 19:51
 */
public class CharDisplayImpl extends DisplayImpl {

	private char head;
	private char body;
	private char foot;

	public CharDisplayImpl(char head, char body, char foot) {
		this.head = head;
		this.body = body;
		this.foot = foot;
	}

	@Override
	public void rawOpen() {
		System.out.print(head);
	}

	@Override
	public void rawPrint() {
		System.out.print(body);
	}

	@Override
	public void rawClose() {
		System.out.println(foot);
	}
}
