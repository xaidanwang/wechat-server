package com.github.aidan.java8.test.design.visitor;

import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/24 14:50
 */
public abstract class Entry implements Element {

	public abstract String getName();
	public abstract int getSize();
	public Entry add(Entry entry)throws FileTreatmentException{
		throw new FileTreatmentException();
	}
	public Iterator iterator()throws FileTreatmentException{
		throw new FileTreatmentException();
	}
	public String toString(){
		return getName() + "(" + getSize() + ")";
	}
}
