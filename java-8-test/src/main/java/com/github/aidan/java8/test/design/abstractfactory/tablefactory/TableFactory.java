package com.github.aidan.java8.test.design.abstractfactory.tablefactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Factory;
import com.github.aidan.java8.test.design.abstractfactory.factory.Link;
import com.github.aidan.java8.test.design.abstractfactory.factory.Page;
import com.github.aidan.java8.test.design.abstractfactory.factory.Tray;

/**
 * @author wang yi fei
 * @date 2019/5/17 14:48
 */
public class TableFactory extends Factory {

	@Override
	public Link createLink(String caption, String url) {
		return null;
	}

	@Override
	public Tray createTray(String caption) {
		return null;
	}

	@Override
	public Page createPage(String title, String author) {
		return null;
	}
}
