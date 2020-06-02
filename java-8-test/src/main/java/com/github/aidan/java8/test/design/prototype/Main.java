package com.github.aidan.java8.test.design.prototype;

import com.github.aidan.java8.test.design.prototype.framework.Manager;
import com.github.aidan.java8.test.design.prototype.framework.Product;

/**
 * @author wang yi fei
 * @date 2019/5/13 16:37
 */
public class Main {

	public static void main(String[] args) {
		// 准备
		Manager manager = new Manager();
		UnderlinePen underlinePen = new UnderlinePen('~');
		MessageBox mbox = new MessageBox('*');
		MessageBox sbox = new MessageBox('/');
		manager.register("strong message",underlinePen);
		manager.register("warning box",mbox);
		manager.register("slash box",sbox);


		// 生成
		Product p1 = manager.create("strong message");
		p1.use("Hello, world.");
		Product p2 =  manager.create("warning box");
		p2.use("Hello, world.");
		Product p3 = manager.create("slash box");
		p3.use("Hello, world.");
	}
}
