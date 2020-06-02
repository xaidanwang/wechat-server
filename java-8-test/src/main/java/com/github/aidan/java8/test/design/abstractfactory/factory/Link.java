package com.github.aidan.java8.test.design.abstractfactory.factory;

/**
 * @author wang yi fei
 * @date 2019/5/16 20:46
 */
public abstract class Link extends Item {

	protected String url;

	public Link(String caption, String url) {
		super(caption);
		this.url = url;
	}
}
