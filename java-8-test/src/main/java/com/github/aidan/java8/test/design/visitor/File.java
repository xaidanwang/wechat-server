package com.github.aidan.java8.test.design.visitor;

/**
 * @author wang yi fei
 * @date 2019/5/24 14:26
 */
public class File extends Entry {
	private String name;
	private int size;

	public File(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
