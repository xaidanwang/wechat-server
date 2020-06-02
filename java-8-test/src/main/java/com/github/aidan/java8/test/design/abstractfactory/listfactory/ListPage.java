package com.github.aidan.java8.test.design.abstractfactory.listfactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Item;
import com.github.aidan.java8.test.design.abstractfactory.factory.Page;

import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/17 10:58
 */
public class ListPage extends Page {
	public ListPage(String title, String author) {
		super(title, author);
	}

	@Override
	public String makeHTML() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head><title>" + title + "</head></title>\n");
		buffer.append("<body>\n");
		buffer.append("<h1>" + title + "</h1>\n");
		buffer.append("<ul>\n");
		Iterator it = concent.iterator();
		while (it.hasNext()){
			Item item = (Item)it.next();
			buffer.append(item.makeHTML());
		}
		buffer.append("</ul>\n");
		buffer.append("<hr><address>" + author +"</address>");
		buffer.append("</body></html>\n");
		return buffer.toString();
	}

}
