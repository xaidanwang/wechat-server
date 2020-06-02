package com.github.aidan.java8.test.design.visitor.practice;

import com.github.aidan.java8.test.design.visitor.Director;
import com.github.aidan.java8.test.design.visitor.Entry;
import com.github.aidan.java8.test.design.visitor.File;
import com.github.aidan.java8.test.design.visitor.Visitor;

import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/25 11:52
 */
public class SizeVisitor extends Visitor {

	private int size = 0;
	@Override
	public void visit(File file) {
		size += file.getSize();
	}

	@Override
	public void visit(Director director) {

		Iterator it = director.iterator();
		while (it.hasNext()){
			Entry entry = (Entry)it.next();
			entry.accept(this);
		}
	}
	public int getSize(){
		return size;
	}
}
