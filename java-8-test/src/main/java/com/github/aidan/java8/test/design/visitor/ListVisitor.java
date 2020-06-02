package com.github.aidan.java8.test.design.visitor;


import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/24 15:14
 */
public class ListVisitor extends Visitor {
	private String currentdir = ""; // 当前访问文件夹的名字

	@Override
	public void visit(File file) {
		System.out.println(currentdir + "/" + file);
	}

	@Override
	public void visit(Director director) {
		System.out.println(currentdir + "/" + director);
		String  savedir = currentdir;
		currentdir = currentdir + "/" + director.getName();
		Iterator it = director.iterator();

		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			entry.accept(this);
		}
		currentdir = savedir;
	}
}
