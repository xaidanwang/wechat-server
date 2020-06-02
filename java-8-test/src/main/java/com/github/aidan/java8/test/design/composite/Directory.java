package com.github.aidan.java8.test.design.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/22 10:17
 */
public class Directory extends Entry {
	private String name;
	private ArrayList directory = new ArrayList();

	public Directory(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		int size = 0;
		Iterator it = directory.iterator();
		while (it.hasNext()){
			Entry entry = (Entry)it.next();
			size += entry.getSize();
		}
		return size;
	}

	@Override
	protected void printList(String prefix) {
		System.out.println(prefix + "/" + this);
		Iterator it = directory.iterator();
		while (it.hasNext()){
			Entry entry = (Entry)it.next();
			entry.printList(prefix + "/" + name);
		}
	}

	@Override
	public Entry add(Entry entry){
		directory.add(entry);
		entry.parent = this;
		return this;
	}
}
