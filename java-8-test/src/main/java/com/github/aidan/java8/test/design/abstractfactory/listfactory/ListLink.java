package com.github.aidan.java8.test.design.abstractfactory.listfactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Link;

/**
 * @author wang yi fei
 * @date 2019/5/17 10:48
 */
public class ListLink extends Link {

	public ListLink(String caption, String url) {
		super(caption, url);
	}

	@Override
	public String makeHTML() {
		return "  <li><a href =\"" + url + "\">" + caption + "</a></li>\n";
	}
}
