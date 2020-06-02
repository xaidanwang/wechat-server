package com.github.aidan.java8.test.design.abstractfactory.tablefactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Page;

/**
 * @author wang yi fei
 * @date 2019/5/17 14:59
 */
public class TablePage extends Page {

	public TablePage(String title, String author) {
		super(title, author);
	}

	@Override
	public String makeHTML() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head><title>" + title + "</head></title>\n");
		buffer.append("<body>\n");
		buffer.append("<h1>" + title + "</h1>\n");
		buffer.append("<table width\"=80%\" border = >");
		return buffer.toString();
	}
}
