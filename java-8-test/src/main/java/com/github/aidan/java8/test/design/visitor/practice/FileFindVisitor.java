package com.github.aidan.java8.test.design.visitor.practice;

import com.github.aidan.java8.test.design.template.AbstractDisplay;
import com.github.aidan.java8.test.design.visitor.Director;
import com.github.aidan.java8.test.design.visitor.Entry;
import com.github.aidan.java8.test.design.visitor.File;
import com.github.aidan.java8.test.design.visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/25 11:00
 */
public class FileFindVisitor extends Visitor {

	private String suffix;

	private ArrayList fileList = new ArrayList();


	public FileFindVisitor(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public void visit(File file) {
		if (isHTMLFile(file)){
			fileList.add(file);
		}
	}

	@Override
	public void visit(Director director) {
		Iterator it = director.iterator();
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			entry.accept(this);
		}
	}
	public Iterator getFoundFiles(){
		return fileList.iterator();
	}

	private boolean isHTMLFile(Entry entry){
		return  entry.getName().endsWith(suffix);
	}
}
