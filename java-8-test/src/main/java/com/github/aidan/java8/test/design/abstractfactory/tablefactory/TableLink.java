package com.github.aidan.java8.test.design.abstractfactory.tablefactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Link;

/**
 * @author wang yi fei
 * @date 2019/5/17 14:48
 */
public class TableLink extends Link {

	public TableLink(String caption, String url) {
		super(caption, url);
	}

	@Override
	public String makeHTML() {
		return "<td><a href = \"" + url + "\">" + caption + "</a></td>\n";
	}
}
