package com.github.aidan.java8.test.design.abstractfactory.factory;

/**
 * @author wang yi fei
 * @date 2019/5/16 20:43
 */
public abstract class Item {

	protected String caption;

	public Item(String caption) {
		this.caption = caption;
	}

	public abstract String makeHTML();
}
