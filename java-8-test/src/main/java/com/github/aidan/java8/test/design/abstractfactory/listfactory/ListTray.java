package com.github.aidan.java8.test.design.abstractfactory.listfactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Item;
import com.github.aidan.java8.test.design.abstractfactory.factory.Tray;

import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/17 10:51
 */
public class ListTray extends Tray {
	public ListTray(String caption) {
		super(caption);
	}

	@Override
	public String makeHTML() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<li>\n");
		buffer.append(caption + "\n");
		buffer.append("<ul>\n");

		Iterator it = tray.iterator();
		while (it.hasNext()){
			Item item = (Item)it.next();
			buffer.append(item.makeHTML());
		}
		buffer.append("</ul>\n");
		buffer.append("</li>\n");
		return buffer.toString();
	}
}
