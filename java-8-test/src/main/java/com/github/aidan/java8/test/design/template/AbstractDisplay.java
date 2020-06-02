package com.github.aidan.java8.test.design.template;

/**
 * @author wang yi fei
 * @date 2019/5/11 19:03
 */
// 抽象类AbstractDisplay
public abstract class AbstractDisplay {
	// 交给子类去实现的抽象方法
	public abstract void open();
	public abstract void print();
	public abstract void close();

	public final void display(){
		open();
		for (int i = 0;i < 5; i++){
			print();
		}
		close();
	}
}
