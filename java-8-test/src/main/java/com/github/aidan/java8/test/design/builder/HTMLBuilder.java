package com.github.aidan.java8.test.design.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wang yi fei
 * @date 2019/5/15 19:41
 */
public class HTMLBuilder extends Builder {
	private String filename;
	private PrintWriter writer;

	public String getResult() {
		return filename;
	}
	@Override
	protected void buildTitle(String title) {
		filename = title + ".html";
		try {
			writer = new PrintWriter(new FileWriter(filename));
		}catch (IOException e){
			e.printStackTrace();
		}
		writer.println("<html><head><title>"+ title + "</head></title><body>");
		// 输出标题
		writer.println("<h1>" + title + "</h1>");
	}
	@Override
	protected void buildString(String str) {
		writer.println("<p>" + str + "</p>");
	}
	@Override
	protected void buildItems(String[] items) {
		writer.println("<ul>");
		for (int i=0; i< items.length; i++){
			writer.println("<li>" + items[i] + "</li>");
		}
		writer.println("</ul>");
	}
	@Override
	protected void buildDone() {
		writer.println("</body></html>");
		writer.close();
	}
}
