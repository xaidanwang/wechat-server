package com.github.aidan.java8.test.design.bridge;

/**
 * @author wang yi fei
 * @date 2019/5/18 11:57
 */
public abstract class Display {
	private DisplayImpl impl;

	public Display(DisplayImpl impl) {
		this.impl = impl;
	}
	public void open(){
		impl.rawOpen();
	}
	public void print(){
		impl.rawPrint();
	}
	public void close(){
		impl.rawClose();
	}
	public final void display(){
		open();
		print();
		close();
	}
}
