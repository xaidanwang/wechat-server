package com.github.aidan.java8.test.design.abstractfactory.tablefactory;

import com.github.aidan.java8.test.design.abstractfactory.factory.Item;
import com.github.aidan.java8.test.design.abstractfactory.factory.Tray;

import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/17 14:50
 */
public class TableTray extends Tray {

	public TableTray(String caption) {
		super(caption);
	}

	@Override
	public String makeHTML() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<td>");
		buffer.append("<table width =\"100%\" border = \"1\"><tr>");
		buffer.append("<td bgcolor=\"#cccccc\" align=\"center\" colspan=\""+ tray.size()+"\"><b>"+caption+"</b></td>");
		buffer.append("</tr>\n");
		buffer.append("<tr>\n");

		Iterator it = tray.iterator();
		while (it.hasNext()){
			Item item = (Item) it.next();
			buffer.append(item.makeHTML());
		}
		buffer.append("</tr></table>");
		buffer.append("</td>");
		return buffer.toString();
	}
}
