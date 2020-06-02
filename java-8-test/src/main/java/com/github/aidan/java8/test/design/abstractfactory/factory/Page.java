package com.github.aidan.java8.test.design.abstractfactory.factory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * @author wang yi fei
 * @date 2019/5/16 20:54
 */
public abstract class Page {
	protected String title;
	protected String author;
	protected ArrayList concent = new ArrayList();

	public Page(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public void add(Item item){
		concent.add(item);
	}

	public void output(){
		try {
			String filenmae = title +".html";
			Writer writer = new FileWriter(filenmae);
			writer.write(this.makeHTML());
			writer.close();
			System.out.println(filenmae + "编写完成。");
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	public abstract String makeHTML();
}
