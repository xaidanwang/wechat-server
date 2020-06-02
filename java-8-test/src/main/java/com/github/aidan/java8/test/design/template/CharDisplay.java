package com.github.aidan.java8.test.design.template;

/**
 * @author wang yi fei
 * @date 2019/5/11 19:16
 */
public class CharDisplay extends AbstractDisplay {

	private char ch;
	public CharDisplay(char ch) {
		this.ch = ch;
	}
	@Override
	public void open() {
		System.out.print("<<");
	}
	@Override
	public void print() {
		System.out.print(ch);
	}
	@Override
	public void close() {
		System.out.println(">>");
	}
}
