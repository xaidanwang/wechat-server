package com.github.aidan.java8.test.design.factory;

import com.github.aidan.java8.test.design.factory.framework.Factory;
import com.github.aidan.java8.test.design.factory.framework.Product;
import com.github.aidan.java8.test.design.factory.idcard.IDCardFactory;

/**
 * @author wang yi fei
 * @date 2019/5/13 10:52
 */
public class Main {

	public static void main(String[] args) {
		Factory factory = new IDCardFactory();
		Product card1 = factory.create("小明");
		Product card2 = factory.create("小红");
		Product card3 = factory.create("小刚");
		card1.use();
		card2.use();
		card3.use();
	}
}
