package com.github.aidan.java8.test.design.visitor;

import com.github.aidan.java8.test.design.visitor.practice.SizeVisitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/24 14:27
 */
public class Director extends Entry{
	private String name;
	private ArrayList<Entry> dir = new ArrayList();

	public Director(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getSize() {
		SizeVisitor sv  = new SizeVisitor();
		accept(sv);
		return sv.getSize();
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Entry add(Entry entry) throws FileTreatmentException {
		dir.add(entry);
		return this;
	}
	public Iterator iterator(){
		return dir.iterator();
	}
}
