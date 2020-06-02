package com.github.aidan.java8.test.design.abstractfactory.listfactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Factory;
import com.github.aidan.java8.test.design.abstractfactory.factory.Link;
import com.github.aidan.java8.test.design.abstractfactory.factory.Page;
import com.github.aidan.java8.test.design.abstractfactory.factory.Tray;

/**
 * @author wang yi fei
 * @date 2019/5/17 10:47
 */
public class ListFactory extends Factory {

	@Override
	public Link createLink(String caption, String url) {
		return new ListLink(caption,url);
	}

	@Override
	public Tray createTray(String caption) {
		return new ListTray(caption);
	}

	@Override
	public Page createPage(String title, String author) {
		return new ListPage(title,author);
	}
}
